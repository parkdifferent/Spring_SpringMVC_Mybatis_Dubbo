package com.szkingdom.ssm.dao;

import com.szkingdom.ssm.entity.Question;
import com.szkingdom.ssm.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by admin on 2017/4/24.
 */

@Mapper
public interface QuestionMapper  {

    Question findOne(@Param("id") Long id);

    void create(Question question);

    void update(Question question);

    void delete(@Param("id") Long id);

    List<Tag> findTags(@Param("id") Long id);
}
