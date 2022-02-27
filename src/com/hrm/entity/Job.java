package com.hrm.entity;

public class Job {
    private int id;
    private String name;
    private String remark;

    public Job() {
    }

    public Job(String name, String remark) {
        this(0,name,remark);
    }

    public Job(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
