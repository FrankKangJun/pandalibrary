package com.panda.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.UserMapper;
import com.panda.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public int insertUser(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(paramMap);
	}
}
