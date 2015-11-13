package com.panda.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Admin;
import com.panda.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	AdminService adminservice;

	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	
	
	/*
	 * 商家登录
	 * @param */
	
	@RequestMapping("/toLogin")
	public @ResponseBody Map<String, Object> toLogin(
			@RequestParam String id, @RequestParam String password)
	{
		Map<String, Object> map  = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (id != null && password != null
				&& !id.trim().equals("")
				&& !password.trim().equals(""))
		{
			paramMap.put("id", id);
			Admin admin = adminservice.selectById(paramMap);
		}
		
		
		return map;
	}
	
		

}
