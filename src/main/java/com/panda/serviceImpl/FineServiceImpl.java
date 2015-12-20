package com.panda.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.mapper.FineMapper;
import com.panda.model.Fine;
import com.panda.service.FineService;

@Service("FineService")
public class FineServiceImpl implements FineService {
	@Autowired
	private FineMapper fineMapper;

	@Override
	public List<Fine> getAllFine() {
		return fineMapper.getAllFine();
	}

	@Override
	public Fine selectByPrimaryKey(String fineId) {
		// TODO Auto-generated method stub
		return fineMapper.selectByPrimaryKey(fineId);
	}

	@Override
	public int addAFine(Fine fine) {
		return fineMapper.insertSelective(fine);
	}

	@Override
	public int deleteAFine(String fineId) {
		return fineMapper.deleteByPrimaryKey(fineId);
	}

	@Override
	public int updateFine(Map<String, Object> paramMap) {
		return fineMapper.updateFine(paramMap);
	}

	@Override
	public Fine selectByBookType(String bookType) {
		// TODO Auto-generated method stub
		return fineMapper.selectByBookType(bookType);
	}
	

}
