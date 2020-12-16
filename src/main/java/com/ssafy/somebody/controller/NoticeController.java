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

import com.ssafy.somebody.service.NoticeService;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Notice;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@PostMapping("/insert")
	public ResponseEntity<BoolResult> insertNotice(@RequestBody Notice notice) throws Exception {
		int result = 0;
		BoolResult nr = new BoolResult();
		try {
			result = noticeService.insertNotice(notice);
			nr.setState("succ");
			return new ResponseEntity(nr, HttpStatus.OK);
		} catch (Exception e) {
			nr.setState("fail");
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/searchAll")
	public ResponseEntity<List<Notice>> searchAllNotice() throws Exception {
		List<Notice> list = noticeService.searchAllNotice();
		if (list.size() != 0) {
			return new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/search/{content}")
	public ResponseEntity<List<Notice>> searchNoticeByContent(@PathVariable String content)throws Exception{
		List<Notice> list = noticeService.searchNoticeByContent(content);
		if(list.size()!=0) {
			for(Notice n : list) {
				System.out.println(n);
			}
			return new ResponseEntity<List<Notice>>(list,HttpStatus.OK);
		}
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{noticeId}")
	public ResponseEntity deleteNotice(@PathVariable String noticeId) throws Exception{
		int result =0;
		BoolResult nr = new BoolResult();
		try {
			result = noticeService.deleteNotice(noticeId);
			nr.setState("succ");
			return new ResponseEntity(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<BoolResult> updateNotice(@RequestBody Notice notice) throws Exception{
		int result =0;
		System.out.println(notice);
		BoolResult nr = new BoolResult();
		try {
			result = noticeService.updateNotice(notice);
			nr.setState("succ");
			return new ResponseEntity(nr, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/searchByNoticeId/{noticeId}")
	public ResponseEntity<Notice> searchNoticeByNoticeId(@PathVariable String noticeId)throws Exception{
		Notice notice;
		try {
			notice = noticeService.searchNoticeByNoticeId(noticeId);
			return new ResponseEntity<Notice>(notice,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
}
