package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.PaymentDao;
import com.ssafy.somebody.service.PaymentService;
import com.ssafy.somebody.vo.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public Payment searchPayment(String tid) {
		return paymentDao.searchPayment(tid);
	}

	@Override
	public List<Payment> searchPaymentByMembersId(String membersId) {
		return paymentDao.searchPaymentByMembersId(membersId);
	}

	@Override
	public int insertPayment(Payment payment) {
		return paymentDao.insertPayment(payment);
	}

	@Override
	public int deletePayment(String tid) {
		return paymentDao.deletePayment(tid);
	}

	@Override
	public Payment searchPaymentByAuctionId(String auctionId) {
		return paymentDao.searchPaymentByAuctionId(auctionId);
	}

}
