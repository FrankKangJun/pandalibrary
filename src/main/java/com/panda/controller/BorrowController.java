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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.panda.model.Book;
import com.panda.model.Borrow;
import com.panda.model.BorrowWithBookName;
import com.panda.model.Fine;
import com.panda.service.BookService;
import com.panda.service.BooksService;
import com.panda.service.BorrowService;
import com.panda.service.FineService;
import com.panda.service.UserService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
     private BorrowService borrowservice;
     private BookService bookservice;
     private FineService fineservice;
     private UserService userservice;
     private BooksService booksservice;
     
     
    @Autowired 
     public void setBooksservice(BooksService booksservice) {
		this.booksservice = booksservice;
	}


	@Autowired
    public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}


	@Autowired
    public void setFineservice(FineService fineservice) {
		this.fineservice = fineservice;
	}


	@Autowired
	public void setBorrowservice(BorrowService borrowservice) {
		this.borrowservice = borrowservice;
	}
    
    
    @Autowired
    public void setBookservice(BookService bookservice) {
		this.bookservice = bookservice;
	}


	/**
     * 借书列表
     * @return
     */
    @RequestMapping("/getAllBorrow")
    public @ResponseBody Map<String,Object> getAllBorrow()
    {
    	Map<String,Object>map = new HashMap<String,Object>();
    	try {
    		List<BorrowWithBookName> borrows = new ArrayList<BorrowWithBookName>();
    		borrows =borrowservice.getAllBorrow();
    		if(borrows == null)
    		{
    			map.put(Constants.STATUS, Constants.FAILURE);
    			map.put(Constants.MESSAGE, "借书列表为空");	
    		}
    		else
    		{
    			JSONArray jsonArray=JSON.parseArray(JSON.toJSONStringWithDateFormat(borrows, "yyyy-MM-dd"));
    			map.put("borrows",jsonArray);
    			map.put(Constants.STATUS, Constants.STATUS);
    			map.put(Constants.MESSAGE, "借书列表获取成功");	
    		}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取借书列表失败");			
		}
		return map;
    }
    
    /**
     * 根据bookname获取图书列表
     * @param bookName
     * @return
     */
    @RequestMapping("/getAllBorrowByBookName")
    public @ResponseBody Map<String,Object> getAllBorrowByBookName(String bookName)
    {
    	Map<String,Object>map = new HashMap<String,Object>();
    	Map<String,Object>paramMap = new HashMap<String,Object>();
    	try {
			String tag = bookName.trim();
			tag = "%"+tag+"%";
			List<BorrowWithBookName> borrows = new ArrayList<BorrowWithBookName>();
    		borrows =borrowservice.getAllBorrowByBookName(tag);
    		if(borrows == null)
    		{
    			map.put(Constants.STATUS, Constants.FAILURE);
    			map.put(Constants.MESSAGE, "借书列表为空");	
    		}
    		else
    		{
    			map.put("borrows",borrows);
    			map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE, "借书列表获取成功");	
    		}
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取借书列表失败");		
		}
		return map;
    }
    
//    @RequestMapping("/getBookById")
//    public @ResponseBody Map<String,Object> getBookById(@RequestParam String borrowId)
//    {
//    	Map<String,Object>map = new HashMap<String,Object>();
//    	Map<String,Object>paramMap = new HashMap<String,Object>();
//    	Borrow borrow = borrowservice.selectByPrimaryKey(borrowId);
//    	
//    	return map;
//    }
    
    @RequestMapping("getFine")
    public @ResponseBody Map<String,Object> getFine(@RequestParam String borrowId)
    {
    	Map<String,Object>map = new HashMap<String,Object>();
    	Map<String,Object>paramMap = new HashMap<String,Object>();
    	try {
    		Borrow borrow = new Borrow();
        	borrow = borrowservice.selectByPrimaryKey(borrowId);
        	if(borrow!=null)
        	{
        		Float fine =(float) 0.0;
    	        Date now = new Date();
    	    	Long intervalMilli = now.getTime() - borrow.getBorrowDate().getTime()+1;
    	    	if(intervalMilli>borrow.getTerm()*24*60*60*1000)
    	    	{
    	    		Book book = new Book();
    	    		String bookId = borrow.getBookId().substring(0, 5);
    	    		book = bookservice.selectBookById(bookId);
    	    		Fine fine1 =new Fine();
    	    		fine1 = fineservice.selectByBookType(book.getBookType());
    	    		int day=(int) (intervalMilli/24/60/60/1000-borrow.getTerm());
    	    		fine=fine1.getPrice()*day;   	    		
    	    	}
    	    	map.put("fine", fine);
    			map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE, "获取成功");	
        	}
        	else
        	{
        		map.put(Constants.STATUS, Constants.FAILURE);
    			map.put(Constants.MESSAGE, "此借阅id不存在");	
        	}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取Fine失败");	
		} 	
    	
    	
    	return map;
    }
    
    @RequestMapping("/returnBook")
    public @ResponseBody Map<String,Object> returnBook(@RequestParam String borrowId,@RequestParam Float fine)
    {
      	Map<String,Object>map = new HashMap<String,Object>();
    	Map<String,Object>paramMap = new HashMap<String,Object>();
    	try {
    		Borrow borrow = new Borrow(); 
    		borrow = borrowservice.selectByPrimaryKey(borrowId);
    		paramMap.put("rank", borrow.getBookId());
    		Date now = new Date();
    		paramMap.put("borrowId", borrowId);
    		paramMap.put("userId",borrow.getUserId());
    		paramMap.put("fine",fine);
    		paramMap.put("status", 0);
    		if(fine>0)
    		{
     			userservice.updateBalance(paramMap);
    		}
    		paramMap.put("returnDate", now);
    		borrowservice.updateBorrow(paramMap);
    		int flag = booksservice.changeStatus(paramMap);
    		map.put(Constants.STATUS,Constants.SUCCESS);
    		map.put(Constants.MESSAGE, "成功");
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "还书失败");
		}
    	
    	
    	return map;
    }
    
    
     
}
