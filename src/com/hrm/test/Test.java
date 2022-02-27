package com.hrm.test;

import com.hrm.util.DBHelper;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        String sqlIns = "insert into user_inf(loginname,password,status,createdate,username,isface) values(?,?,?,now(),?,?)";
        // 执行新增
        DBHelper.update(sqlIns,"admin","abc123","1","zhangsan",2);

        String sql = "select * from user_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        for (Map<String, Object> map : list){
            // 从map中获取值的时候，使用key就是列名（注意：默认列名是大写形式）
            System.out.println(map.get("id"));
            System.out.println(map.get("loginname"));
            System.out.println(map.get("isface"));
        }

    }

    public void printfMassage(){
        System.out.println("happy");
    }

}
