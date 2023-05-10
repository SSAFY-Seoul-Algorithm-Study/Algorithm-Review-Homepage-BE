package com.Seoul5.Algo.model.service;

import java.util.List;

import com.Seoul5.Algo.model.dto.Review;

public interface ReviewService {

	int regist(Review reivew);

	List<Review> selectByP(int pNum);

	int deleteById(int id);

	int updateById(Review review);

	Review selectById(int id);

	List<Integer> selectByUser(String userId);

	List<Integer> selectOrderByDate();

	double averageRateByP(int pNum);
	
}
