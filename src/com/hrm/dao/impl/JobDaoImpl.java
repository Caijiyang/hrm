package com.hrm.dao.impl;

import com.hrm.dao.JobDao;
import com.hrm.entity.Job;
import com.hrm.entity.Page;
import com.hrm.util.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobDaoImpl implements JobDao {

    @Override
    public int insert(Job job) {
        String sql = "insert into job_inf(name,remark) values(?,?)";
        int row = DBHelper.update(sql,job.getName(),job.getRemark());
        return row;
    }

    @Override
    public int update(Job job) {
        String sql  = "update job_inf set name=?,remark=? where id=?";
        int row = DBHelper.update(sql,job.getName(),job.getRemark(),job.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        String sql = "delete from job_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public Job selectById(int id) {
        String sql = "select * from job_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<Job> jobs = convert(list);
        if (jobs.isEmpty()){
            return null;
        } else {
            return jobs.get(0);
        }
    }

    @Override
    public List<Job> selectAll() {
        String sql = "select * from job_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<Job> jobs = convert(list);
        return jobs;
    }

    @Override
    public List<Job> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM job_inf ";
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
        List<Job> jobs = convert(list);
        return jobs;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM job_inf ";
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
    public List<Job> selectAllIdAndName() {
        String sql = "select id,name from job_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        return convert(list);
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<Job> convert(List<Map<String, Object>> list){
        List<Job> jobs = new ArrayList<>();
        for (Map<String, Object> map : list){
            Job job = new Job();
            job.setId(Integer.valueOf(map.get("id").toString()));
            job.setName(map.get("name").toString());
            job.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
            // 将创建的用户加入到集合中
            jobs.add(job);
        }
        return jobs;
    }
}
