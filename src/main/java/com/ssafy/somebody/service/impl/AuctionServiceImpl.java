package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.AuctionDao;
import com.ssafy.somebody.service.AuctionService;
import com.ssafy.somebody.vo.Auction;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionDao auctionDao;
	
	@Override
	public int insertAction(Auction auction) {
		return auctionDao.insertAction(auction);
	}

	@Override
	public int updateAuction(Auction auction) {
		return auctionDao.updateAuction(auction);
	}

	@Override
	public int deleteAuction(String auctionId) {
		return auctionDao.deleteAuction(auctionId);
	}

	@Override
	public Auction searchAuction(String auctionId) {
		return auctionDao.searchAuction(auctionId);
	}

	@Override
	public List<Auction> searchAuctionByTag(String tag) {
		return auctionDao.searchAuctionByTag(tag);
	}

	@Override
	public List<Auction> searchAuctionByMembers(String memberId) {
		return auctionDao.searchAuctionByMembers(memberId);
	}

	@Override
	public List<Auction> searchAllAuction() {
		return auctionDao.searchAllAuction();
	}

	@Override
	public List<Auction> searchAllAuctionDesc() {
		return auctionDao.searchAllAuctionDesc();
	}

	@Override
	public List<Auction> searchAuctionBySalesTime(String salesTime) {
		return auctionDao.searchAuctionBySalesTime(salesTime);
	}

	@Override
	public int updateEnd(Auction auction) {
		return auctionDao.updateEnd(auction);
	}

}
