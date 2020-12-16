package com.ssafy.somebody.vo;

public class Attend {
	private String attendId;
	private String membersId;
	private String name;
	private String image;
	private String auctionId;
	private String date;
	private int price;
	public Attend() {
		super();
	}
	public Attend(String attendId, String membersId, String name, String image, String auctionId, String date,
			int price) {
		super();
		this.attendId = attendId;
		this.membersId = membersId;
		this.name = name;
		this.image = image;
		this.auctionId = auctionId;
		this.date = date;
		this.price = price;
	}
	public String getAttendId() {
		return attendId;
	}
	public void setAttendId(String attendId) {
		this.attendId = attendId;
	}
	public String getMembersId() {
		return membersId;
	}
	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Attend [attendId=" + attendId + ", membersId=" + membersId + ", name=" + name + ", image=" + image
				+ ", auctionId=" + auctionId + ", date=" + date + ", price=" + price + "]";
	}
	
}
