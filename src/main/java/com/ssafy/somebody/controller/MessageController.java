package com.ssafy.somebody.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.somebody.service.AttendService;
import com.ssafy.somebody.service.AuctionService;
import com.ssafy.somebody.service.MemberService;
import com.ssafy.somebody.service.MessageService;
import com.ssafy.somebody.vo.Attend;
import com.ssafy.somebody.vo.Auction;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Members;
import com.ssafy.somebody.vo.Message;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private AttendService attendService;

	public ResponseEntity<BoolResult> insertMessage(Message message) {
		boolean flag = messageService.insertMessage(message) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{messageId}")
	public ResponseEntity<BoolResult> deleteMember(@PathVariable String messageId) {
		boolean flag = messageService.deleteMessage(messageId) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@GetMapping("/searchByMembersId/{membersId}")
	public ResponseEntity<List<Message>> searchByMembersId(@PathVariable String membersId) {
		List<Message> list = messageService.searchMessageByMember(membersId);
		if (list.size() != 0) {
			return new ResponseEntity<List<Message>>(list, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/{messageId}")
	public ResponseEntity<Message> search(@PathVariable String messageId) {
		Message message = messageService.searchMessage(messageId);
		if (message.getState() == 0) {
			messageService.updateState(message);
		}
		if (message != null) {
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/searchStateCntByMembersId/{membersId}")
	public ResponseEntity<Integer> searchStateCntByMembersId(@PathVariable String membersId) {
		int cnt = messageService.searchStateCntByMembersId(membersId);
		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void endAuction() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date now = new Date();
	    String salesTime = sdf.format(now);
		// 특정 시간 경매 검색
		List<Auction> auctionList = auctionService.searchAuctionBySalesTime(salesTime);
		if (auctionList.size() == 0)
			return;
		for (Auction auction : auctionList) {
			// 해당 경매 등록자 검색 후 알림전송
			String title1 = "[경매 종료] '" + auction.getTitle() + "' 경매 종료";
			String contents1 = "회원님이 등록하신 경매가 종료되었습니다. 경매 확인 후 낙찰을 진행해주세요.\n" + "[" + auction.getTitle() + "]";
			Message msg1 = new Message();
			msg1.setMembersId(auction.getMembersId());
			msg1.setTitle(title1);
			msg1.setContents(contents1);
			insertMessage(msg1);
			// 해당 경매 참가자 검색 후 알림전송
			List<Attend> attendList = attendService.searchAttendByAuctionId(auction.getAuctionId());
			for (Attend attend : attendList) {
				String title2 = "[경매 종료] '" + auction.getTitle() + "' 경매 종료";
				String contents2 = "회원님이 입찰하신 경매가 종료되었습니다.\n" + "[" + auction.getTitle() + "]";
				Message msg2 = new Message();
				msg2.setMembersId(attend.getMembersId());
				msg2.setTitle(title2);
				msg2.setContents(contents2);
				insertMessage(msg2);
			}
		}
	}
}
