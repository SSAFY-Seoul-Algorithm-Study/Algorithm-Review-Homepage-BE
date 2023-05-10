package com.Seoul5.Algo.model.dao;

import com.Seoul5.Algo.model.dto.Problem;

public interface ProblemDao {

	int insertProblem(Problem problem);

	int updateProblem(Problem problem);

	Problem searchByNum(int pNum);
}
