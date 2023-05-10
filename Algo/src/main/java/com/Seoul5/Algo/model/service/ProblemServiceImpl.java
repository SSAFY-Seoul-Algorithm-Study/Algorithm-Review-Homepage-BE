package com.Seoul5.Algo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Seoul5.Algo.model.dao.ProblemDao;
import com.Seoul5.Algo.model.dto.Problem;
import com.Seoul5.Algo.model.dto.ProblemInfo;
import com.Seoul5.Algo.model.dto.SearchCondition;

@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemDao pDao;
	  
	@Override
	@Transactional
	public int insertProblem(Problem problem) { //문제 넣는 메서드
		return pDao.insertProblem(problem);
	}

	@Override
	@Transactional
	public int updateProblem(Problem problem) { //문제 평점 업데이트 메서드
		
		return pDao.updateProblem(problem);
	}

	@Override
	public Problem searchByNum(int pNum) { //문제번호로 문제찾기 메서드
		return pDao.searchByNum(pNum);
	}
	
	@Override
	public List<Problem> selectAll() { //모든 문제리스트정보 반환하는 메서드
		return pDao.selectAll();
	}
	 
	@Override
	public List<Problem> selectByCondition(SearchCondition searchCondition) { //검색조건으로 문제리스트 반환하는 메서드
		return pDao.selectByCondition(searchCondition);
	}

	@Override 
	public int insertProblemInfo(ProblemInfo problemInfo) { //해당하는 알고리즘 넣는 메서드
		return pDao.insertProblemInfo(problemInfo);
	}
	 

}
