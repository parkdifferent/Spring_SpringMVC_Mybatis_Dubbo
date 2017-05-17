package com.szkingdom.ssm.service;

import com.szkingdom.ssm.entity.Record;

import java.util.List;

/**
 * Created by tianf on 2016/10/31.
 */
public interface IRecordService {

    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> selectRecordList();

    void exportExcel(List<Record> list);
}
