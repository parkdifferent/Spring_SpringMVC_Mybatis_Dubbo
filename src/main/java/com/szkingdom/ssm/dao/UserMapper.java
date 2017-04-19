package com.szkingdom.ssm.dao;

import com.szkingdom.ssm.entity.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Cacheable("ssmCache")
    List<User> selectUserList();
}