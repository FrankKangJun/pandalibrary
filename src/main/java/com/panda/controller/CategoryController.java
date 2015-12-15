package com.panda.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.model.Category;
import com.panda.service.CategoryService;
import com.panda.tools.Constants;

@Controller
@RequestMapping("/category")
public class CategoryController {

	CategoryService categoryservice;
	
	@Autowired
	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}


	@RequestMapping("/getAllCategory")
	public @ResponseBody Map<String,Object> getAllCategory()
	{
		Map<String,Object>map = new HashMap<String,Object>();
		try {
			List<Category> category = new ArrayList<Category>();
			category = categoryservice.getAllCategory();
			if(category == null)
			{
				map.put(Constants.STATUS, Constants.FAILURE);
				map.put(Constants.MESSAGE, "获取category列表失败");
			}
			else
			{
				map.put("category",category);
				map.put(Constants.STATUS, Constants.SUCCESS);
				map.put(Constants.MESSAGE, "获取category成功");
			}			
		} catch (Exception e) {
			e.getStackTrace();
			map.put(Constants.STATUS, Constants.FAILURE);
			map.put(Constants.MESSAGE, "获取category列表失败");
		}
		return map;
	}
	
}
