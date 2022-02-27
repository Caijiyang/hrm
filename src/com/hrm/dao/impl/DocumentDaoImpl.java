package com.hrm.dao.impl;

import com.hrm.dao.DocumentDao;
import com.hrm.entity.Document;
import com.hrm.entity.Page;
import com.hrm.util.DBHelper;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentDaoImpl implements DocumentDao {

    @Override
    public int insert(Document document) {
        String sql = "insert into document_inf(title,filepath,remark,createdate,userId) " +
                "values(?,?,?,now(),?)";
        int row = DBHelper.update(sql,document.getTitle(),document.getFilePath(),document.getRemark(),document.getUserId());
        return row;
    }

    @Override
    public int update(Document document) {
        String sql  = "update document_inf set title=?,filepath=?,remark=? where id=?";
        int row = DBHelper.update(sql,document.getTitle(),document.getFilePath(),document.getRemark(),document.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        String sql = "delete from document_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public Document selectById(int id) {
        String sql = "select * from document_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<Document> documents = convert(list);
        if (documents.isEmpty()){
            return null;
        } else {
            return documents.get(0);
        }
    }

    @Override
    public List<Document> selectAll() {
        String sql = "select * from document_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<Document> documents = convert(list);
        return documents;
    }

    @Override
    public List<Document> selectByIds(int[] ids) {
        // select * from document_inf where id in(?,.....)
        String sql = "select * from document_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        List<Map<String, Object>> list = DBHelper.select(sql);
        return convert(list);
    }

    @Override
    public List<Document> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM document_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("title")){
                    sql += key + " LIKE '%" + value + "%' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        // 分页
        sql += " LIMIT ?,?";
        System.out.println(sql);
        // 调用通用的查询方法
        List<Map<String, Object>> list = DBHelper.select(sql, page.getStartIndex(), page.getPageRow());
        // 转换结果
        List<Document> documents = convert(list);
        return documents;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM document_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("title")){
                    sql += key + " LIKE '%" + value + "%' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        System.out.println(sql);
        List<Map<String, Object>> list = DBHelper.select(sql);
        return Integer.valueOf(list.get(0).get("count_num").toString());
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<Document> convert(List<Map<String, Object>> list){
        List<Document> documents = new ArrayList<>();
        for (Map<String, Object> map : list){
            Document document = new Document();
            document.setId(Integer.valueOf(map.get("id").toString()));
            document.setTitle(map.get("title").toString());
            document.setFilePath(map.get("filepath").toString());
            document.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
            document.setCreateDate((Timestamp) map.get("createdate"));
            document.setUserId(Integer.valueOf(map.get("userid").toString()));
            // 将创建的用户加入到集合中
            documents.add(document);
        }
        return documents;
    }
}
