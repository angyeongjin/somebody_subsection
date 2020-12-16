package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.PaymentDao;
import com.ssafy.somebody.vo.Payment;

@Repository
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SqlSession sqlsession;
	
	private String nr = "paymentMapper.";
	
	@Override
	public Payment searchPayment(String tid) {
		return sqlsession.selectOne(nr+"searchPayment", tid);
	}

	@Override
	public List<Payment> searchPaymentByMembersId(String membersId) {
		return sqlsession.selectList(nr+"searchPaymentByMembersId", membersId);
	}

	@Override
	public int insertPayment(Payment payment) {
		return sqlsession.insert(nr+"insertPayment", payment);
	}

	@Override
	public int deletePayment(String tid) {
		return sqlsession.delete(nr+"deletePayment", tid);
	}

	@Override
	public Payment searchPaymentByAuctionId(String auctionId) {
		return sqlsession.selectOne(nr+"searchPaymentByAuctionId", auctionId);
	}

}
