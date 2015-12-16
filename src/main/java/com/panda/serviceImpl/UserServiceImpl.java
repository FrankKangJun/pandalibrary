package com.panda.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.BorrowMapper;
import com.panda.mapper.UserMapper;
import com.panda.model.ShortBooks;
import com.panda.model.User;
import com.panda.service.UserService;


@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BorrowMapper borrowMapper;


	public int insertSelective(User user) {
		return userMapper.insertSelective(user);
	}


	public List<User> getAllUser() {		
		return userMapper.getAllUser();
	}


	public User selectByPrimaryKey(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}


	public int deleteAUser(String userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}


	public boolean updateUser(Map<String, Object> paramMap) {
		return userMapper.updateUser(paramMap);
	}


	@Override
	public boolean deposit(Map<String, Object> paramMap) {
		return userMapper.deposit(paramMap);
	}


	@Override
	public List<ShortBooks> getShortBooks(Map<String, Object> paramMap) {
		return borrowMapper.getShortBooks(paramMap);
	}



}
