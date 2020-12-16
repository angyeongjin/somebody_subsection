package com.ssafy.somebody.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.somebody.service.AttendService;
import com.ssafy.somebody.service.AuctionService;
import com.ssafy.somebody.service.MemberService;
import com.ssafy.somebody.vo.Attend;
import com.ssafy.somebody.vo.Auction;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Members;
import com.ssafy.somebody.vo.Message;

@RestController
@RequestMapping("/attend")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class AttendController {

	@Autowired
	private AttendService attendservice;
	
	@Autowired
	private AuctionService auctionservice;
	
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private MessageController messagecontroller;
	
	@GetMapping("/searchByAuctionId/{auctionId}")
	public ResponseEntity<List<Attend>> searchAttendByAuctionId(@PathVariable String auctionId) throws Exception {
		List<Attend> attend = attendservice.searchAttendByAuctionId(auctionId);
		System.out.println(attend.size());
		BoolResult nr = new BoolResult();
		if (attend == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<List<Attend>>(attend, HttpStatus.OK);
	}

	@GetMapping("/searchByMemberId/{membersId}")
	public ResponseEntity<BoolResult> searchAttendByMembersId(@PathVariable String membersId) throws Exception {
		List<Attend> attend = attendservice.searchAttendByMembersId(membersId);
		BoolResult nr = new BoolResult();
		if (attend == null) {
			nr.setState("fail");
			return new ResponseEntity<BoolResult>(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity(attend, HttpStatus.OK);
	}
	
	@GetMapping("/searchPickByAuctionId/{auctionId}")
	public ResponseEntity<Attend> searchPickAttendByAuctionId(@PathVariable String auctionId) throws Exception {
		Attend attend = attendservice.searchPickAttendByAuctionId(auctionId);
		BoolResult nr = new BoolResult();
		if (attend == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<Attend>(attend, HttpStatus.OK);
	}
	
	@PostMapping("/attend")
	public ResponseEntity<BoolResult> attend(@RequestBody Attend attend) {
		boolean flag = attendservice.attend(attend) > 0 ? true : false;
		
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			Members member = memberservice.searchMember(attend.getMembersId());
			Auction auction = auctionservice.searchAuction(attend.getAuctionId());
			String title = "[알림] 회원님의 경매에 " + member.getName() + " 님이 입찰하였습니다.";
			String contents = "회원님이 등록하신 경매에 " + member.getName() + "님이 입찰하였습니다.\n입찰자의 정보를 확인 해 보세요!";
			Message msg = new Message();
			msg.setMembersId(auction.getMembersId());
			msg.setTitle(title);
			msg.setContents(contents);
			messagecontroller.insertMessage(msg);
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PutMapping("/pickAttend")
	public ResponseEntity<BoolResult> pickAttend(@RequestBody Attend attend) throws Exception {
		boolean flag = attendservice.pickAttend(attend.getAttendId()) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			Auction auction = auctionservice.searchAuction(attend.getAuctionId());
			String title = "[알림] " + auction.getTitle() + " 낙찰 안내";
			String contents = "회원님이 입찰하신 경매에 낙찰되었습니다.\n\n낙찰된 경매 : " + auction.getTitle();
			Message msg = new Message();
			msg.setMembersId(attend.getMembersId());
			msg.setTitle(title);
			msg.setContents(contents);
			messagecontroller.insertMessage(msg);
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@DeleteMapping("/cancel/{attendId}")
	public ResponseEntity<BoolResult> cancelAttend(@PathVariable String attendId) throws Exception {
		boolean flag = attendservice.cancelAttend(attendId) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelByMembersId/{membersId}")
	public ResponseEntity<BoolResult> cancelAttendByMembersId(@PathVariable String membersId) throws Exception {
		boolean flag = attendservice.cancelAttendByMembersId(membersId) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

}
