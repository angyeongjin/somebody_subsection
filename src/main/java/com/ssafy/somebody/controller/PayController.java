package com.ssafy.somebody.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssafy.somebody.service.PaymentService;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.KakaoPayApproval;
import com.ssafy.somebody.vo.KakaoPayReady;
import com.ssafy.somebody.vo.Payment;

@RestController
@RequestMapping("/pay")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class PayController {

	private static final String HOST = "https://kapi.kakao.com";

	private KakaoPayReady kakaoPayReady;
	private KakaoPayApproval kakaoPayApproval;

	@Autowired
	private PaymentService paymentservice;

	@PostMapping("/paykakao")
	public ResponseEntity<String> kakaoPayReady(@RequestBody Payment payment, HttpServletRequest request) {

		RestTemplate restTemplate = new RestTemplate();
		String webUrl = "http://localhost:8080/";
		
		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "b0de6416836d503b141aecdf8918fa1e");
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", "Somebody Corp.");
		params.add("partner_user_id", payment.getMembersId());
		params.add("item_name", payment.getItemName());
		params.add("quantity", "1");
		params.add("total_amount", String.valueOf(payment.getPrice()));
		params.add("tax_free_amount", "0");
		params.add("approval_url", webUrl+"#/PaymentSucc?membersId="+payment.getMembersId()+"&auctionId="+payment.getAuctionId());
		params.add("cancel_url", webUrl+"home");
		params.add("fail_url", webUrl+"home");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);
			return new ResponseEntity<String>(kakaoPayReady.getNext_redirect_pc_url(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/succPaykakao/{membersId}/{auctionId}/{pg_token}")
	public ResponseEntity<Payment> kakaoPayInfo(@PathVariable String membersId, @PathVariable String auctionId, @PathVariable String pg_token) {

		RestTemplate restTemplate = new RestTemplate();

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + "b0de6416836d503b141aecdf8918fa1e");
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 서버로 요청할 Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReady.getTid());
		params.add("partner_order_id", "Somebody Corp.");
		params.add("partner_user_id", membersId);
		params.add("pg_token", pg_token);

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

		try {
			kakaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
					KakaoPayApproval.class);
			Payment payment = createPayment(kakaoPayApproval, auctionId);
			insertPayment(payment);
			return new ResponseEntity<Payment>(payment, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	public Payment createPayment(KakaoPayApproval kakaoPayApproval, String auctionId) {
		Payment payment = new Payment();
		payment.setMembersId(kakaoPayApproval.getPartner_user_id());
		payment.setItemName(kakaoPayApproval.getItem_name());
		payment.setPrice(kakaoPayApproval.getAmount().getTotal());
		payment.setTid(kakaoPayApproval.getTid());
		payment.setPaymentMethodType(kakaoPayApproval.getPayment_method_type());
		payment.setAuctionId(auctionId);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String approvedAt = transFormat.format(kakaoPayApproval.getApproved_at());
		payment.setApprovedAt(approvedAt);
		return payment;
	}

	public ResponseEntity<BoolResult> insertPayment(Payment payment) {
		boolean flag = paymentservice.insertPayment(payment) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{tid}")
	public ResponseEntity<BoolResult> deleteMember(@PathVariable String tid) {
		boolean flag = paymentservice.deletePayment(tid) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@GetMapping("/searchByMembersId/{membersId}")
	public ResponseEntity<List<Payment>> searchByMembersId(@PathVariable String membersId) {
		List<Payment> list = paymentservice.searchPaymentByMembersId(membersId);
		if (list.size() != 0) {
			return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/{tid}")
	public ResponseEntity<Payment> search(@RequestParam String tid) {
		Payment payment = paymentservice.searchPayment(tid);
		if (payment != null) {
			return new ResponseEntity<Payment>(payment, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/searchByAuctionId/{auctionId}")
	public ResponseEntity<Payment> searchByAuctionId(@PathVariable String auctionId) {
		Payment payment = paymentservice.searchPaymentByAuctionId(auctionId);
		if (payment != null) {
			return new ResponseEntity<Payment>(payment, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
