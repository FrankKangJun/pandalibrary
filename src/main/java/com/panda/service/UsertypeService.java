package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.model.Usertype;

public interface UsertypeService {

	List<Usertype> getAllUserType();

	int addAUsertype(Map<String, Object> paramMap);

	int deleteAUsertype(Map<String, Object> paramMap);

	int updateMaxBorrowNum(Map<String, Object> paramMap);

}
