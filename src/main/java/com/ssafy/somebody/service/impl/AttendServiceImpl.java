package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.AttendDao;
import com.ssafy.somebody.service.AttendService;
import com.ssafy.somebody.vo.Attend;

@Service
public class AttendServiceImpl implements AttendService {

	@Autowired
	private AttendDao attendDao;

	@Override
	public int attend(Attend attend) {
		return attendDao.attend(attend);
	}

	@Override
	public int cancelAttend(String attendId) {
		return attendDao.cancelAttend(attendId);
	}

	@Override
	public List<Attend> searchAttendByAuctionId(String auctionId) {
		return attendDao.searchAttendByAuctionId(auctionId);
	}

	@Override
	public List<Attend> searchAttendByMembersId(String membersId) {
		return attendDao.searchAttendByMembersId(membersId);
	}

	@Override
	public int pickAttend(String AttendId) {
		return attendDao.pickAttend(AttendId);
	}

	@Override
	public Attend searchPickAttendByAuctionId(String auctionId) {
		return attendDao.searchPickAttendByAuctionId(auctionId);
	}

	@Override
	public int cancelAttendByMembersId(String membersId) {
		return attendDao.cancelAttendByMembersId(membersId);
	}
}
