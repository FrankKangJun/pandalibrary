package com.panda.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.BookMapper;
import com.panda.mapper.BooksMapper;
import com.panda.model.Book;
import com.panda.model.Books;
import com.panda.model.ShortBook;
import com.panda.service.BookService;

@Service("BookService")
public class BookServiceImpl implements BookService{
	@Autowired
	private	BookMapper bookmapper;
	
	@Autowired 
	private	BooksMapper booksmapper;


	public List<ShortBook> selectAllBook() {
		return bookmapper.selectAllBook();
	}


	public Book selectBookById(String bookId) {
		return bookmapper.selectByPrimaryKey(bookId);
	}


	@Override
	public boolean addabook(Map<String, Object> paramMap) {
		return bookmapper.addabook(paramMap);
	}


	@Override
	public int addABook(Book book) {
		return bookmapper.insertSelective(book);
	}


	@Override
	public List<ShortBook> getBookById(String bookId) {
		return bookmapper.getBookById(bookId);
	}


	@Override
	public List<ShortBook> getBookByName(String bookName) {
		return bookmapper.getBookByName(bookName);
	}


	@Override
	public List<ShortBook> getBookByPress(String press) {
		return bookmapper.getBookByPress(press);
	}


	@Override
	public boolean decreaseSurplus(String bookId) {
		return bookmapper.decreaseSurplus(bookId);
	}


	@Override
	public void deleteABook(String bookId) {
		bookmapper.deleteByPrimaryKey(bookId);
		
	}


	@Override
	public boolean updateABook(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return bookmapper.updateABook(paramMap);
	}


	@Override
	public String selectMaxBookId() {
		// TODO Auto-generated method stub
		return bookmapper.selectMaxBookId();
	}


	@Override
	public int updateByPrimaryKeySelective(Book book) {
		// TODO Auto-generated method stub
		return bookmapper.updateByPrimaryKeySelective(book);
	}


	@Override
	public List<Books> getBooksById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return booksmapper.getBooksById(paramMap);
	}







}
