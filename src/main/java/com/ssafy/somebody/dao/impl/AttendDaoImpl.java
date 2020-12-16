package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.AttendDao;
import com.ssafy.somebody.vo.Attend;

@Repository
public class AttendDaoImpl implements AttendDao {

	@Autowired
	private SqlSession sqlsession;
	
	private String ns = "attendMapper.";

	@Override
	public int attend(Attend attend) {
		return sqlsession.insert(ns+"attend", attend);
	}

	@Override
	public int cancelAttend(String attendId) {
		return sqlsession.delete(ns+"cancelAttend", attendId);
	}

	@Override
	public List<Attend> searchAttendByAuctionId(String auctionId) {
		return sqlsession.selectList(ns+"searchAttendByAuctionId", auctionId);
	}

	@Override
	public List<Attend> searchAttendByMembersId(String membersId) {
		return sqlsession.selectList(ns+"searchAttendByMembersId", membersId);
	}

	@Override
	public int pickAttend(String AttendId) {
		return sqlsession.update(ns+"pickAttend", AttendId);
	}

	@Override
	public Attend searchPickAttendByAuctionId(String auctionId) {
		return sqlsession.selectOne(ns+"searchPickAttendByAuctionId", auctionId);
	}

	@Override
	public int cancelAttendByMembersId(String membersId) {
		return sqlsession.delete(ns+"cancelAttendByMembersId", membersId);
	}
}
