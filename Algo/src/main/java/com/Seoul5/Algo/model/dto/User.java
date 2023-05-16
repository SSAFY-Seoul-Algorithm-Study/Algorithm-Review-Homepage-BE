package com.Seoul5.Algo.model.dto;

public class User {
	private String id;
	private String name;
	private String password;
	private String BOJid;
	private String email;
	private String img;
	private String orgImg;
	private int difficulty;
	// 1 브론즈까지 2 실버까지 3 골드...까지
	private String algo;
	// 추천문제에 어떤 알고리즘을 넣을지
	private boolean again;
	// 추천문제에 풀었던 문제도 나올지
	private int solvedCnt;
	// 총 푼 문제수 -> 이걸 주 단위로 갱신해서 주간 랭킹 볼 수 있게?
	private int week;
	// 개인이 설정한 주간 문제

	public User(String id, String name, String password, String bOJid, String email, String img, int solvedCnt,
			int week) {
		this.id = id;
		this.name = name;
		this.password = password;
		BOJid = bOJid;
		this.email = email;
		this.img = img;
		this.solvedCnt = solvedCnt;
		this.week = week;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBOJid() {
		return BOJid;
	}

	public void setBOJid(String bOJid) {
		BOJid = bOJid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public boolean isAgain() {
		return again;
	}

	public void setAgain(boolean again) {
		this.again = again;
	}

	public int getSolvedCnt() {
		return solvedCnt;
	}

	public void setSolvedCnt(int solvedCnt) {
		this.solvedCnt = solvedCnt;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

}
