package com.panda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		try{
			Book book = new Book();
			book = bookservice.selectBookById(bookId);
			map.put("book",book);
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "获取图书列表成功");

			
		}
		catch(Exception e)
		{
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取图书失败");
			
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
	    		paramMap.put("userId", userId);
	    		paramMap.put("typeId",user.getUserType());
	    		int borrowedNum = borrowservice.getBorrowedNum(paramMap);
	    		int maxBorrowNum = userservice.getMaxBorrowNum(paramMap);
	    		if(borrowedNum>=maxBorrowNum)
	    		{
	    			map.put(Constants.STATUS, Constants.FAILURE);
    				map.put(Constants.MESSAGE, "对不起，您已达最大借书数量上限");
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
		    			Book book = new Book();
		    			book = bookservice.selectBookById(books.getBookId());
		    			System.out.println(user.getUserType());
		    			if(user.getUserType().equals("00001")||(user.getUserType().equals("00002")&&(book.getBookType().equals("00002")||book.getBookType().equals("00003")))
		    					||book.getBookType().equals("00003")){  				
			    			
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
		    			else
		    			{
		    				map.put(Constants.STATUS, Constants.FAILURE);
		    				map.put(Constants.MESSAGE, "你无法借这个类型的书");
		    			}
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
	 * @param num
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/addAnExistingBook")
	public @ResponseBody Map<String,Object> addAnExistingBook(@RequestParam Integer num,
			@RequestParam String bookId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			paramMap.put("bookId", bookId);
			Book book = new Book();
			book = bookservice.selectBookById(bookId);
			if(num==0)
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "添加失败,请输入大于0的数量");
			}
			else
			{
				if(book == null)
				{
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "添加失败,该bookId不存在");	
				}
				else{
					Books books = new Books();
					books.setBookId(bookId);
					books.setIsDelete("0");
					for(int i = 0;i<num;i++)
					{
						if(bookservice.addabook(paramMap))
						{
							books.setRank(bookId+System.currentTimeMillis());
							booksservice.addAnExistingBook(books);
						}
						else
						{
							map.put(Constants.STATUS, Constants.SUCCESS);
							map.put(Constants.MESSAGE, "添加失败");	
						}
					}
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "添加成功");
				
				}
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
	public @ResponseBody Map<String,Object> addABook(@RequestParam Byte num
			,@RequestParam String bookName,@RequestParam String author
			,@RequestParam String press,@RequestParam String version,
			@RequestParam Short publicationYear,@RequestParam String bookType,
			@RequestParam Float price,@RequestParam String categoryId,
			@RequestParam String location,String introduction,
			String imgUrl)
			{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			
			if(num == 0)
			{
				
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "0本书添加个蛋蛋");		
			}
			else
			{
				String BookId = bookservice.selectMaxBookId();
				Integer id = new Integer(BookId);
				String bookId = String.format("%05d",id+1);
				Date date=new Date();
				Book book =new Book();
				Books books = new Books();								
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
				book.setSurplus(num);
				book.setVersion(version);
				books.setBookId(bookId);
				books.setIsDelete("0");
				books.setRank(bookId+System.currentTimeMillis());
				books.setStatus((byte) 0);
				int flag1 = bookservice.addABook(book);
				booksservice.addAnExistingBook(books);
				if(num>1)
				{
					for(int i = 1;i<num ;i++)
					{
						books.setRank(bookId+System.currentTimeMillis());
						booksservice.addAnExistingBook(books);
					}
				}
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
	public @ResponseBody Map<String,Object>updateABook(
			@RequestParam String bookId,
			@RequestParam String bookName,
			@RequestParam String author	,@RequestParam String press,@RequestParam String version,
			@RequestParam Short publicationYear,@RequestParam String bookType,
			@RequestParam Float price,@RequestParam String categoryId,
			@RequestParam String location,String introduction,
			String imgUrl)
			{
			Map<String,Object> map = new HashMap<String,Object>();
	Map<String,Object> paramMap = new HashMap<String,Object>();
	try {
			Book book =new Book();
			Books books = new Books();								
			book.setAuthor(author);
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setBookType(bookType);
			book.setCategoryId(categoryId);
			book.setImgUrl(imgUrl);
			book.setIntroduction(introduction);
			book.setLocation(location);
			book.setPress(press);
			book.setPrice(price);
			book.setPublicationYear(publicationYear);			
			book.setVersion(version);
            int flag = bookservice.updateByPrimaryKeySelective(book);
            if(flag == 1)
            {
            	map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE, "修改成功");
            }
            else
            {
        		map.put(Constants.STATUS, Constants.FAILURE);
        		map.put(Constants.MESSAGE, "添加失败");
            }			
		}		
	  catch (Exception e) {
		e.getStackTrace();
		map.put(Constants.STATUS, Constants.FAILURE);
		map.put(Constants.MESSAGE, "添加失败");		
	}
	
	return map;
		}
	
	@RequestMapping ("/getBooksById")
	public @ResponseBody Map<String,Object> getBooksById(@RequestParam String bookId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try {
			List<Books> books= new ArrayList<Books>();
			paramMap.put("bookId", bookId);
			books = bookservice.getBooksById(paramMap);
			map.put("books", books);
        	map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "失败");	
		}
		
		return map;
	}
	
}
