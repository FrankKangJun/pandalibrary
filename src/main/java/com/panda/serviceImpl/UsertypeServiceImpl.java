package com.panda.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.UsertypeMapper;
import com.panda.model.Usertype;
import com.panda.service.UsertypeService;

@Service("UsertypeService")
public class UsertypeServiceImpl implements UsertypeService{
 @Autowired
 UsertypeMapper usertypemapper;


public List<Usertype> getAllUserType() {
	return usertypemapper.getAllUserType();
}



public int addAUsertype(Map<String, Object> paramMap) {
	return usertypemapper.addAUsertype(paramMap);
}



public int deleteAUsertype(Map<String, Object> paramMap) {
	return usertypemapper.deleteAUsertype(paramMap);
}



public int updateMaxBorrowNum(Map<String, Object> paramMap) {
	return usertypemapper.updateMaxBorrowNum(paramMap);
}


 
  
}
