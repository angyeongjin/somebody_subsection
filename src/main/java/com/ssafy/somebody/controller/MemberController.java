package com.ssafy.somebody.controller;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.somebody.service.MemberService;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Members;
import com.ssafy.somebody.vo.Verify;
import com.sun.mail.util.logging.MailHandler;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MemberController {

	@Autowired
	MemberService memberservice;

	@Autowired
	JavaMailSender mailSender;

	@GetMapping("/search/{membersId}")
	public ResponseEntity<BoolResult> searchMember(@PathVariable String membersId) throws Exception {
		Members members = memberservice.searchMember(membersId);
		BoolResult nr = new BoolResult();
		if (members == null) {
			nr.setState("fail");
			return new ResponseEntity<BoolResult>(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity(members, HttpStatus.OK);
	}

	@GetMapping("/searchAll")
	public ResponseEntity<BoolResult> searchAllMember() throws Exception {
		List<Members> memberslist = memberservice.searchAllMember();
		BoolResult nr = new BoolResult();
		nr.setState("succ");
		return new ResponseEntity(memberslist, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<BoolResult> insertMember(Members member, HttpServletRequest request) throws Exception {
		String image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/"
				+ "profile.png";
		String portfolio = "";

		if (!member.getImageFile().getOriginalFilename().equals(""))
			image = fileUpload(member.getImageFile(), "/image/", request);
		if (!member.getPortfolioFile().getOriginalFilename().equals(""))
			portfolio = fileUpload(member.getPortfolioFile(), "/portfolio/", request);

		member.setPortfolio(portfolio);
		member.setImage(image);
		boolean flag = memberservice.insertMember(member) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<BoolResult> updateMember(Members member, HttpServletRequest request) throws Exception {
		String image = null;
		String portfolio = null;
		
		if (!member.getImageFile().getOriginalFilename().equals(""))
			image = fileUpload(member.getImageFile(), "/image/", request);
		if (!member.getPortfolioFile().getOriginalFilename().equals(""))
			portfolio = fileUpload(member.getPortfolioFile(), "/portfolio/", request);

		member.setPortfolio(portfolio);
		member.setImage(image);
		boolean flag = memberservice.updateMember(member) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<BoolResult> updatePassword(@RequestBody Members member) throws Exception {
		boolean flag = memberservice.updatePassword(member) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{membersId}")
	public ResponseEntity<BoolResult> deleteMember(@PathVariable String membersId) throws Exception {
		boolean flag = memberservice.deleteMember(membersId) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PostMapping("/login") // = login
	public ResponseEntity<BoolResult> checkPass(@RequestBody Members member) throws Exception {
		Members members = memberservice.passCheck(member.getMembersId(), member.getPassword());
		BoolResult nr = new BoolResult();
		if (members == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity(nr, HttpStatus.OK);
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<BoolResult> sendEmail(@RequestBody Verify verify) throws Exception {
		BoolResult nr = new BoolResult();
		Members member = memberservice.searchMemberByEmail(verify.getEmail());
		if (member != null) {
			nr.setState("exist");
			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
		}
		Verify isVerify = memberservice.searchVerify(verify.getEmail());
		verify.setCode(randomCode());
		if (isVerify == null) {
			memberservice.insertVerify(verify);
		} else {
			memberservice.updateVerify(verify);
		}
		// 여기 이메일 보내는 코드 작성
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(verify.getEmail());
		message.setSubject("[Somebody]이메인 인증 코드 발송 메일입니다."); // 제목

		String contents = "회원가입 페이지에서 인증 코드를 입력하세요.\n\ncode : " + verify.getCode();
		message.setText(contents);
		mailSender.send(message);

		nr.setState("succ");
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PostMapping("/checkEmail")
	public ResponseEntity<BoolResult> checkEmail(@RequestBody Verify verify) throws Exception {
		Verify checkVerify = memberservice.checkVerify(verify);
		BoolResult nr = new BoolResult();
		if (checkVerify == null) {
			nr.setState("fail");
		} else {
			verify.setCode("Y");
			memberservice.updateVerify(verify);
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PutMapping("/updateRank")
	public ResponseEntity<BoolResult> updateRank(@RequestBody Members member) throws Exception {
		boolean flag = memberservice.updateRank(member) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	public String fileUpload(MultipartFile file, String folder, HttpServletRequest request) throws Exception {

		String realPathtoUploads =  request.getServletContext().getRealPath(folder);
		
		String orgName = genSaveFileName(file.getOriginalFilename());
		String filePath = realPathtoUploads + orgName;
		if(! new File(realPathtoUploads).exists())
		{
			System.out.println("파일의 사이즈 : " + file.getSize());
			System.out.println("파일의 이름 : " + file.getOriginalFilename());
			new File(realPathtoUploads).mkdir();
		}
		File dest = new File(filePath);
		file.transferTo(dest);
		System.out.println("real path upload : " + realPathtoUploads);
		System.out.println("file path : " + filePath);

		String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + folder;
		
		return serverUrl + orgName;
	}

	private String genSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	private String randomCode() {
		int certNumLength = 6;

		Random random = new Random(System.currentTimeMillis());

		int range = (int) Math.pow(10, certNumLength);
		int trim = (int) Math.pow(10, certNumLength - 1);
		int result = random.nextInt(range) + trim;

		if (result > range) {
			result = result - trim;
		}

		return String.valueOf(result);
	}
}