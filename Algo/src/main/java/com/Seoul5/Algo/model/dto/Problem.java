package com.Seoul5.Algo.model.dto;

import java.util.Date;

public class Problem {
	private int pNum;//문제아이디
	private int grade; //평점
	
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Problem(int pNum, int grade) {
		super();
		this.pNum = pNum;
		this.grade = grade;
	}
	 
	
}
