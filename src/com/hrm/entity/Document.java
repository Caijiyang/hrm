package com.hrm.entity;

import java.sql.Timestamp;

public class Document {
    private int id;
    private String title;
    private String filePath;
    private String remark;
    private Timestamp createDate;
    private int userId;
    private User user;

    public Document() {
    }

    public Document(int id, String title, String filePath, String remark, int userId) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.remark = remark;
        this.userId = userId;
    }

    public Document(int id, String title, String filePath, String remark, Timestamp createDate, int userId, User user) {
        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.remark = remark;
        this.createDate = createDate;
        this.userId = userId;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
