package com.hrm.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于连接数据库，执行增删改查的操作
 */
public class DBHelper {
    // 加载驱动
    // 创建连接（Connection对象）
    // 创建SQL语句执行器（PreparedStatement对象）
    // 查询：获取结果集（ResultSet对象）
    // 关闭资源

    // 加载驱动（只需要加载一次）
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接对象的方法
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hrm?characterEncoding=utf-8",
                "root", "root");
    }

    // 通用的更新（增删改）方法（增删改返回的都是影响的行数）
    // 参数一：insert或delete 或update的sql语句
    // 参数二：param用于填充sql语句中?站位符的参数
    public static int update(String sql, Object... param) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // 获取连接
            con = getConnection();
            // 获取SQL执行器  （ insert into 表名(类名...) values(?,?,...) ）
            ps = con.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < param.length; i++) {
                // 注意：占位符的下标是从1开始的
                // 参数一：?占位符的下标    参数二：实际值
                ps.setObject(i + 1, param[i]);
            }
            // 执行sql语句
            int row = ps.executeUpdate();
            return row;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(con, ps, null);
        }
        // 出现异常后，执行return -1;
        return -1;
    }

    // 通用的查询方法
    // select * from t where id=?
    public static List<Map<String, Object>> select(String sql, Object... param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 获取连接
            con = getConnection();
            // 获取SQL执行器  （ insert into 表名(类名...) values(?,?,...) ）
            ps = con.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < param.length; i++) {
                // 注意：占位符的下标是从1开始的
                // 参数一：?占位符的下标    参数二：实际值
                ps.setObject(i + 1, param[i]);
            }
            // 执行查询SQL并获取结果集
            rs = ps.executeQuery();
            // 由于一个map只能存储一行数据，如果结果集中有多行数据，那么就需要多个map对象，那么多个map对象要存储到一个List集合中
            // 最终返回List集合
            List<Map<String, Object>> list = new ArrayList<>();
            // 获取结果集中的数据
            while (rs.next()) {
                // 每循环一次，获取的是一行数据，即对应着一个map对象
                // 创建map对象
                Map<String, Object> map = new HashMap<>();
                // 获取元数据的对象
                ResultSetMetaData metaData = rs.getMetaData();
                // 循环获取每个列的列名和值
                // metaData.getColumnCount()获取列的个数
                // 列的下标从1开始
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    // 获取列名
                    // toLowerCase()：将列名的大写形式，转换为小写形式
                    String columnName = metaData.getColumnName(i).toLowerCase();
                    // 获取列的值（数据）
                    Object value = rs.getObject(i);
                    // 将列名作为key，列的值作为value存入map中
                    // 如果从数据库中取出的值，有可能是null值时，则需要进行null值判断
                    map.put(columnName,value == null ? "" : value);
                }
                // 将每个map都加入到list集合中
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(con, ps, rs);
        }
        // 如果try中的代码出现异常，则执行该return语句
        return null;
    }

    // 关闭所有资源的方法
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        // 先关后打开的资源
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
