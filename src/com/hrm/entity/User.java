package com.hrm.entity;

import java.sql.Timestamp;

public class User {
    private int id;
    private String loginName;
    private String password;
    // 值1:管理员，值2:普通用户
    private String status;
    private Timestamp createDate;
    private String username;
    // 值1已进行人脸注册，值2未进行人脸注册
    private String isFace;
    // 生成代码的快捷键：Alt + Shift + S
    public User() {
    }

    public User(String loginName, String password, String status, String username) {
        this(0,loginName,password,status,username);
    }

    public User(int id, String loginName, String password, String status, String username) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.status = status;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsFace() {
        return isFace;
    }

    public void setIsFace(String isFace) {
        this.isFace = isFace;
    }
}
