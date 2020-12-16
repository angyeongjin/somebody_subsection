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

import com.ssafy.somebody.service.FeedbackService;
import com.ssafy.somebody.vo.BoolResult;
import com.ssafy.somebody.vo.Feedback;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping("/insertFeedback")
	public ResponseEntity<BoolResult> insertFeedback(@RequestBody Feedback feedback) throws Exception {
		int result = 0;
		BoolResult nr = new BoolResult();
		try {
			result = feedbackService.insertFeedback(feedback);
			nr.setState("succ");
			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/searchAllFeedback")
	public ResponseEntity<List<Feedback>> searchAllFeedback() throws Exception {
		List<Feedback> list;
		try {
			list = feedbackService.searchAllFeedback();
			return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/searchFeedbackByMembersId/{membersId}")
	public ResponseEntity<List<Feedback>> searchFeedbackByMemberId(@PathVariable String membersId) throws Exception {
		List<Feedback> list;
		try {
			list = feedbackService.searchFeedbackByMembersId(membersId);
			return new ResponseEntity<List<Feedback>>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/searchFeedbackByFeedbackId/{feedbackId}")
	public ResponseEntity<Feedback> searchFeedbackByFeedbackId(@PathVariable String feedbackId) throws Exception {
		Feedback feedback;
		try {
			feedback = feedbackService.searchFeedbackByFeedbackId(feedbackId);
			return new ResponseEntity<Feedback>(feedback,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/updateFeedback")
	public ResponseEntity<BoolResult> updateFeedback(@RequestBody Feedback feedback) throws Exception {
		BoolResult nr = new BoolResult();
		int result = 0;
		try {
			result = feedbackService.updateFeedback(feedback);
			nr.setState("succ");
			return new ResponseEntity(nr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/deleteFeedback/{feedbackId}")
	public ResponseEntity<BoolResult> deleteFeedback(@PathVariable String feedbackId) throws Exception {
		int result = 0;
		BoolResult nr = new BoolResult();
		try {
			result = feedbackService.deleteFeedback(feedbackId);
			nr.setState("succ");
			return new ResponseEntity<BoolResult>(nr, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

}
