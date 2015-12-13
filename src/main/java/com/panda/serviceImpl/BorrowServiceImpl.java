package com.panda.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.BorrowMapper;
import com.panda.model.Borrow;
import com.panda.service.BorrowService;

@Service("BorrowService")
public class BorrowServiceImpl implements BorrowService{

	@Autowired
	private BorrowMapper borrowMapper;

	public int insertSelective(Borrow borrow) {
		return borrowMapper.insertSelective(borrow);
	}
}
