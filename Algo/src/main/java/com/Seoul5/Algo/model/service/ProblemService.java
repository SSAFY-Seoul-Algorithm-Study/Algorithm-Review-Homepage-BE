package com.Seoul5.Algo.model.service;

import java.util.List;

import com.Seoul5.Algo.model.dto.Problem;
import com.Seoul5.Algo.model.dto.SearchCondition;

public interface ProblemService {

	
	int insertProblem(Problem problem);

	int updateProblem(Problem problem);

	Problem searchByNum(int pNum);

	List<Problem> selectByCondition(SearchCondition searchCondition);

	List<Problem> selectAll();

}
