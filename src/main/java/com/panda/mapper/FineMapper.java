package com.panda.mapper;

import com.panda.model.Fine;

public interface FineMapper {
    int deleteByPrimaryKey(String fineId);

    int insert(Fine record);

    int insertSelective(Fine record);

    Fine selectByPrimaryKey(String fineId);

    int updateByPrimaryKeySelective(Fine record);

    int updateByPrimaryKey(Fine record);
}