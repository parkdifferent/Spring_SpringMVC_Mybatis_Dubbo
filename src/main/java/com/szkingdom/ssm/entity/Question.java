package com.szkingdom.ssm.entity;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/4/24.
 */

@Table(name = "t_question")
public class Question implements Serializable {


    private static final long serialVersionUID = -7496967282411255056L;

    private Long id;
    private String title;
    private String description;
    private Date createdTime;
    private List<Tag> tags;
    private List<Answer> answers;

    /**
     * <collection>的作用类似于<association>，所不同的是，它描述的是“有多个”的关系。
     * 在将结果集映射Question时，同时映射生成List<Tag>和List<Answer>两个两个集合类型的成员变量，就需要在Mpper配置文件中使用<collection>元素了。
     * @return
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdTime=" + createdTime +
                ", tags=" + tags +
                ", answers=" + answers +
                '}';
    }
}
