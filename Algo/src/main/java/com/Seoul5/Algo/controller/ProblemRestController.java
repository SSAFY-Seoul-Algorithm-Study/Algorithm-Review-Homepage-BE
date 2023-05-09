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
import com.Seoul5.Algo.model.dto.SearchCondition;
import com.Seoul5.Algo.model.dto.User;
import com.Seoul5.Algo.model.service.ProblemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/problemapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProblemRestController {

	@Autowired
	ProblemService ps;
	
	
	@GetMapping("problem")
	@ApiOperation(value = "문제번호와 일치하는 문제정보를 반환한다.", response = User.class)
	public ResponseEntity<?> searchByNum(int pNum) { 
		Problem p = ps.searchByNum(pNum);
		return new ResponseEntity<Problem>(p, HttpStatus.OK);
	}
	
	@PostMapping("problem")
	@ApiOperation(value = "새로운 문제 정보를 등록, 수정 한다.", response = Integer.class)
	public ResponseEntity<?> insertProblem(Problem problem) {
		List<Problem> list = ps.selectAll();
		for(Problem pp : list) {
			if(pp.getpNum()==problem.getpNum()) {//이미 해당하는 문제 정보가 있다면,
				//업데이트,
				int res = ps.updateProblem(problem);
				return new ResponseEntity<Integer>(res, HttpStatus.OK);
			} 
		} 
		//새롭게 등록하는 문제라면 
		int res = ps.insertProblem(problem);
		return new ResponseEntity<Integer>(res, HttpStatus.OK); 
	}	
//	문제 등록과 동일한 로직인데 굳이 구분해야할까?
//	@PutMapping("problem") 
//	@ApiOperation(value = " 문제 정보를 수정 한다.", response = Integer.class)
//	public ResponseEntity<?> updateProblem(Problem problem) {
//		List<Problem> list = ps.selectAll();
//		for(Problem pp : list) {
//			if(pp.getpNum()==problem.getpNum()) {//이미 해당하는 문제 정보가 있다면,
//				//업데이트,
//				int res = ps.updateProblem(problem);
//				return new ResponseEntity<Integer>(res, HttpStatus.OK);
//			} 
//		} 
//		//새롭게 등록하는 문제라면 
//		int res = ps.insertProblem(problem);
//		return new ResponseEntity<Integer>(res, HttpStatus.OK); 
//	}
	
	@GetMapping("problem")
	@ApiOperation(value = "조건에 맞는 일치하는 문제정보 리스ㅌ를 반환한다.", response = User.class)
	public ResponseEntity<?> selectByCondition(SearchCondition searchCondition) { 
		List<Problem> list = ps.selectByCondition(searchCondition);
		return new ResponseEntity<List<Problem>>(list, HttpStatus.OK);
	}
	
	
}
