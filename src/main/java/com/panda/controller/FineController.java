package com.panda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Fine;
import com.panda.service.FineService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/fine")
public class FineController {
	private FineService fineservice;

	@Autowired
	public void setFineservice(FineService fineservice) {
		this.fineservice = fineservice;
	}

	@RequestMapping("/getAllFine")
	public @ResponseBody Map<String,Object> getAllFine()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<Fine> fine = new ArrayList<Fine>();
			fine = fineservice.getAllFine();
			map.put("fine", fine);
			map.put(Constants.STATUS, Constants.SUCCESS);
			map.put(Constants.MESSAGE, "获取罚款列表成功");	
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取罚款列表失败");			
		}
		return map;
	}
	
	@RequestMapping("/addAFine")
	public @ResponseBody Map<String,Object> addAFine(@RequestParam String fineId,
			@RequestParam String bookType,@RequestParam Float price)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Fine fine = new Fine();
			fine.setBookType(bookType);
			fine.setFineId(fineId);
			fine.setIsDelete("0");
			fine.setPrice(price);
			Fine haveFine = new Fine();
			haveFine = fineservice.selectByPrimaryKey(fineId);
			if(haveFine!=null)
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "fineId已存在");	
			}
			else
			{
				int flag = fineservice.addAFine(fine);
				if(flag == 1)
				{
					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "添加罚款规则成功");
				}
				else
				{
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "添加规则失败");
				}
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "添加规则失败");	
		}
		return map;
	}


	@RequestMapping("/deleteAFine")
	public @ResponseBody Map<String,Object> deleteAFine(@RequestParam String fineId)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int flag = fineservice.deleteAFine(fineId);
			if(flag==1)
			{
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "删除规则成功");
			}
			else
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "删除规则失败");	
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "删除规则失败");	
		}
		
		return map;
	}
	@RequestMapping("/updateFine")
	public @ResponseBody Map<String,Object> updateFine(@RequestParam String fineId,@RequestParam
			String price)
			{ 
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("fineId", fineId);
				paramMap.put("price", price);
				int flag = fineservice.updateFine(paramMap);
				if(flag == 1)
				{

					map.put(Constants.STATUS, Constants.SUCCESS);
					map.put(Constants.MESSAGE, "修改规则成功");
				}
				else
				{
					map.put(Constants.STATUS, Constants.FAILURE);
					map.put(Constants.MESSAGE, "删除规则失败");	
				}
				return map;
			}
}
