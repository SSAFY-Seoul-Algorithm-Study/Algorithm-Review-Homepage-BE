package com.Seoul5.Algo.model.dto;

import java.util.List;

public class ProblemInfo { //알고리즘 넣는 클래스
	private int pNum;
	private List<String> algo; //문제가 해당하는 알고리즘 카테고리

	public ProblemInfo(int pNum, List<String> algo) {
		this.pNum = pNum;
		this.algo = algo;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public List<String> getAlgo() {
		return algo;
	}

	public void setAlgo(List<String> algo) {
		this.algo = algo;
	}

}
