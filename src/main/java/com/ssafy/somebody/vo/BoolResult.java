package com.ssafy.somebody.vo;

import java.io.Serializable;

public class BoolResult implements Serializable{
	private String state;

	public BoolResult() {
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BoolResult [state=" + state + "]";
	}

  }