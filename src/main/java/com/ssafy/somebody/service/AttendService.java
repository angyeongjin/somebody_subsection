package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Attend;

public interface AttendService {
	int attend(Attend attend);
	int cancelAttend(String attendId);
	int cancelAttendByMembersId(String membersId);
	List<Attend> searchAttendByAuctionId(String auctionId);
	List<Attend> searchAttendByMembersId(String membersId);
	Attend searchPickAttendByAuctionId(String auctionId);
	int pickAttend(String AttendId);
}
