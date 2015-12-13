package com.panda.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.BookMapper;
import com.panda.model.ShortBook;
import com.panda.service.BookService;

@Service("BookService")
public class BookServiceImpl implements BookService{
	@Autowired
	private	BookMapper bookmapper;


	public List<ShortBook> selectAllBook() {
		return bookmapper.selectAllBook();
	}

}
