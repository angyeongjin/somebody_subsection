package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.AuctionDao;
import com.ssafy.somebody.vo.Auction;

@Repository
public class AuctionDaoImpl implements AuctionDao {

	@Autowired
	private SqlSession sqlsession;
	private final String ns = "auctionMapper.";
	
	@Override
	public int insertAction(Auction auction) {
		return sqlsession.insert(ns+"insertAuction", auction);
	}

	@Override
	public int updateAuction(Auction auction) {
		return sqlsession.update(ns+"updateAuction", auction);
	}

	@Override
	public int deleteAuction(String auctionId) {
		return sqlsession.delete(ns+"deleteAuction", auctionId);
	}

	@Override
	public Auction searchAuction(String auctionId) {
		return sqlsession.selectOne(ns+"searchAuction", auctionId);
	}

	@Override
	public List<Auction> searchAuctionByTag(String tag) {
		return sqlsession.selectList(ns+"searchAuctionByTag", tag);
	}

	@Override
	public List<Auction> searchAuctionByMembers(String memberId) {
		return sqlsession.selectList(ns+"searchAuctionByMembers", memberId);
	}

	@Override
	public List<Auction> searchAllAuction() {
		return sqlsession.selectList(ns+"searchAllAuction");
	}

	@Override
	public List<Auction> searchAllAuctionDesc() {
		return sqlsession.selectList(ns+"searchAllAuctionDesc");
	}

	@Override
	public List<Auction> searchAuctionBySalesTime(String salesTime) {
		return sqlsession.selectList(ns+"searchAuctionBySalesTime", salesTime);
	}

	@Override
	public int updateEnd(Auction auction) {
		return sqlsession.update(ns+"updateEnd", auction);
	}

}
