package com.hrm.dao.impl;

import com.hrm.dao.LevelDao;
import com.hrm.entity.Level;
import com.hrm.util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LevelDaoImpl implements LevelDao {
    @Override
    public List<Level> selectAllIdAndRange() {
        String sql = "select id,`range` from level_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        return convert(list);
    }

    private List<Level> convert(List<Map<String, Object>> list){
        List<Level> levels = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Level level = new Level();
            level.setId(Integer.valueOf(map.get("id").toString()));
            level.setRange(map.get("range").toString());
            levels.add(level);
        }
        return levels;
    }
}
