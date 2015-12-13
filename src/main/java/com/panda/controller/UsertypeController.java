package com.panda.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Usertype;
import com.panda.service.UsertypeService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("usertype")
public class UsertypeController {
	UsertypeService usertypeservice;
	
	@Autowired
	public void setUsertypeservice(UsertypeService usertypeservice) {
		this.usertypeservice = usertypeservice;
	}
	
	/**
	 * 获取所有用户类型
	 * @return
	 */
	@RequestMapping("/getAllUsertype")
	public @ResponseBody Map<String,Object> getAllUsertype()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			List<Usertype> usertypes = new ArrayList<Usertype>();
			usertypes = usertypeservice.getAllUserType();
			if(usertypes.size()>0)
			{
				map.put("usertypes", usertypes);
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "获取所有用户类型成功");					
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "没有用户类型");
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取用户类型失败");
		}
		return map;
		
	}
	
	/**
	 * 添加一个用户类型
	 * @param typeId
	 * @param typeName
	 * @param maxBorrowNum
	 * @return
	 */
	@RequestMapping("/addAUsertype")
	public @ResponseBody Map<String,Object> addAUsertype(@RequestParam String typeId,@RequestParam String typeName,
			@RequestParam Integer maxBorrowNum) 
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try{
			
			paramMap.put("typeId", typeId);
			paramMap.put("typeName", typeName);
			paramMap.put("maxBorrowNum", maxBorrowNum);
			int flag = usertypeservice.addAUsertype(paramMap);
			if(flag == 1)
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "添加用户类型成功");	
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "添加用户类型失败");	
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "添加用户类型失败");				
		}
		
		return map;
	}
	
	
	/**
	 * 删除用户类型
	 * @param typeId
	 * @return
	 */
	@RequestMapping("/deleteAUsertype")
	public @ResponseBody Map<String,Object> deleteAUsertype(@RequestParam String typeId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		try { 
			paramMap.put("typeId",typeId);
			int flag = usertypeservice.deleteAUsertype(paramMap);
			if(flag == 1)
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "删除用户类型成功");	
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "删除用户类型失败");	
			}
			
		} 
		catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "删除用户类型失败");			
		}
		return map;
	}
	
	@RequestMapping("updateMaxBorrowNum")
	public @ResponseBody Map<String,Object> updateMaxBorrowNum(@RequestParam String typeId,
			@RequestParam Integer max)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			try {
				paramMap.put("typeId", typeId);
				paramMap.put("maxBorrowNum", max);
				int flag = usertypeservice.updateMaxBorrowNum(paramMap);
				if(flag == 1)
				{
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "更新用户类型成功");	
				}
				else
				{
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "更新用户类型失败");	
				}			
				
			} catch (Exception e) {
				e.getStackTrace();
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "更新用户类型失败");
			}
			return map;
		
		}

}
