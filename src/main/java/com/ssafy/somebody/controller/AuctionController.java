package com.ssafy.somebody.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ssafy.somebody.vo.Attend;
import com.ssafy.somebody.vo.Auction;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Message;

@RestController
@RequestMapping("/auction")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class AuctionController {
	
	@Autowired
	AuctionService auctionservice;
	
	@Autowired
	MemberController membercontroller;
	
	@Autowired
	MessageController messagecontroller;
	
	@Autowired
	AttendService attentservice;
	
	@GetMapping("/search/{auctionId}")
	public ResponseEntity<Auction> searchAuction(@PathVariable String auctionId) throws Exception {
		Auction auction = auctionservice.searchAuction(auctionId);
		BoolResult nr = new BoolResult();
		if (auction == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<Auction>(auction, HttpStatus.OK);
	}

	@GetMapping("/searchByTag/{tag}")
	public ResponseEntity<List<Auction>> searchAuctionByTag(@PathVariable String tag) throws Exception {
		List<Auction> auction = auctionservice.searchAuctionByTag(tag);
		BoolResult nr = new BoolResult();
		if (auction == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<List<Auction>>(auction, HttpStatus.OK);
	}
	
	@GetMapping("/searchByMembers/{memberId}")
	public ResponseEntity<List<Auction>> searchAuctionByMembers(@PathVariable String memberId) throws Exception {
		List<Auction> auction = auctionservice.searchAuctionByMembers(memberId);
		BoolResult nr = new BoolResult();
		if (auction == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<List<Auction>>(auction, HttpStatus.OK);
	}
	
	@GetMapping("/searchAll")
	public ResponseEntity<List<Auction>> searchAllAuction() throws Exception {
		List<Auction> auction = auctionservice.searchAllAuction();
		BoolResult nr = new BoolResult();
		if (auction == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<List<Auction>>(auction, HttpStatus.OK);
	}
	
	@GetMapping("/searchAllDesc")
	public ResponseEntity<List<Auction>> searchAllAuctionDesc() throws Exception {
		List<Auction> auction = auctionservice.searchAllAuctionDesc();
		BoolResult nr = new BoolResult();
		if (auction == null) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<List<Auction>>(auction, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<BoolResult> insertAction(Auction auction, HttpServletRequest request) throws Exception {
		String detail = "";
		String thumbnail = request.getScheme() + "://" + request.getServerName() + ":" + 
				   request.getServerPort() + "/thumbnail/" + "thumbnail.png";
		System.out.println(auction.getThumbnailFile().getOriginalFilename());
		System.out.println(auction);
		if (!auction.getDetailFile().getOriginalFilename().equals("")) detail = membercontroller.fileUpload(auction.getDetailFile(), "/detail/", request);
		if (!auction.getThumbnailFile().getOriginalFilename().equals("")) thumbnail = membercontroller.fileUpload(auction.getThumbnailFile(), "/thumbnail/", request);
		
		auction.setDetail(detail);
		auction.setThumbnail(thumbnail);
		boolean flag = auctionservice.insertAction(auction) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState(auction.getAuctionId(	));
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<BoolResult> updateAction(Auction auction, HttpServletRequest request) throws Exception {
		String detail = null;
		String thumbnail = null;
		
		if (!auction.getDetailFile().getOriginalFilename().equals("")) detail = membercontroller.fileUpload(auction.getDetailFile(), "/detail", request);
		if (!auction.getThumbnailFile().getOriginalFilename().equals("")) thumbnail = membercontroller.fileUpload(auction.getThumbnailFile(), "/thumbnail", request);
		
		auction.setDetail(detail);
		auction.setThumbnail(thumbnail);
		boolean flag = auctionservice.updateAuction(auction) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
	
	@PutMapping("/updateEnd")
	public ResponseEntity<BoolResult> updateEnd(@RequestBody Auction auction) throws Exception {
		boolean flag = auctionservice.updateEnd(auction) > 0 ? true : false;
		Attend attend = attentservice.searchPickAttendByAuctionId(auction.getAuctionId());
		BoolResult nr = new BoolResult();
		if (flag == false) {
			nr.setState("fail");
		} else {
			String title1 = "[알림] '" + auction.getTitle() + "' 경매 종료 및 입금 안내";
			String contents1 = auction.getTitle() + ".해당 경매 등록자가 구매를 확정하여 경매가 완전히 종료되었습니다. 입금금액 : " + attend.getPrice() + ".입금 내역을 확인해주세요";
			Message msg = new Message();
			msg.setMembersId(attend.getMembersId());
			msg.setTitle(title1);
			msg.setContents(contents1);
			messagecontroller.insertMessage(msg);
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
			
	@DeleteMapping("/delete/{auctionId}")
	public ResponseEntity<BoolResult> deleteAuction(@PathVariable String auctionId) throws Exception {
		boolean flag = auctionservice.deleteAuction(auctionId) > 0 ? true : false;
		BoolResult nr = new BoolResult();
		if (!flag) {
			nr.setState("fail");
		} else {
			nr.setState("succ");
		}
		return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
	}
}