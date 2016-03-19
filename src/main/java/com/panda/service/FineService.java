package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.model.Fine;

public interface FineService {

	List<Fine> getAllFine();

	Fine selectByPrimaryKey(String fineId);

	int addAFine(Fine fine);

	int deleteAFine(String fineId);

	int updateFine(Map<String, Object> paramMap);

	Fine selectByBookType(String bookType);

}
