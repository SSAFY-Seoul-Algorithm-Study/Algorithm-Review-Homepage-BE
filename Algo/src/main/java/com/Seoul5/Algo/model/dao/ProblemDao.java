package com.Seoul5.Algo.model.dao;

import java.util.List;

import com.Seoul5.Algo.model.dto.Problem;
import com.Seoul5.Algo.model.dto.SearchCondition;

public interface ProblemDao {

	int insertProblem(Problem problem);

	int updateProblem(Problem problem);

	Problem searchByNum(int pNum);
	
	List<Problem> selectAll();
	
	List<Problem> selectByCondition(SearchCondition searchCondition);

}
