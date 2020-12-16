package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.FeedbackDao;
import com.ssafy.somebody.vo.Feedback;

@Repository
public class FeedbackDaoImpl implements FeedbackDao{
	
	@Autowired
	SqlSession sqlsession;
	
	private final String ns = "feedbackMapper.";
	
	
	@Override
	public List<Feedback> searchAllFeedback() {
		return sqlsession.selectList(ns+"searchAllFeedback");
	}

	@Override
	public List<Feedback> searchFeedbackByMembersId(String membersId) {
		return sqlsession.selectList(ns+"searchFeedbackByMembersId",membersId);
	}

	@Override
	public Feedback searchFeedbackByFeedbackId(String feedbackId) {
		return sqlsession.selectOne(ns+"searchFeedbackByFeedbackId",feedbackId);
	}

	@Override
	public int insertFeedback(Feedback feedback) {
		return sqlsession.insert(ns+"insertFeedback",feedback );
	}

	@Override
	public int updateFeedback(Feedback feedback) {
		return sqlsession.update(ns+"updateFeedback",feedback);
	}

	@Override
	public int deleteFeedback(String feedbackId) {
		return sqlsession.delete(ns+"deleteFeedback",feedbackId);
	}
	
}
