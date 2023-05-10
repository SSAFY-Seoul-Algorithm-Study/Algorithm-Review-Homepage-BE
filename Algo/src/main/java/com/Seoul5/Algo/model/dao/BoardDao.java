package com.Seoul5.Algo.model.dao;

import com.Seoul5.Algo.model.dto.Board;
import com.Seoul5.Algo.model.dto.SearchCondition;

public interface BoardDao {
	
	int insertBoard(Board board);

	int deleteBoard(int bNum);

	Board selectByCondition(SearchCondition searchCondition);

	int updateBoard(Board board);
}
