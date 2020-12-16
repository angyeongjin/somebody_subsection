package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.MessageDao;
import com.ssafy.somebody.service.MessageService;
import com.ssafy.somebody.vo.Message;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public int insertMessage(Message message) {
		return messageDao.insertMessage(message);
	}

	@Override
	public int deleteMessage(String messageId) {
		return messageDao.deleteMessage(messageId);
	}

	@Override
	public List<Message> searchMessageByMember(String memberId) {
		return messageDao.searchMessageByMember(memberId);
	}

	@Override
	public Message searchMessage(String messageId) {
		return messageDao.searchMessage(messageId);
	}

	@Override
	public int updateState(Message message) {
		return messageDao.updateState(message);
	}

	@Override
	public int searchStateCntByMembersId(String membersId) {
		return messageDao.searchStateCntByMembersId(membersId);
	}

}
