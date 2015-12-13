package com.panda.mapper;

import com.panda.model.Borrow;

public interface BorrowMapper {
    int deleteByPrimaryKey(String borrowId);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(String borrowId);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);
}