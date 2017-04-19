package com.szkingdom.ssm.service;

import com.szkingdom.ssm.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by tianf on 2016/10/29.
 */
public interface IUserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserList();
}
