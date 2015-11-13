package com.panda.mapper;

import java.util.Map;

import com.panda.model.Admin;

public interface AdminMapper {

	Admin selectById(Map<String, Object> paramMap);


}
