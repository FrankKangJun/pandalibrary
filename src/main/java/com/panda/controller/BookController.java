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

	/**
	 * 获取所有图书
	 * @return
	 */
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
	 * 根据id获取图书
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/getBookById")
	public @ResponseBody Map<String,Object> getBookById(@RequestParam String bookId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<ShortBook> shortbook = new ArrayList<ShortBook>();
		try{
			shortbook = bookservice.getBookById(bookId);
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
	 * 根据书名获取图书
	 * @param bookName
	 * @return
	 */
	@RequestMapping("/getBookByName")
	public @ResponseBody Map<String,Object> getBookByName(@RequestParam String bookName)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<ShortBook> shortbook = new ArrayList<ShortBook>();
		try{
			bookName="%"+bookName+"%";
			shortbook = bookservice.getBookByName(bookName);
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
	 * 根据出版社获取图书
	 * @param press
	 * @return
	 */
	@RequestMapping("/getBookByPress")
	public @ResponseBody Map<String,Object> getBookByPress(@RequestParam String press)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<ShortBook> shortbook = new ArrayList<ShortBook>();
		try{
			press = "%"+press+"%";
			shortbook = bookservice.getBookByPress(press);
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
	
	/**
	 * 根据id查找book
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/selectBookById")
	public @ResponseBody Map<String,Object> selectBookById(@RequestParam String bookId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try{
			Book book = new Book();
			book = bookservice.selectBookById(bookId);
			if(book == null)
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "无此id的图书");
			}
			else
			{
				map.put("book",book);
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
	 * 添加一本已存在的书
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/addAnExistingBook")
	public @ResponseBody Map<String,Object> addAnExistingBook(@RequestParam String bookId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			paramMap.put("bookId", bookId);
			if(bookservice.addabook(paramMap))
			{
				Books books = new Books();
				books.setBookId(bookId);
				books.setIsDelete("0");
				books.setRank(bookId+System.currentTimeMillis());
				booksservice.addAnExistingBook(books);
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "添加成功");			
			}
			else
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "添加失败");	
			}
			
		} catch (Exception e) {
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "添加失败");			
		}
		
		return map;
	}
	
	/**
	 * 添加一本新书
	 * @param bookId
	 * @param bookName
	 * @param author
	 * @param press
	 * @param version
	 * @param publicationYear
	 * @param bookType
	 * @param price
	 * @param categoryId
	 * @param location
	 * @param introduction
	 * @param imgUrl
	 * @return
	 */
	@RequestMapping ("/addABook")
	public @ResponseBody Map<String,Object> addABook(@RequestParam String bookId,@RequestParam String bookName,@RequestParam String author
			,@RequestParam String press,@RequestParam String version,@RequestParam Short publicationYear,@RequestParam String bookType,
			@RequestParam Float price,@RequestParam String categoryId,@RequestParam String location,String introduction,
			String imgUrl)
			{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			Date date=new Date();
			Book book =new Book();
			Book havebook =new Book();
			Books books = new Books();
			havebook = bookservice.selectBookById(bookId);
			if(havebook != null)
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "添加失败,此bookId已存在");
			}
			else{					
				book.setAddTime(date);
				book.setAuthor(author);
				book.setBookId(bookId);
				book.setBookName(bookName);
				book.setBookType(bookType);
				book.setCategoryId(categoryId);
				book.setImgUrl(imgUrl);
				book.setIntroduction(introduction);
				book.setIsDelete("0");
				book.setLocation(location);
				book.setPress(press);
				book.setPrice(price);
				book.setPublicationYear(publicationYear);
				book.setSurplus((byte) 1);
				book.setVersion(version);
				books.setBookId(bookId);
				books.setIsDelete("0");
				books.setRank(bookId+System.currentTimeMillis());
				books.setStatus((byte) 0);
				int flag1 = bookservice.addABook(book);
				booksservice.addAnExistingBook(books);
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "添加成功");				
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "添加失败");		
		}
		
		return map;
			}


	/**
	 * 删除books
	 * @param rank
	 * @return
	 */
	@RequestMapping ("/deleteABook")
	public @ResponseBody Map<String,Object>deleteABook(@RequestParam String rank)
	{
		{
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			try {
				if(booksservice.deleteABook(rank))
				{
					String bookId=rank.substring(0, 5);
					if(bookservice.decreaseSurplus(bookId))
					{
						map.put(Constants.STATUS, Constants.SUCCESS);
						map.put(Constants.MESSAGE, "删除成功");				
					}
					else{					
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "数量减失败");	
					}
					Book book =new Book();
					book = bookservice.selectBookById(bookId);
					if (book.getSurplus()<=0)
					{
						bookservice.deleteABook(bookId);
					}
				}
				
			} catch (Exception e) {
				e.getStackTrace();
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "删除失败");	
			}
			
			return map;
		}
	}
	
	@RequestMapping ("/updateABook")
	public @ResponseBody Map<String,Object>updateABook(@RequestParam String bookId,String bookName,String press)
	{
		
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			paramMap.put("bookId", bookId);
			paramMap.put("bookName", bookName);
			paramMap.put("press", press);
			if(bookservice.updateABook(paramMap))
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "修改成功");	
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "修改失败");
			}
			
			
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "修改失败");	
		}
		return map;

		
	}
}
