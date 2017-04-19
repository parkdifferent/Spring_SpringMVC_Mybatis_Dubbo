package com.szkingdom.ssm.dao;

import com.szkingdom.ssm.entity.Record;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
    @Cacheable("ssmCache")
    List<Record> selectRecordList();
}