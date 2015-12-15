package com.panda.service;

import java.util.List;

import com.panda.model.Borrow;
import com.panda.model.BorrowWithBookName;

public interface BorrowService {

	int insertSelective(Borrow borrow);

	List<BorrowWithBookName> getAllBorrow();

	List<BorrowWithBookName> getAllBorrowByBookName(String tag);

	

}
