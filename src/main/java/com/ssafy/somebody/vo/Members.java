package com.ssafy.somebody.vo;

import org.springframework.web.multipart.MultipartFile;

public class Members {
	private String membersId;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String account;
	private String image;
	private String portfolio;
	private String about;
	private MultipartFile imageFile;
	private MultipartFile portfolioFile;
	private int rank;
	public Members() {
	}
	public Members(String membersId, String password, String name, String email, String address, String phone,
			String account, String image, String portfolio, String about, MultipartFile imageFile,
			MultipartFile portfolioFile, int rank) {
		super();
		this.membersId = membersId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.account = account;
		this.image = image;
		this.portfolio = portfolio;
		this.about = about;
		this.imageFile = imageFile;
		this.portfolioFile = portfolioFile;
		this.rank = rank;
	}
	public String getMembersId() {
		return membersId;
	}
	public void setMembersId(String membersId) {
		this.membersId = membersId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	public MultipartFile getPortfolioFile() {
		return portfolioFile;
	}
	public void setPortfolioFile(MultipartFile portfolioFile) {
		this.portfolioFile = portfolioFile;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "Members [membersId=" + membersId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", account=" + account + ", image=" + image
				+ ", portfolio=" + portfolio + ", about=" + about + ", imageFile=" + imageFile + ", portfolioFile="
				+ portfolioFile + ", rank=" + rank + "]";
	}
}