package com.hrm.dao;

import com.hrm.entity.Document;
import com.hrm.entity.Page;

import java.util.List;
import java.util.Map;

public interface DocumentDao {
    // 新增用户
    public int insert(Document document);
    // 更新用户
    public int update(Document document);
    // 删除用户
    public int delete(int[] ids);
    // 根据id查询用户
    public Document selectById(int id);
    // 查询所有用户
    public List<Document> selectAll();

    public List<Document> selectByIds(int[] ids);

    // 带条件的分页查询
    public List<Document> selectPage(Page page, Map<String, String> condition);
    // 统计总行数的方法
    public int countRows(Map<String, String> condition);
}
