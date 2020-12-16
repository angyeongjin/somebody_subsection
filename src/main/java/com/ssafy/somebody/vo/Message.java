package com.ssafy.somebody.vo;

public class Message {

	private String messageId;
	private String membersId;
	private String title;
	private String contents;
	private String date;
	private int state;
	public Message() { }
	public Message(String messageId, String membersId, String title, String contents, String date, int state) {
		super();
		this.messageId = messageId;
		this.membersId = membersId;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.state = state;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMembersId() {
		return membersId;
	}
	public void setMembersId(String membersId) {
		this.membersId = membersId;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", membersId=" + membersId + ", title=" + title + ", contents="
				+ contents + ", date=" + date + ", state=" + state + "]";
	}
	
}
