package com.panda.mapper;

import java.util.List;
import java.util.Map;

import com.panda.model.Book;
import com.panda.model.ShortBook;

public interface BookMapper {
    void deleteByPrimaryKey(String bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

	List<ShortBook> selectAllBook();

	boolean addabook(Map<String, Object> paramMap);

	List<ShortBook> getBookById(String bookId);

	List<ShortBook> getBookByName(String bookName);

	List<ShortBook> getBookByPress(String press);

	boolean decreaseSurplus(String bookId);

	boolean updateABook(Map<String, Object> paramMap);

	String selectMaxBookId();
}