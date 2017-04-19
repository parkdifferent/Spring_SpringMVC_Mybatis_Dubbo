package com.szkingdom.ssm.entity;

public class Record {
    private Integer id;

    private String url;

    private String question;

    private String upvote;

    public Record(Integer id, String url, String question, String upvote) {
        this.id = id;
        this.url = url;
        this.question = question;
        this.upvote = upvote;
    }

    public Record() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getUpvote() {
        return upvote;
    }

    public void setUpvote(String upvote) {
        this.upvote = upvote == null ? null : upvote.trim();
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", question='" + question + '\'' +
                ", upvote='" + upvote + '\'' +
                '}';
    }
}