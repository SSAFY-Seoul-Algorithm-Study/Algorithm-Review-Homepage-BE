package com.Seoul5.Algo.model.dto;

import java.time.LocalDateTime;

public class Review {
	private int id;
	private int pNum;
	private String userId;
	private String content;
	private int rate;
	LocalDateTime regDate;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	public Review(int pNum, String userId, String content, int rate) {
		// TODO Auto-generated constructor stub
	}
	public Review(int id, int pNum, String userId, String content, int rate) {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "Review [id=" + id + ", pNum=" + pNum + ", userId=" + userId + ", content=" + content + ", rate=" + rate
				+ "]";
	}
	
	
	
}
