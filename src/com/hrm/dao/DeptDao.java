package com.hrm.dao;

import com.hrm.entity.Page;
import com.hrm.entity.Dept;

import java.util.List;
import java.util.Map;

public interface DeptDao {
    // 新增用户
    public int insert(Dept dept);
    // 更新用户
    public int update(Dept dept);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public Dept selectById(int id);
    // 查询所有用户
    public List<Dept> selectAll();

    // 带条件的分页查询
    public List<Dept> selectPage(Page page, Map<String, String> condition);
    // 统计总行数的方法
    public int countRows(Map<String, String> condition);
    // 仅查询所有的部门ID和部门名称
    public List<Dept> selectAllIdAndName();
}
