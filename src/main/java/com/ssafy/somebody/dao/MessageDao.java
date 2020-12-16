package com.ssafy.somebody.dao;


import java.util.List;

import com.ssafy.somebody.vo.Message;

public interface MessageDao {
	int insertMessage(Message message);
	int deleteMessage(String messageId);
	List<Message> searchMessageByMember(String memberId);
	Message searchMessage(String messageId);
	int updateState(Message message);
	int searchStateCntByMembersId(String membersId);
}
