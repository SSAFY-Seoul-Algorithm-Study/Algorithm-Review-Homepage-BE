package com.Seoul5.Algo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Seoul5.Algo.model.dto.Review;
import com.Seoul5.Algo.model.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/reviewapi")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ReviewRestController {

	@Autowired
	ReviewService rs;
	
	// 내가 리뷰한 문제들
	// rate 기준 내림차순 정렬 후 반환
	@GetMapping("/problem/{userId}")
	@ApiOperation(value = "내가 리뷰한 문제들을 나타낸다.", notes="리뷰를 모두 나타낸다.")
	public ResponseEntity<?> selectByUser(@PathVariable String userId) {
		List<Integer> pNums = rs.selectByUser(userId);
		
		if(pNums == null || pNums.size() == 0) return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		List<double[]> list = new ArrayList<>();
		for(int p : pNums) {
			double[] arr = {p, rs.averageRateByP(p)};
			list.add(arr);
		}
		
		return new ResponseEntity<List<double[]>>(list, HttpStatus.OK);
	}
	
	// 사람들이 최근에 리뷰한 문제들 
	@GetMapping("/problem")
	@ApiOperation(value = "사람들이 최근에 리뷰한 문제들 ", notes="문제 번호와 평균 평점 반환. regDate기준 리뷰를 내림차순 정렬 후 DISTINCT로 중복을 제거한 문제들을 반환한다.")
	public ResponseEntity<?> selectOrderByDate() {
		List<Integer> pNums = rs.selectOrderByDate();
		
		if(pNums == null || pNums.size() == 0) return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		List<double[]> list = new ArrayList<>();
		for(int p : pNums) {
			double[] arr = {p, rs.averageRateByP(p)};
			list.add(arr);
		}
		
		return new ResponseEntity<List<double[]>>(list, HttpStatus.OK);
	}
	
	// pNum에 해당하는 모든 리뷰 반환
	// rate 기준 내림차순 후 content 기준 오름차순으로 정렬
	@GetMapping("/review/{pNum}")
	@ApiOperation(value = "문제의 리뷰를 모두 나타낸다.", notes="url에서 문제의 번호를 확인 후 해당하는 리뷰를 모두 나타낸다.")
	public ResponseEntity<?> selectByP(@PathVariable int pNum) {
		List<Review> reviews = rs.selectByP(pNum);
		
		if(reviews == null || reviews.size() == 0) return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
	}	
	
	// 쿠키가 없으면 404에러
	// 이전에 리뷰를 등록했으면 404에러
	@PostMapping("/review/{pNum}")
	@ApiOperation(value = "새로운 리뷰를 등록한다.", notes="내용과 평점을 입력하면 리뷰를 등록함. 로그인 유저 정보는 쿠키를 확인해서 가져옴. 문제번호는 url에서 가져옴.")
	public ResponseEntity<?> insert(@PathVariable int pNum, @CookieValue(value="userId") String userId, String content, int rate) {
		if(userId != null) {
			List<Review> reviews = rs.selectByP(pNum);
			
			if(reviews != null && reviews.size() != 0 ) {
				for(Review r : reviews) {
					if(r.getUserId().equals(userId)) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
			
				Review review = new Review(pNum, userId, content, rate);
				
				rs.regist(review);
				
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/review/{pNum}/{id}")
	@ApiOperation(value = "리뷰를 상세 조회한다.", notes="url을 통해 리뷰를 구분 후 조회.")
	public ResponseEntity<?> detailById(@PathVariable int pNum, @PathVariable int id) {
		Review review = rs.selectById(id);
		
		if(review != null) {
			return new ResponseEntity<Review>(review, HttpStatus.OK);	
		}
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	// 쿠키가 없으면 404에러
	// 자신이 작성한 리뷰만 지울 수 있다.
	@DeleteMapping("/review/{pNum}/{id}")
	@ApiOperation(value = "리뷰를 삭제한다.", notes="PathVariable을 통해 리뷰를 구분 후 삭제. 로그인 유저 정보는 쿠키를 확인해서 가져옴.")
	public ResponseEntity<?> delete(@PathVariable int pNum, @CookieValue(value="userId") String userId, @PathVariable int id) {
		if(userId != null) {
			Review review = rs.selectById(id);
			
			if(review != null && review.getUserId().equals(userId) && review.getpNum() == pNum) {
				rs.deleteById(id);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}	
	
	// 쿠키가 없으면 404에러
	// 자신이 작성한 리뷰만 수정할 수 있다.
	@PutMapping("/review/{pNum}/{id}")
	@ApiOperation(value = "리뷰를 수정한다.", notes="PathVariable을 통해 리뷰를 구분 후 수정. 로그인 유저 정보는 쿠키를 확인해서 가져옴.")
	public ResponseEntity<?> update(@PathVariable int pNum, @CookieValue(value="userId") String userId, @PathVariable int id, String content, int rate) {
		if(userId != null) {
			Review review = rs.selectById(id);
			
			if(review != null && review.getUserId() == userId) {
				Review newReview = new Review(id, pNum, userId, content, rate);
				rs.updateById(newReview);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
}
