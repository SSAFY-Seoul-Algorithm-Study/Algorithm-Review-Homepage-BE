package com.Seoul5.Algo.model.dao;

import java.util.List;

import com.Seoul5.Algo.model.dto.Review;

public interface ReviewDao {
	
	List<Review> selectByP(int pNum);

	int regist(Review review);

	int deleteById(int id);

	int updateById(Review review);

	Review selectById(int id);

	List<Integer> selectByUser(String userId);

	List<Integer> selectOrderByDate();

	double averageRateByP(int pNum);
	
}
