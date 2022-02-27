package com.hrm.service.impl;

import com.hrm.dao.LevelDao;
import com.hrm.dao.impl.LevelDaoImpl;
import com.hrm.entity.Level;
import com.hrm.service.LevelService;

import java.util.List;

public class LevelServiceImpl implements LevelService {
    private LevelDao dao = new LevelDaoImpl();
    @Override
    public List<Level> selectAllIdAndRange() {
        return dao.selectAllIdAndRange();
    }
}
