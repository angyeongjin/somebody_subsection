package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Feedback;

public interface FeedbackService {
	
	List<Feedback> searchAllFeedback();
	List<Feedback> searchFeedbackByMembersId(String membersId);
	Feedback searchFeedbackByFeedbackId(String feedbackId);
	int insertFeedback(Feedback feedback);
	int updateFeedback(Feedback feedback);
	int deleteFeedback(String feedbackId);
	
}
