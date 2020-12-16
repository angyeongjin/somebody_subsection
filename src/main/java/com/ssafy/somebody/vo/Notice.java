package com.ssafy.somebody.vo;

public class Notice {
	private String title;
	private String contents;
	private String membersId;
	private String date;
	private String noticeId;
	
	public Notice() {
		super();
	}

	public Notice(String title, String contents, String membersId, String date, String noticeId) {
		this.title = title;
		this.contents = contents;
		this.membersId = membersId;
		this.date = date;
		this.noticeId = noticeId;
	}
	
	
	
	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getMembersId() {
		return membersId;
	}

	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Notice [title=" + title + ", contents=" + contents + ", membersId=" + membersId + ", date=" + date
				+ "]";
	}

}
