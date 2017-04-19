package com.szkingdom.ssm.service.impl;

import com.szkingdom.ssm.dao.RecordMapper;
import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianf on 2016/10/31.
 */

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Transactional(rollbackFor = Exception.class)
   /* 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)*/
    public int deleteByPrimaryKey(Integer id) {
        return recordMapper.deleteByPrimaryKey(id);
    }

    public int insert(Record record) {
        return recordMapper.insert(record);
    }

    public int insertSelective(Record record) {
        return recordMapper.insertSelective(record);
    }

    public Record selectByPrimaryKey(Integer id) {
        return recordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Record record) {
        return recordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Record record) {
        return recordMapper.updateByPrimaryKey(record);
    }

    @Transactional(propagation= Propagation.NOT_SUPPORTED,readOnly=true)
    /*在整个方法运行前就不会开启事务,这样就做成一个只读事务，可以提高效率。*/
    public List<Record> selectRecordList() {
        return recordMapper.selectRecordList();
    }
}
