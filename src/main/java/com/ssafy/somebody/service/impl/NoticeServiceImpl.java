package com.ssafy.somebody.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.somebody.dao.NoticeDao;
import com.ssafy.somebody.service.NoticeService;
import com.ssafy.somebody.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDao noticeDao;
	
	@Override
	public List<Notice> searchAllNotice() {
		return noticeDao.searchAllNotice();
	}

	@Override
	public int insertNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(String noticeId) {
		return noticeDao.deleteNotice(noticeId);
	}

	@Override
	public List<Notice> searchNoticeByContent(String content) {
		return noticeDao.searchNoticeByContent(content);
	}

	@Override
	public Notice searchNoticeByNoticeId(String noticeId) {
		return noticeDao.searchNoticeByNoticeId(noticeId);
	}
	
}
