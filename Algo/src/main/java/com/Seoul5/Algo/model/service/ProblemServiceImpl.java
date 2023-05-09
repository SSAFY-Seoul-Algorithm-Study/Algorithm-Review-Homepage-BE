package com.Seoul5.Algo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Seoul5.Algo.model.dao.ProblemDao;
import com.Seoul5.Algo.model.dto.Problem;
import com.Seoul5.Algo.model.dto.SearchCondition;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemDao pDao;

	@Override
	@Transactional
	public int insertProblem(Problem problem) {
		return pDao.insertProblem(problem);
	}

	@Override
	@Transactional
	public int updateProblem(Problem problem) {
		return pDao.updateProblem(problem);
	}

	@Override
	public Problem searchByNum(int pNum) {
		return pDao.searchByNum(pNum);
	}
	
	@Override
	public List<Problem> selectAll() {
		return pDao.selectAll();
	}
	
	 
	@Override
	public List<Problem> selectByCondition(SearchCondition searchCondition) {
		return pDao.selectByCondition(searchCondition);
	}

}
