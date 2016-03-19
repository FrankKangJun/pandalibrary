package com.panda.mapper;

import java.util.List;
import java.util.Map;

import com.panda.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User user);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> getAllUser();

	boolean updateUser(Map<String, Object> paramMap);

	boolean deposit(Map<String, Object> paramMap);

	void updateBalance(Map<String, Object> paramMap);

}