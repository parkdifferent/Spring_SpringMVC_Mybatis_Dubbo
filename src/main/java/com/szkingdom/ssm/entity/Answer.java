package com.szkingdom.ssm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 2946670160600229183L;

    private Long id;
    private String content;
    private String email;
    private Date createdTime;
    private Question question;

    /**
     * <association>元素用以描述“有一个”类型的关系，比如一个Answer对象会有一个对应的Question对象。

     answer表有一个question_id列，这一列是关联到一条question表的记录，通常这会是一个外键关联。

     对应Answer类中则是持有一个Question对象的引用：
     */

    public Answer() {
    }

    /**
     * <association>元素的作用就是在映射Answer的同时，把Answer内部的成员变量Question也一起映射。如何使用<association>呢？ 有两种方式：

     嵌套查询:通过执行另外一个SQL语句来映射关联类
     嵌套结果:使用嵌套结果来一次性映射联合查询
     */



    public Answer(Long id, String content, String email, Date createdTime, Question question) {
        this.id = id;
        this.content = content;
        this.email = email;
        this.createdTime = createdTime;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", createdTime=" + createdTime +
                ", question=" + question +
                '}';
    }
}
