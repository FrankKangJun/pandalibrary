package com.panda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Admin;
import com.panda.service.AdminService;
import com.panda.service.UserService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/admin")
public class AdminController {
	AdminService adminService;
    
    @Autowired
	public void setAdminservice(AdminService adminservice) {
		this.adminService = adminservice;
	}	
	
	/*
	 * 管理员登录
	 * @param */

	@RequestMapping("/toLogin")
	public @ResponseBody Map<String, Object> toLogin(
			@RequestParam String id, @RequestParam String password,HttpServletRequest request)
	{
		Map<String, Object> map  = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (id != null && password != null
				&& !id.trim().equals("")
				&& !password.trim().equals(""))
		{
			paramMap.put("id", id);
			Admin admin = adminService.selectById(paramMap);
			if(admin!=null)
			{		
				if(password.equals(admin.getPassword()))
				{
					Date time  = new Date();
					paramMap.put("time",time);
					Integer flag = adminService.updateLastLoginTime(paramMap);					
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "登陆成功");
				}
				else {
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "账号或密码错误，请检查后输入");
				}
			}
			else {
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "账号不存在，请检查后输入");			
			}			
		}
		else {
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "账号或密码不能为空，请检查后输入");	
		}
		return map;
	}
	
}
