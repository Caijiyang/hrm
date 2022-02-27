package com.hrm.dao.impl;

import com.hrm.dao.DeptDao;
import com.hrm.entity.Page;
import com.hrm.entity.Dept;
import com.hrm.util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeptDaoImpl implements DeptDao {

    @Override
    public int insert(Dept dept) {
        String sql = "insert into dep_inf(name,remark) values(?,?)";
        int row = DBHelper.update(sql,dept.getName(),dept.getRemark());
        return row;
    }

    @Override
    public int update(Dept dept) {
        String sql  = "update dep_inf set name=?,remark=? where id=?";
        int row = DBHelper.update(sql,dept.getName(),dept.getRemark(),dept.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        String sql = "delete from dep_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public Dept selectById(int id) {
        String sql = "select * from dep_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<Dept> depts = convert(list);
        if (depts.isEmpty()){
            return null;
        } else {
            return depts.get(0);
        }
    }

    @Override
    public List<Dept> selectAll() {
        String sql = "select * from dep_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<Dept> depts = convert(list);
        return depts;
    }

    @Override
    public List<Dept> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM dep_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("name")){
                    sql += key + " LIKE '%" + value + "%' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        // 分页
        sql += " LIMIT ?,?";
        // 调用通用的查询方法
        List<Map<String, Object>> list = DBHelper.select(sql, page.getStartIndex(), page.getPageRow());
        // 转换结果
        List<Dept> depts = convert(list);
        return depts;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM dep_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("name")){
                    sql += key + " LIKE '%" + value + "%' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        List<Map<String, Object>> list = DBHelper.select(sql);
        return Integer.valueOf(list.get(0).get("count_num").toString());
    }

    @Override
    public List<Dept> selectAllIdAndName() {
        String sql = "select id,name from dep_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        return convert(list);
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<Dept> convert(List<Map<String, Object>> list){
        List<Dept> depts = new ArrayList<>();
        for (Map<String, Object> map : list){
            Dept dept = new Dept();
            dept.setId(Integer.valueOf(map.get("id").toString()));
            dept.setName(map.get("name").toString());
            dept.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
            // 将创建的用户加入到集合中
            depts.add(dept);
        }
        return depts;
    }
}
