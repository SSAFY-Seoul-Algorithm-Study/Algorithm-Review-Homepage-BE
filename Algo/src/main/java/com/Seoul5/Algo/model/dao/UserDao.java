package com.Seoul5.Algo.model.dao;

import java.util.List;

import com.Seoul5.Algo.model.dto.User;

public interface UserDao {

	int insert(User user);
	
	User selectById(String id);
	
	List<User> selectAll();

	List<User> searchById(String id);
	
	int update(User user);
	
	int weeklyUpdate(User user);

}
