package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.FeedbackDao;
import com.ssafy.somebody.service.FeedbackService;
import com.ssafy.somebody.vo.Feedback;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	FeedbackDao feedbackDao;

	@Override
	public List<Feedback> searchAllFeedback() {
		return feedbackDao.searchAllFeedback();
	}

	@Override
	public List<Feedback> searchFeedbackByMembersId(String membersId) {
		return feedbackDao.searchFeedbackByMembersId(membersId);
	}

	@Override
	public Feedback searchFeedbackByFeedbackId(String feedbackId) {
		return feedbackDao.searchFeedbackByFeedbackId(feedbackId);
	}

	@Override
	public int insertFeedback(Feedback feedback) {
		return feedbackDao.insertFeedback(feedback);
	}

	@Override
	public int updateFeedback(Feedback feedback) {
		return feedbackDao.updateFeedback(feedback);
	}

	@Override
	public int deleteFeedback(String feedbackId) {
		return feedbackDao.deleteFeedback(feedbackId);
	}
	
}
