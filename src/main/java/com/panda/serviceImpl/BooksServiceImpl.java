package com.panda.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.BooksMapper;
import com.panda.model.Books;
import com.panda.model.ShortBook;
import com.panda.service.BookService;
import com.panda.service.BooksService;

@Service("BooksService")
public class BooksServiceImpl implements BooksService{
	@Autowired 
	private BooksMapper booksmapper;

	@Override
	public Books selectByPrimaryKey(String bookId) {
		return booksmapper.selectByPrimaryKey(bookId);
	}

	@Override
	public int changeStatus(Map<String, Object> paramMap) {
		return booksmapper.changeStatus(paramMap);
	}

	@Override
	public void addAnExistingBook(Books books) {
		booksmapper.insertSelective(books);	
	}

	@Override
	public boolean deleteABook(String rank) {
		// TODO Auto-generated method stub
		return booksmapper.deleteByPrimaryKey(rank);
	}





}
