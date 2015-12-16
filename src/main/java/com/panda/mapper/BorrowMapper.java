package com.panda.mapper;

import java.util.List;
import java.util.Map;

import com.panda.model.Borrow;
import com.panda.model.BorrowWithBookName;
import com.panda.model.ShortBooks;

public interface BorrowMapper {
    int deleteByPrimaryKey(String borrowId);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(String borrowId);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

	List<BorrowWithBookName> getAllBorrow();

	List<BorrowWithBookName> getAllBorrowByBookName(String tag);

	List<ShortBooks> getShortBooks(Map<String, Object> paramMap);
}