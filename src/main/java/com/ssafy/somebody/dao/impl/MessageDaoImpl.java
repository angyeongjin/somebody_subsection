package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.MessageDao;
import com.ssafy.somebody.vo.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private SqlSession sqlsession;
	
	private String ns = "messageMapper.";

	@Override
	public int insertMessage(Message message) {
		return sqlsession.insert(ns+"insertMessage", message);
	}

	@Override
	public int deleteMessage(String messageId) {
		return sqlsession.delete(ns+"deleteMessage", messageId);
	}

	@Override
	public List<Message> searchMessageByMember(String memberId) {
		return sqlsession.selectList(ns+"searchMessageByMemberId", memberId);
	}

	@Override
	public Message searchMessage(String messageId) {
		return sqlsession.selectOne(ns+"searchMessage", messageId);
	}

	@Override
	public int updateState(Message message) {
		return sqlsession.update(ns+"updateState", message);
	}

	@Override
	public int searchStateCntByMembersId(String membersId) {
		return sqlsession.selectOne(ns+"searchStateCntByMembersId", membersId);
	}


}
