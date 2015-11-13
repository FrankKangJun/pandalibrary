package com.panda.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.AdminMapper;
import com.panda.model.Admin;
import com.panda.service.AdminService;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
	@Autowired private AdminMapper adminMapper;
	
	public Admin selectById(Map<String, Object> paramMap)
	{
		return adminMapper.selectById(paramMap);
	}	
}
