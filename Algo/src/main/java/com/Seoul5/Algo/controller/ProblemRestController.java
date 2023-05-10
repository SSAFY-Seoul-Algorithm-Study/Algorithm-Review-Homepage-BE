package com.Seoul5.Algo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Seoul5.Algo.model.dto.Problem;
import com.Seoul5.Algo.model.dto.ProblemInfo;
import com.Seoul5.Algo.model.dto.SearchCondition;
import com.Seoul5.Algo.model.dto.User;
import com.Seoul5.Algo.model.service.ProblemService;
import com.Seoul5.Algo.model.service.ScoreService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/problemapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProblemRestController {

	@Autowired
	ProblemService ps;

	@Autowired
	ScoreService ss;

	@GetMapping("problem")
	@ApiOperation(value = "문제번호와 일치하는 문제정보를 반환한다.", response = Problem.class)
	public ResponseEntity<?> searchByNum(int pNum) {
		Problem p = ps.searchByNum(pNum);
		return new ResponseEntity<Problem>(p, HttpStatus.OK);
	}

	@PostMapping("problem")
	@ApiOperation(value = "새로운 문제 정보를 등록한다.", response = Integer.class)
	public ResponseEntity<?> insertProblem(Problem problem, String algo) {
//		ProblemInfo pInfo = new ProblemInfo(problem.getpNum(), algo);
//		ps.insertProblemInfo(problem); //알고리즘 등록
		
		int res = ps.insertProblem(problem);
		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}

	@PutMapping("problem")
	@ApiOperation(value = " 문제 정보를 수정 한다.", response = Integer.class)
	public ResponseEntity<?> updateProblem(Problem problem, ProblemInfo problemInfo) {
		// 해당하는 문제 무조건 있다고 생각, 그 전 로직(리뷰 등록 시)에서 체크한다
		// 업데이트,
		// 리뷰서비스에서 문제번호와 일치하는 리뷰 개수 가져온다
		int newGrade = problem.getGrade(); // ((가져온 리뷰개수*problem.getGrade() ) + 새로 넣을 평점 )/ (가져온리뷰개수+1), 
		Problem updateProblem  = new Problem(problem.getpNum(), newGrade ); 
		int res = ps.updateProblem(updateProblem);
		return new ResponseEntity<Integer>(res, HttpStatus.OK);
	}

	@GetMapping("problem")
	@ApiOperation(value = "조건에 맞는 일치하는 문제정보 리스트를 반환한다.", response = User.class)
	public ResponseEntity<?> selectByCondition(SearchCondition searchCondition) {
		List<Problem> list = ps.selectByCondition(searchCondition);
		return new ResponseEntity<List<Problem>>(list, HttpStatus.OK);
	}

}
