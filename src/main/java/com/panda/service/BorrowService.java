package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.model.Borrow;
import com.panda.model.BorrowWithBookName;

public interface BorrowService {

	int insertSelective(Borrow borrow);

	List<BorrowWithBookName> getAllBorrow();

	List<BorrowWithBookName> getAllBorrowByBookName(String tag);

	Borrow selectByPrimaryKey(String borrowId);

	void updateBorrow(Map<String, Object> paramMap);

	int getBorrowedNum(Map<String, Object> paramMap);

	

}
