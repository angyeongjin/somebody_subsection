package com.ssafy.somebody.vo;

public class Verify {
	private String virifyId;
	private String email;
	private String code;
	public Verify() { }
	public Verify(String virifyId, String email, String code) {
		super();
		this.virifyId = virifyId;
		this.email = email;
		this.code = code;
	}
	public String getVirifyId() {
		return virifyId;
	}
	public void setVirifyId(String virifyId) {
		this.virifyId = virifyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Verify [virifyId=" + virifyId + ", email=" + email + ", code=" + code + "]";
	}
	
}
