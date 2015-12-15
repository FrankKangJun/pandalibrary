package com.panda.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.CategoryMapper;
import com.panda.model.Category;
import com.panda.service.CategoryService;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getAllCategory() {
		return categoryMapper.getAllCategory();
	}

}
