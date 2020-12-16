package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Auction;

public interface AuctionService {
	int insertAction(Auction auction);
	int updateAuction(Auction auction);
	int updateEnd(Auction auction);
	int deleteAuction(String auctionId);
	Auction searchAuction(String auctionId);
	List<Auction> searchAuctionByTag(String tag);
	List<Auction> searchAuctionByMembers(String memberId);
	List<Auction> searchAllAuction();
	List<Auction> searchAllAuctionDesc();
	List<Auction> searchAuctionBySalesTime(String salesTime);

}
