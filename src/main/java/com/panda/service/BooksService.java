package com.panda.service;

import java.util.Map;

import com.panda.model.Books;

public interface BooksService {

	Books selectByPrimaryKey(String bookId);

	int changeStatus(Map<String, Object> paramMap);

	void addAnExistingBook(Books books);

	boolean deleteABook(String rank);


}
