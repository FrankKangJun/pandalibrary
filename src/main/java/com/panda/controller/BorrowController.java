package com.panda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.BorrowWithBookName;
import com.panda.service.BorrowService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
     BorrowService borrowservice;

    @Autowired
	public void setBorrowservice(BorrowService borrowservice) {
		this.borrowservice = borrowservice;
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
    			map.put("borrows",borrows);
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
     
}
