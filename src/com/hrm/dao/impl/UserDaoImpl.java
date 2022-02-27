package com.hrm.dao.impl;

import com.hrm.dao.UserDao;
import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.util.DBHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(String loginname, String password) {
        // 创建SQL语句
        String sql = "SELECT * FROM user_inf WHERE loginname=? AND PASSWORD=?";
        // 执行查询，并获得查询结果
        List<Map<String, Object>> list = DBHelper.select(sql, loginname, password);
        // 转换
        List<User> users = convert(list);
        // 判断users集合中是否有user对象
        if (users.isEmpty()){
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public int insert(User user) {
        String sql = "insert into user_inf(loginname,password,status,createdate,username,isface) values(?,?,?,now(),?,?)";
        int row = DBHelper.update(sql,user.getLoginName(),user.getPassword(),user.getStatus(),user.getUsername(),"2");
        return row;
    }

    @Override
    public int update(User user) {
        String sql  = "update user_inf set loginname=?,status=?,username=? where id=?";
        int row = DBHelper.update(sql,user.getLoginName(),user.getStatus(),user.getUsername(),user.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        // delete from user_inf where id=? or id=?....
        // delete from user_inf where id in(?,?,....,?,)
        String sql = "delete from user_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public User selectById(int id) {
        String sql = "select * from user_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<User> users = convert(list);
        if (users.isEmpty()){
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public List<User> selectAll() {
        String sql = "select * from user_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<User> users = convert(list);
        return users;
    }

    @Override
    public List<User> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM user_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("username")){
                    sql += key + " LIKE '%" + value + "%' and ";
                } else {
                    sql += key + " = '" + value + "' and ";
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
        List<User> users = convert(list);
        return users;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM user_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("username")){
                    sql += key + " LIKE '%" + value + "%' and ";
                } else {
                    sql += key + " = '" + value + "' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        List<Map<String, Object>> list = DBHelper.select(sql);
        return Integer.valueOf(list.get(0).get("count_num").toString());
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<User> convert(List<Map<String, Object>> list){
        List<User> users = new ArrayList<>();
        for (Map<String, Object> map : list){
            User user = new User();
            user.setId(Integer.valueOf(map.get("id").toString()));
            user.setLoginName(map.get("loginname").toString());
            user.setPassword(map.get("password").toString());
            user.setStatus(map.get("status").toString());
            user.setCreateDate((Timestamp) map.get("createdate"));
            user.setUsername(map.get("username").toString());
            user.setIsFace(map.get("isface").toString());
            // 将创建的用户加入到集合中
            users.add(user);
        }
        return users;
    }
}
