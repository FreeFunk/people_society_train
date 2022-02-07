package com.edgedo.pay.wxpay.util.vo;

public class AccessTokenVo {
	//accessToken
	private String token;
	//accessToken的有效期
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
