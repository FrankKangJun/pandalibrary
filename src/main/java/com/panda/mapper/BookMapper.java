package com.panda.mapper;

import java.util.List;

import com.panda.model.Book;
import com.panda.model.ShortBook;

public interface BookMapper {
    int deleteByPrimaryKey(String bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

	List<ShortBook> selectAllBook();
}