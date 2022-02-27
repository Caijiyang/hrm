package com.hrm.dao;

import com.hrm.entity.Level;

import java.util.List;

public interface LevelDao {
    public List<Level> selectAllIdAndRange();
}
