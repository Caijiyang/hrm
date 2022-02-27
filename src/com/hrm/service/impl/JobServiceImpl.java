package com.hrm.service.impl;

import com.hrm.dao.JobDao;
import com.hrm.dao.impl.JobDaoImpl;
import com.hrm.entity.Job;
import com.hrm.entity.Page;
import com.hrm.service.JobService;

import java.util.List;
import java.util.Map;

public class JobServiceImpl implements JobService {
    private JobDao dao = new JobDaoImpl();

    @Override
    public int insert(Job job) {
        return dao.insert(job);
    }

    @Override
    public int update(Job job) {
        return dao.update(job);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public Job selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Job> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Job> selectPage(Page page, Map<String, String> condition) {
        return dao.selectPage(page,condition);
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }

    @Override
    public List<Job> selectAllIdAndName() {
        return dao.selectAllIdAndName();
    }
}
