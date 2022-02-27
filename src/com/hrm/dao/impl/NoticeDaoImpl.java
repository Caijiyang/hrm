package com.hrm.dao.impl;

import com.hrm.dao.NoticeDao;
import com.hrm.entity.Notice;
import com.hrm.entity.Page;
import com.hrm.util.DBHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoticeDaoImpl implements NoticeDao {

    @Override
    public int insert(Notice notice) {
        String sql = "insert into notice_inf(title,content,createDate,userId) values(?,?,now(),?)";
        int row = DBHelper.update(sql,notice.getTitle(),notice.getContent(),notice.getUserId());
        return row;
    }

    @Override
    public int update(Notice notice) {
        String sql  = "update notice_inf set title=?,content=? where id=?";
        int row = DBHelper.update(sql,notice.getTitle(),notice.getContent(),notice.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        String sql = "delete from notice_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public Notice selectById(int id) {
        String sql = "select * from notice_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<Notice> notices = convert(list);
        if (notices.isEmpty()){
            return null;
        } else {
            return notices.get(0);
        }
    }

    @Override
    public List<Notice> selectAll() {
        String sql = "select * from notice_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<Notice> notices = convert(list);
        return notices;
    }

    @Override
    public List<Notice> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM notice_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                sql += key + " LIKE '%" + value + "%' and ";
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        // 分页
        sql += " LIMIT ?,?";
        // 调用通用的查询方法
        List<Map<String, Object>> list = DBHelper.select(sql, page.getStartIndex(), page.getPageRow());
        // 转换结果
        List<Notice> notices = convert(list);
        return notices;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM notice_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                sql += key + " LIKE '%" + value + "%' and ";
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        List<Map<String, Object>> list = DBHelper.select(sql);
        return Integer.valueOf(list.get(0).get("count_num").toString());
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<Notice> convert(List<Map<String, Object>> list){
        List<Notice> notices = new ArrayList<>();
        for (Map<String, Object> map : list){
            Notice notice = new Notice();
            notice.setId(Integer.valueOf(map.get("id").toString()));
            notice.setTitle(map.get("title").toString());
            notice.setContent(map.get("content").toString());
            notice.setCreateDate((Timestamp) map.get("createdate"));
            notice.setUserId(Integer.valueOf(map.get("userid").toString()));
            // 将创建的用户加入到集合中
            notices.add(notice);
        }
        return notices;
    }
}
