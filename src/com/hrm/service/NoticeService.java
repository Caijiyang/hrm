package com.hrm.service;

import com.hrm.entity.Notice;
import com.hrm.entity.Page;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    // 新增用户
    public int insert(Notice notice);
    // 更新用户
    public int update(Notice notice);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public Notice selectById(int id);
    // 查询所有用户
    public List<Notice> selectAll();

    // 带条件的分页查询
    public List<Notice> selectPage(Page page, Map<String, String> condition);
    // 统计总行数的方法
    public int countRows(Map<String, String> condition);
}
