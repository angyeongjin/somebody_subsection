package com.ssafy.somebody.vo;

import org.springframework.web.multipart.MultipartFile;

public class Auction {
	private String auctionId;
	private String membersId;
	private String name;
	private String title;
	private String salesTime;
	private String deadline;
	private String contents;
	private String detail;
	private byte end;
	private MultipartFile detailFile;
	private String thumbnail;
	private MultipartFile thumbnailFile;
	private String tag;
	private byte online;
	private int maxPrice;
	public Auction() { }
	public Auction(String auctionId, String membersId, String name, String title, String salesTime, String deadline,
			String contents, String detail, byte end, MultipartFile detailFile, String thumbnail,
			MultipartFile thumbnailFile, String tag, byte online, int maxPrice) {
		super();
		this.auctionId = auctionId;
		this.membersId = membersId;
		this.name = name;
		this.title = title;
		this.salesTime = salesTime;
		this.deadline = deadline;
		this.contents = contents;
		this.detail = detail;
		this.end = end;
		this.detailFile = detailFile;
		this.thumbnail = thumbnail;
		this.thumbnailFile = thumbnailFile;
		this.tag = tag;
		this.online = online;
		this.maxPrice = maxPrice;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSalesTime() {
		return salesTime;
	}
	public void setSalesTime(String salesTime) {
		this.salesTime = salesTime;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public byte getEnd() {
		return end;
	}
	public void setEnd(byte end) {
		this.end = end;
	}
	public MultipartFile getDetailFile() {
		return detailFile;
	}
	public void setDetailFile(MultipartFile detailFile) {
		this.detailFile = detailFile;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public MultipartFile getThumbnailFile() {
		return thumbnailFile;
	}
	public void setThumbnailFile(MultipartFile thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public byte getOnline() {
		return online;
	}
	public void setOnline(byte online) {
		this.online = online;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", membersId=" + membersId + ", name=" + name + ", title=" + title
				+ ", salesTime=" + salesTime + ", deadline=" + deadline + ", contents=" + contents + ", detail="
				+ detail + ", end=" + end + ", detailFile=" + detailFile + ", thumbnail=" + thumbnail
				+ ", thumbnailFile=" + thumbnailFile + ", tag=" + tag + ", online=" + online + ", maxPrice=" + maxPrice
				+ "]";
	}
	
}
