package com.hrm.service;

import com.hrm.entity.Page;
import com.hrm.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    // 登录的方法
    public User login(String loginname, String password);
    // 新增用户
    public int insert(User user);
    // 更新用户
    public int update(User user);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public User selectById(int id);
    // 查询所有用户
    public List<User> selectAll();
    // 带条件的分页查询
    public List<User> selectPage(Page page, Map<String,String> condition);
    // 统计总行数的方法
    public int countRows(Map<String,String> condition);
}
