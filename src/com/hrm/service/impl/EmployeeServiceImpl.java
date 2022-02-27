package com.hrm.service.impl;

import com.hrm.dao.EmployeeDao;
import com.hrm.dao.impl.EmployeeDaoImpl;
import com.hrm.entity.*;
import com.hrm.service.DeptService;
import com.hrm.service.EmployeeService;
import com.hrm.service.JobService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao dao = new EmployeeDaoImpl();
    private JobService jobService = new JobServiceImpl();
    private DeptService deptService = new DeptServiceImpl();

    @Override
    public int insert(Employee employee) {
        return dao.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return dao.update(employee);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public Employee selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Employee> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Employee> selectPage(Page page, Map<String, String> condition) {
        List<Employee> employees = dao.selectPage(page, condition);
        Map<Integer,Job> jobMap = new HashMap<>();
        Map<Integer,Dept> deptMap = new HashMap<>();
        for (Employee employee : employees) {
            int jobId = employee.getJobId();
            if (jobMap.containsKey(jobId)){
                employee.setJob(jobMap.get(jobId));
            } else {
                Job job = jobService.selectById(jobId);
                jobMap.put(jobId,job);
                employee.setJob(job);
            }

            int depId = employee.getDepId();
            if (deptMap.containsKey(depId)){
                employee.setDep(deptMap.get(depId));
            } else {
                Dept dept = deptService.selectById(depId);
                deptMap.put(depId,dept);
                employee.setDep(dept);
            }
        }
        return employees;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }

    @Override
    public List<LevelCountEmployee> countEmployee() {
        return dao.countEmployee();
    }
}
