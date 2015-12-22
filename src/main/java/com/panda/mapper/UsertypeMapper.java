package com.panda.mapper;

import java.util.List;
import java.util.Map;

import com.panda.model.Usertype;

public interface UsertypeMapper {

	List<Usertype> getAllUserType();

	int addAUsertype(Map<String, Object> paramMap);

	int deleteAUsertype(Map<String, Object> paramMap);

	int updateMaxBorrowNum(Map<String, Object> paramMap);

	int getMaxBorrowNum(Map<String, Object> paramMap);

}
