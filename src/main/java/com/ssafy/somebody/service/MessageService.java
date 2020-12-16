package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Message;

public interface MessageService {
	int insertMessage(Message message);
	int deleteMessage(String messageId);
	List<Message> searchMessageByMember(String memberId);
	Message searchMessage(String messageId);
	int updateState(Message message);
	int searchStateCntByMembersId(String membersId);
}
