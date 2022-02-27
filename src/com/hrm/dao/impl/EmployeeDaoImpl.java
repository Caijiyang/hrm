package com.hrm.dao.impl;

import com.hrm.dao.EmployeeDao;
import com.hrm.entity.Employee;
import com.hrm.entity.LevelCountEmployee;
import com.hrm.entity.Page;
import com.hrm.util.DBHelper;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public int insert(Employee employee) {
        String sql = "insert into employee_inf(depid,jobid,name,cardid,address,postcode,tel,phone,qqnum,email,sex,party,birthday," +
                "race,education,speciality,hobby,remark,createdate,levelid,salary) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?)";
        int row = DBHelper.update(sql,employee.getDepId(),employee.getJobId(),employee.getName(),employee.getCardId()
                ,employee.getAddress(),employee.getPostCode(),employee.getTel(),employee.getPhone(),employee.getQqNum(),employee.getEmail()
                ,employee.getSex(),employee.getParty(),employee.getBirthday(),employee.getRace(),employee.getEducation()
                ,employee.getSpeciality(),employee.getHobby(),employee.getRemark(),employee.getLevelId(),employee.getSalary());
        return row;
    }

    @Override
    public int update(Employee employee) {
        String sql  = "update employee_inf set depid=?,jobid=?,name=?,cardid=?,address=?,postcode=?,tel=?,phone=?,qqnum=?,email=?" +
                ",sex=?,party=?,birthday=?,race=?,education=?,speciality=?,hobby=?,remark=?,levelid=?,salary=? where id=?";
        int row = DBHelper.update(sql,employee.getDepId(),employee.getJobId(),employee.getName(),employee.getCardId()
                ,employee.getAddress(),employee.getPostCode(),employee.getTel(),employee.getPhone(),employee.getQqNum(),employee.getEmail()
                ,employee.getSex(),employee.getParty(),employee.getBirthday(),employee.getRace(),employee.getEducation()
                ,employee.getSpeciality(),employee.getHobby(),employee.getRemark(),employee.getLevelId(),employee.getSalary(),employee.getId());
        return row;
    }

    @Override
    public int delete(int[] ids) {
        String sql = "delete from employee_inf where id in(";
        for (int id : ids){
            sql += id + ",";
        }
        // 删除最后多出的逗号
        sql = sql.substring(0,sql.length()-1);
        sql += ")";
        return DBHelper.update(sql);
    }

    @Override
    public Employee selectById(int id) {
        String sql = "select * from employee_inf where id=?";
        List<Map<String, Object>> list = DBHelper.select(sql, id);
        List<Employee> employees = convert(list);
        if (employees.isEmpty()){
            return null;
        } else {
            return employees.get(0);
        }
    }

    @Override
    public List<Employee> selectAll() {
        String sql = "select * from employee_inf";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<Employee> employees = convert(list);
        return employees;
    }

    @Override
    public List<Employee> selectPage(Page page, Map<String, String> condition) {
        String sql = "SELECT * FROM employee_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("name")||key.equals("cardid")||key.equals("phone")){
                    sql += key + " LIKE '%" + value + "%' and ";
                } else {
                    sql += key + " = '" + value +"' and ";
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
        List<Employee> employees = convert(list);
        return employees;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        String sql = "SELECT count(1) count_num FROM employee_inf ";
        if (condition != null && !condition.isEmpty()){
            sql += "WHERE ";
            // 循环获取map中的条件key和value
            for (Map.Entry<String,String> entry : condition.entrySet()){
                // 列名
                String key = entry.getKey();
                // 条件值
                String value = entry.getValue();
                if (key.equals("name")||key.equals("cardid")||key.equals("phone")){
                    sql += key + " LIKE '%" + value + "%' and ";
                } else {
                    sql += key + " = '" + value +"' and ";
                }
            }
            // 删除多余的and
            sql = sql.substring(0,sql.length() - 4);
        }
        System.out.println(sql);
        List<Map<String, Object>> list = DBHelper.select(sql);
        return Integer.valueOf(list.get(0).get("count_num").toString());
    }

    @Override
    public List<LevelCountEmployee> countEmployee() {
        String sql = "SELECT emp.levelid,lev.range,COUNT(1) emp_num " +
                "FROM employee_inf emp INNER JOIN level_inf lev ON emp.levelid = lev.id " +
                "GROUP BY emp.levelid";
        List<Map<String, Object>> list = DBHelper.select(sql);
        List<LevelCountEmployee> lces = new ArrayList<>();
        for (Map<String, Object> map : list) {
            LevelCountEmployee lce = new LevelCountEmployee();
            lce.setId(Integer.valueOf(map.get("levelid").toString()));
            lce.setRang(map.get("range").toString());
            lce.setEmpCount(Integer.valueOf(map.get("emp_num").toString()));
            lces.add(lce);
        }
        return lces;
    }

    // 创建一个转换结果的方法，将查询结果中的map转换成表对应的实体类
    private List<Employee> convert(List<Map<String, Object>> list){
        List<Employee> employees = new ArrayList<>();
        for (Map<String, Object> map : list){
            Employee employee = new Employee();
            employee.setId(Integer.valueOf(map.get("id").toString()));
            employee.setDepId(Integer.valueOf(map.get("depid").toString()));
            employee.setJobId(Integer.valueOf(map.get("jobid").toString()));
            employee.setName(map.get("name") == null ? "" : map.get("name").toString());
            employee.setCardId(map.get("cardid") == null ? "" : map.get("cardid").toString());
            employee.setAddress(map.get("address") == null ? "" : map.get("address").toString());
            employee.setPostCode(map.get("postcode") == null ? "" : map.get("postcode").toString());
            employee.setTel(map.get("tel") == null ? "" : map.get("tel").toString());
            employee.setPhone(map.get("phone") == null ? "" : map.get("phone").toString());
            employee.setQqNum(map.get("qqnum") == null ? "" : map.get("qqnum").toString());
            employee.setEmail(map.get("email") == null ? "" : map.get("email").toString());
            employee.setSex(map.get("sex") == null ? "" : map.get("sex").toString());
            employee.setParty(map.get("party") == null ? "" : map.get("party").toString());
            employee.setBirthday((Timestamp) map.get("birthday"));
            employee.setRace(map.get("race") == null ? "" : map.get("race").toString());
            employee.setEducation(map.get("education") == null ? "" : map.get("education").toString());
            employee.setSpeciality(map.get("speciality") == null ? "" : map.get("speciality").toString());
            employee.setHobby(map.get("hobby") == null ? "" : map.get("hobby").toString());
            employee.setRemark(map.get("remark") == null ? "" : map.get("remark").toString());
            employee.setCreateDate((Timestamp) map.get("createdate"));
            employee.setLevelId(Integer.valueOf(map.get("levelid").toString()));
            employee.setSalary(Double.valueOf(map.get("salary").toString()));
            // 将创建的用户加入到集合中
            employees.add(employee);
        }
        return employees;
    }
}
