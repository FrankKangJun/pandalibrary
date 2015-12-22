package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.model.Book;
import com.panda.model.Books;
import com.panda.model.ShortBook;

public interface BookService {

	List<ShortBook> selectAllBook();

	Book selectBookById(String bookId);

	boolean addabook(Map<String, Object> paramMap);

	int addABook(Book book);

	List<ShortBook> getBookById(String bookId);

	List<ShortBook> getBookByName(String bookName);

	List<ShortBook> getBookByPress(String press);

	boolean decreaseSurplus(String bookId);

	void deleteABook(String bookId);

	boolean updateABook(Map<String, Object> paramMap);

	String selectMaxBookId();

	int updateByPrimaryKeySelective(Book book);

	List<Books> getBooksById(Map<String, Object> paramMap);



	

}
