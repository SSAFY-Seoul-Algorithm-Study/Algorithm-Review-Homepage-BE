package com.Seoul5.Algo.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Seoul5.Algo.model.dao.ReviewDao;
import com.Seoul5.Algo.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	private ReviewDao reviewDao;
	
	public ReviewDao getReviewRepo() { return reviewDao; }
	
	@Autowired
	public void setReviewRepo(ReviewDao dao) { this.reviewDao = dao; }

	@Override
	public int regist(Review review) {
		return reviewDao.regist(review);
	}

	@Override
	public List<Review> selectByP(int pNum) {
		return reviewDao.selectByP(pNum);
	}

	@Override
	public int deleteById(int id) {
		return reviewDao.deleteById(id);
	}

	@Override
	public int updateById(Review review) {
		return reviewDao.updateById(review);
	}

	@Override
	public Review selectById(int id) {
		return reviewDao.selectById(id);
	}

	@Override
	public List<Integer> selectByUser(String userId) {
		return reviewDao.selectByUser(userId);
	}

	@Override
	public List<Integer> selectOrderByDate() {
		return reviewDao.selectOrderByDate();
	}

	@Override
	public double averageRateByP(int pNum) {
		return reviewDao.averageRateByP(pNum);
	}
	
}
