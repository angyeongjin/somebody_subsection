package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Payment;

public interface PaymentService {

	Payment searchPayment(String tid);
	Payment searchPaymentByAuctionId(String auctionId);
	List<Payment> searchPaymentByMembersId(String membersId);
	int insertPayment(Payment payment);
	int deletePayment(String tid);
}
