package com.panda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Book;
import com.panda.model.Books;
import com.panda.model.Borrow;
import com.panda.model.ShortBook;
import com.panda.model.User;
import com.panda.service.BookService;
import com.panda.service.BooksService;
import com.panda.service.BorrowService;
import com.panda.service.UserService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/book")
public class BookController {
	private BookService bookservice;
	private UserService userservice;
	private BooksService booksservice;
	private BorrowService borrowservice;

	@Autowired
	public void setBooksservice(BooksService booksservice) {
		this.booksservice = booksservice;
	}

	@Autowired
	public void setBorrowservice(BorrowService borrowservice) {
		this.borrowservice = borrowservice;
	}

	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	@Autowired
	public void setBookservice(BookService bookservice) {
		this.bookservice = bookservice;
	}

	@RequestMapping("/selectAllBook")
	public @ResponseBody Map<String,Object> selectAllBook()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<ShortBook> shortbook = new ArrayList<ShortBook>();
		try{
			shortbook = bookservice.selectAllBook();
			if(shortbook == null)
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "没有图书");
			}
			else
			{
				map.put("book",shortbook);
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "获取图书列表成功");
			}
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取图书列表失败");
			
		}	
		
		return map;
	}
	
	/**
	 * 借书
	 * @param bookId
	 * @param userId
	 * @return
	 */
	
	@RequestMapping("borrowABook")
	public @ResponseBody Map<String,Object> borrowABook(@RequestParam String bookId,@RequestParam String userId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
	    try {
	    	User user = new User();
	    	user = userservice.selectByPrimaryKey(userId);
	    	if(user == null)
	    	{
	    		map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "该用户id不存在");
	    	}
	    	else
	    	{
	    		Books books = new Books();
	    		books = booksservice.selectByPrimaryKey(bookId);
	    		if(books == null)
	    		{
	    			map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "该书不存在");
	    		}
	    		else if(books.getStatus() != 0)
	    		{
	    			map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "该书已借出或丢失");
	    		}
	    		else
	    		{
	    			paramMap.put("rank", bookId);
	    			paramMap.put("status", 1);
	    			int flag =  booksservice.changeStatus(paramMap);
	    			String borrowId; 
	    			borrowId=userId+System.currentTimeMillis();
	    			Borrow borrow = new Borrow();
	    			borrow.setBookId(books.getRank());
	    			borrow.setBorrowDate(new Date());
	    			borrow.setBorrowId(borrowId);
	    			borrow.setFine((float) 0.0);
	    			borrow.setIsDelete("0");
	    			borrow.setUserId(userId);
	    			borrow.setTerm((short) 60);
	    			int flag2 = borrowservice.insertSelective(borrow);
	    			if (flag2 == 1)
	    			{
	    				map.put(Constants.STATUS, Constants.SUCCESS);
	    				map.put(Constants.MESSAGE, "借书成功");
	    			}
	    			else
	    			{
	    				map.put(Constants.STATUS, Constants.FAILURE);
	    				map.put(Constants.MESSAGE, "借书失败");
	    			}
	    			
	    		}
	    	}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "借书失败");
		}
		return map;
	}
}
