package com.panda.mapper;

import java.util.List;

import com.panda.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

	List<Category> getAllCategory();
}