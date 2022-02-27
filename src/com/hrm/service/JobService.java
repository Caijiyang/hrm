package com.hrm.service;

import com.hrm.entity.Job;
import com.hrm.entity.Page;

import java.util.List;
import java.util.Map;

public interface JobService {
    // 新增用户
    public int insert(Job job);
    // 更新用户
    public int update(Job job);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public Job selectById(int id);
    // 查询所有用户
    public List<Job> selectAll();

    // 带条件的分页查询
    public List<Job> selectPage(Page page, Map<String, String> condition);
    // 统计总行数的方法
    public int countRows(Map<String, String> condition);
    // 仅查询所有的职位ID和职位名称
    public List<Job> selectAllIdAndName();
}
