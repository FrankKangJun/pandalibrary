package com.panda.mapper;

import java.util.Map;

public interface UserMapper {

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser( Map<String,Object>paramMap);

}