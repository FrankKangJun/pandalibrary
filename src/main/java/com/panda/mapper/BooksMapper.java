package com.panda.mapper;

import java.util.List;
import java.util.Map;

import com.panda.model.Books;

public interface BooksMapper {
    boolean deleteByPrimaryKey(String rank);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(String rank);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

	int changeStatus(Map<String, Object> paramMap);

	List<Books> getBooksById(Map<String, Object> paramMap);
}