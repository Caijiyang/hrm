package com.hrm.service;

import com.hrm.entity.Employee;
import com.hrm.entity.LevelCountEmployee;
import com.hrm.entity.Page;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    // 新增用户
    public int insert(Employee employee);
    // 更新用户
    public int update(Employee employee);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public Employee selectById(int id);
    // 查询所有用户
    public List<Employee> selectAll();

    // 带条件的分页查询
    public List<Employee> selectPage(Page page, Map<String, String> condition);
    // 统计总行数的方法
    public int countRows(Map<String, String> condition);

    public List<LevelCountEmployee> countEmployee();
}
