package com.ssafy.somebody.service;

import java.util.List;

import com.ssafy.somebody.vo.Notice;

public interface NoticeService {
	
	List<Notice> searchAllNotice();
	int insertNotice(Notice notice);
	int updateNotice(Notice notice);
	int deleteNotice(String noticeId);
	List<Notice> searchNoticeByContent(String content);
	Notice searchNoticeByNoticeId(String noticeId);
}
