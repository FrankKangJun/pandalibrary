package com.panda.controller;

import java.text.SimpleDateFormat;
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

import com.panda.model.User;
import com.panda.service.UserService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/user")
public class UserController {
	UserService userservice;

    @Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

    /**
     * 添加一个用户
     * @param userId
     * @param userName
     * @param password
     * @param age
     * @param email
     * @param sex
     * @param phone
     * @param usertype
     * @param categoryId
     * @return
     */
    @RequestMapping("/addAUser")
    public @ResponseBody Map<String,Object> addAUser(@RequestParam String userId,@RequestParam String userName,@RequestParam String password,
    		@RequestParam Byte age,@RequestParam String email,@RequestParam String sex,@RequestParam String phone,@RequestParam String usertype,String categoryId)

    {
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
    	
    		Date date = new Date();
			User user = new User();
			User user2 = new User();
			user2 = userservice.selectByPrimaryKey(userId);
			if (user2 == null)
			{
				user.setAge(age);
				user.setBalance((float) 0.0);
				user.setCategoryId(categoryId);
				user.setCreateTime(date);
				user.setEmail(email);
				user.setIsDelete("0");
				user.setLastVisit(date);
				user.setPassword(password);
				user.setRegisterTime(date);
				user.setSex(sex);
				user.setStatus("1");
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserType(usertype);
				user.setPhone(phone);
				int flag = userservice.insertSelective(user);
				System.out.println(new Date());
				if(flag == 1)
				{
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "用户添加成功");
				}
				else
				{
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "用户添加失败");
				}
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "用户id已存在");
			}
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "用户添加失败2");
		}
    	
    	return map;
    }
    

    /**
     * 获取所有用户的信息
     * @return
     */
	@RequestMapping("/getAllUser")
    public @ResponseBody Map<String,Object> getAllUser()
    {
    	Map<String,Object> map = new HashMap<String,Object>();
    	try {
    		List<User> users = new ArrayList<User>();
    		users = userservice.getAllUser();
    		if(users != null)
    		{
    			map.put("users", users);
    			map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE,"获取用户列表成功");
    		}
    		else
    		{
    			map.put(Constants.STATUS, Constants.FAILURE);
    			map.put(Constants.MESSAGE, "用户列表为空");
    		}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取用户列表失败");
		}
		return map;
    }    
	
	/**
	 * 删除一个用户
	 * @param userId
	 * @return
	 */
	
	@RequestMapping("/deleteAUser")
	public @ResponseBody Map<String,Object> deleteAUser(@RequestParam String userId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int flag = userservice.deleteAUser(userId);
			if(flag == 1)
			{
    			map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE,"删除用户成功");
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "删除用户失败");
			}
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "删除用户失败");
		}
		return map;
	}
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param userName
	 * @param userType
	 * @return
	 */
	@RequestMapping("/updateAUser")
	public @ResponseBody Map<String,Object> updateAUser(@RequestParam String userId,String userName,String userType)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			paramMap.put("userId",userId);
			paramMap.put("userName", userName);
			paramMap.put("userType", userType);
			if(userservice.updateUser(paramMap))
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE,"修改用户成功");
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "修改用户失败");
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "修改用户信息失败");
		}
		return map;
	}
	
	
	/**
	 * 充值
	 * @param userId
	 * @param money
	 * @return
	 */
	@RequestMapping("/deposit")
	public @ResponseBody Map<String,Object> deposit(@RequestParam String userId,@RequestParam Float money)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {User user = new User();
		user = userservice.selectByPrimaryKey(userId);
		if (user != null)
		{
			paramMap.put("userId", userId);
			paramMap.put("money", money);
			if(userservice.deposit(paramMap)){
				map.put(Constants.STATUS, Constants.SUCCESS);
    			map.put(Constants.MESSAGE,"充值成功");
    			user = userservice.selectByPrimaryKey(userId);
    			map.put("balance", user.getBalance());
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "充值失败");
			}
		}
		else
		{
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "用户id不存在");
		}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "充值失败2");
		}
		return map;
	}
	
	@RequestMapping("/getInfById")
	public @ResponseBody Map<String,Object> getInfById(@RequestParam String userId)
	{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		
		return map;
	}
}