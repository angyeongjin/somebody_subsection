package com.ssafy.somebody.vo;

public class Payment {
	private int paymentId;
	private String membersId;
	private String auctionId;
	private String tid;
	private String itemName;
	private String paymentMethodType;
	private int price;
	private String approvedAt;
	public Payment() { }
	public Payment(int paymentId, String membersId, String auctionId, String tid, String itemName,
			String paymentMethodType, int price, String approvedAt) {
		super();
		this.paymentId = paymentId;
		this.membersId = membersId;
		this.auctionId = auctionId;
		this.tid = tid;
		this.itemName = itemName;
		this.paymentMethodType = paymentMethodType;
		this.price = price;
		this.approvedAt = approvedAt;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getMembersId() {
		return membersId;
	}
	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPaymentMethodType() {
		return paymentMethodType;
	}
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(String approvedAt) {
		this.approvedAt = approvedAt;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", membersId=" + membersId + ", auctionId=" + auctionId + ", tid="
				+ tid + ", itemName=" + itemName + ", paymentMethodType=" + paymentMethodType + ", price=" + price
				+ ", approvedAt=" + approvedAt + "]";
	}

}
