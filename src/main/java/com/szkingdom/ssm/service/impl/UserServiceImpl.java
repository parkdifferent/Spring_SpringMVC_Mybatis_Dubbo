package com.szkingdom.ssm.service.impl;


import com.szkingdom.ssm.dao.UserMapper;
import com.szkingdom.ssm.entity.User;
import com.szkingdom.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianf on 2016/10/29.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @CachePut(value = "ssmCache", key = "#record.userId")
    public int insert(User record) {
        return userMapper.insert(record);
    }

    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Cacheable(value = "user", key = "#userId")
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @CachePut(value = "ssmCache", key = "#record.userId")
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    @CacheEvict(value = "ssmCache", allEntries = true)// 清除缓存  移除所有数据
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Cacheable(value = "ssmCache")
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    /*通常在查询数据时，进行缓存， 在增加、修改、删除数据时，清除缓存 ！*/
}
