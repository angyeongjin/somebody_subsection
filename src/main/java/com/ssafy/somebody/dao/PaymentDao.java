package com.ssafy.somebody.dao;

import java.util.List;

import com.ssafy.somebody.vo.Payment;

public interface PaymentDao {
	Payment searchPayment(String tid);
	Payment searchPaymentByAuctionId(String auctionId);
	List<Payment> searchPaymentByMembersId(String membersId);
	int insertPayment(Payment payment);
	int deletePayment(String tid);
}
