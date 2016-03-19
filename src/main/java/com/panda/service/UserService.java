package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.model.ShortBooks;
import com.panda.model.User;

public interface UserService {
	public int insertSelective(User user);

	public List<User> getAllUser();

	public User selectByPrimaryKey(String userId);

	public int deleteAUser(String userId);

	public boolean updateUser(Map<String, Object> paramMap);

	public boolean deposit(Map<String, Object> paramMap);

	public List<ShortBooks> getShortBooks(Map<String, Object> paramMap);

	public void updateBalance(Map<String, Object> paramMap);

	public int getMaxBorrowNum(Map<String, Object> paramMap);

}