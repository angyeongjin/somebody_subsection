package com.ssafy.somebody.vo;

public class Feedback {

	private String title;
	private String contents;
	private String membersId;
	private String date;
	private String answer;
	private String feedbackId;

	public Feedback() {
	}

	public Feedback(String title, String contents, String membersId, String date, String answer,String feedbackId) {
		this.title = title;
		this.contents = contents;
		this.membersId = membersId;
		this.date = date;
		this.answer = answer;
		this.feedbackId=feedbackId;
	}

	
	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Feedback [title=" + title + ", contents=" + contents + ", membersId=" + membersId + ", date=" + date
				+ ", answer=" + answer + ", feedbackId=" + feedbackId + "]";
	}


	
}
