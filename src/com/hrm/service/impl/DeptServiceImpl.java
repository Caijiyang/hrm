package com.hrm.service.impl;

import com.hrm.dao.DeptDao;
import com.hrm.dao.impl.DeptDaoImpl;
import com.hrm.entity.Page;
import com.hrm.entity.Dept;
import com.hrm.service.DeptService;

import java.util.List;
import java.util.Map;

public class DeptServiceImpl implements DeptService {
    private DeptDao dao = new DeptDaoImpl();

    @Override
    public int insert(Dept dept) {
        return dao.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        return dao.update(dept);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public Dept selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Dept> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Dept> selectPage(Page page, Map<String, String> condition) {
        return dao.selectPage(page,condition);
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }

    @Override
    public List<Dept> selectAllIdAndName() {
        return dao.selectAllIdAndName();
    }
}
