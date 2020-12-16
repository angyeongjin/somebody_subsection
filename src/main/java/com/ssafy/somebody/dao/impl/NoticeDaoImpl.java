package com.ssafy.somebody.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.somebody.dao.NoticeDao;
import com.ssafy.somebody.vo.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	SqlSession sqlsession;
	private final String ns = "noticeMapper.";
	
	@Override
	public List<Notice> searchAllNotice() {
		return sqlsession.selectList(ns+"searchAllNotice");
	}

	@Override
	public int insertNotice(Notice notice) {
		return sqlsession.insert(ns+"insertNotice",notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return sqlsession.update(ns+"updateNotice",notice);
	}

	@Override
	public int deleteNotice(String noticeId) {
		return sqlsession.delete(ns+"deleteNotice",noticeId);
	}

	@Override
	public List<Notice> searchNoticeByContent(String content) {
		return sqlsession.selectList(ns+"searchNoticeByContent",content);
	}

	@Override
	public Notice searchNoticeByNoticeId(String noticeId) {
		return sqlsession.selectOne(ns+"searchNoticeByNoticeId",noticeId);
	}
	
}
