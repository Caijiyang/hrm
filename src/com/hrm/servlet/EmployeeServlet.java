package com.hrm.servlet;

import com.hrm.entity.*;
import com.hrm.service.DeptService;
import com.hrm.service.EmployeeService;
import com.hrm.service.JobService;
import com.hrm.service.LevelService;
import com.hrm.service.impl.DeptServiceImpl;
import com.hrm.service.impl.EmployeeServiceImpl;
import com.hrm.service.impl.JobServiceImpl;
import com.hrm.service.impl.LevelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 代码提示的快捷键：Ctrl + Alt + 空格    等价于Eclipse中的：Alt + /
public class EmployeeServlet extends HttpServlet {
    private EmployeeService service = new EmployeeServiceImpl();
    private JobService jobService = new JobServiceImpl();
    private DeptService deptService = new DeptServiceImpl();
    private LevelService levelService = new LevelServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中标识当前操作的参数
        String operation = req.getParameter("operation");
        switch (operation) {
            case "add":
                add(req, resp);
                break;
            case "insert":
                insert(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "save":
                save(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "selectPage":
                selectPage(req, resp);
                break;
            case "selectAll":
                selectAll(req, resp);
                break;
            case "statistics":
                statistics(req, resp);
                break;
            default:
                break;
        }
    }

    private void statistics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<LevelCountEmployee> lces = service.countEmployee();
        req.setAttribute("lces",lces);
        req.getRequestDispatcher("employee/showEmployeeStatistics.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有的职位名
        List<Job> jobs = jobService.selectAllIdAndName();
        // 查询所有的部门名称
        List<Dept> depts = deptService.selectAllIdAndName();
        // 查询薪资范围
        List<Level> levels = levelService.selectAllIdAndRange();
        req.setAttribute("jobs", jobs);
        req.setAttribute("depts", depts);
        req.setAttribute("levels", levels);
        // 跳转到employee.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("employee/showAddEmployee.jsp").forward(req, resp);
    }

    private void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建保存条件的map集合
        Map<String, String> condition = new HashMap<>();
        // 获取查询条件
        String jobid = req.getParameter("jobid");
        String name = req.getParameter("name");
        String cardid = req.getParameter("cardid");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String depid = req.getParameter("depid");
        if (jobid != null && !jobid.equals("")) {
            condition.put("jobid", jobid);
        }
        if (name != null && !name.equals("")) {
            condition.put("name", name);
        }
        if (cardid != null && !cardid.equals("")) {
            condition.put("cardid", cardid);
        }
        if (sex != null && !sex.equals("")) {
            condition.put("sex", sex);
        }
        if (phone != null && !phone.equals("")) {
            condition.put("phone", phone);
        }
        if (depid != null && !depid.equals("")) {
            condition.put("depid", depid);
        }
        // 获取用户点击的页码
        String pageNumStr = req.getParameter("pageNum");
        // 判断前台传入的页码如果为空，则默认为第一页，否则转换传入的页码
        int pageNum = (pageNumStr == null || "".equals(pageNumStr)) ? 1 : Integer.valueOf(pageNumStr);
        pageNum = pageNum < 1 ? 1 : pageNum;
        // 查询总行数
        int totalRows = service.countRows(condition);
        // 每页显示的行数
        int pageRow = 2;
        // 创建page对象
        Page page = new Page(pageNum,pageRow,totalRows);
        // 执行分页条件查询
        List<Employee> employees = service.selectPage(page, condition);
        // 查询所有的职位名
        List<Job> jobs = jobService.selectAllIdAndName();
        // 查询所有的部门名称
        List<Dept> depts = deptService.selectAllIdAndName();

        // 将page加入到request域中，因为页面上需要显示分页的数据
        req.setAttribute("page", page);
        // 将条件携带回页面（查询条件的数据回显）
        req.setAttribute("jobid", jobid);
        req.setAttribute("name", name);
        req.setAttribute("cardid", cardid);
        req.setAttribute("sex", sex);
        req.setAttribute("phone", phone);
        req.setAttribute("depid", depid);
        // 将查询结果加入到request域中
        req.setAttribute("employees", employees);
        req.setAttribute("jobs", jobs);
        req.setAttribute("depts", depts);
        // 跳转到employee.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("employee/employee.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] str = req.getParameterValues("ids");
        if (str != null) {
            int[] ids = new int[str.length];
            // 将String[] 转换成 int[]
            for (int i = 0; i < str.length; i++) {
                ids[i] = Integer.valueOf(str[i]);
            }
            int row = service.delete(ids);
//            selectAll(req, resp);
            selectPage(req, resp);
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr == null || "".equals(idStr)) {
            selectAll(req, resp);
        } else {
            int id = Integer.valueOf(idStr);
            String depid = req.getParameter("depid");
            String jobid = req.getParameter("jobid");
            String name = req.getParameter("name");
            String cardid = req.getParameter("cardid");
            String address = req.getParameter("address");
            String postcode = req.getParameter("postcode");
            String tel = req.getParameter("tel");
            String phone = req.getParameter("phone");
            String qqnum = req.getParameter("qqnum");
            String email = req.getParameter("email");
            String sex = req.getParameter("sex");
            String party = req.getParameter("party");
            String birthday = req.getParameter("birthday");
            String race = req.getParameter("race");
            String education = req.getParameter("education");
            String speciality = req.getParameter("speciality");
            String hobby = req.getParameter("hobby");
            String remark = req.getParameter("remark");
            String levelid = req.getParameter("levelid");
            String salary = req.getParameter("salary");
            // 封装Employee对象
            Employee employee = new Employee();
            employee.setId(id);
            employee.setDepId(Integer.valueOf(depid));
            employee.setJobId(Integer.valueOf(jobid));
            employee.setName(name);
            employee.setCardId(cardid);
            employee.setAddress(address);
            employee.setPostCode(postcode);
            employee.setTel(tel);
            employee.setPhone(phone);
            employee.setQqNum(qqnum);
            employee.setEmail(email);
            employee.setSex(sex);
            employee.setParty(party);

            try {
                if (birthday != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(birthday);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
//                    LocalDateTime dateTime = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                    Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
                    employee.setBirthday(timestamp);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            employee.setRace(race);
            employee.setEducation(education);
            employee.setSpeciality(speciality);
            employee.setHobby(hobby);
            employee.setRemark(remark);
            employee.setLevelId(Integer.valueOf(levelid));
            employee.setSalary(Double.valueOf(salary));
            // 执行更新
            int row = service.update(employee);
//            selectAll(req, resp);
            selectPage(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 点击修改按钮，通过id查询用户信息，并将查询到的用户信息显示到修改页面中
        String idStr = req.getParameter("id");
        if (idStr == null || "".equals(idStr)) {
//            selectAll(req, resp);
            selectPage(req, resp);
        } else {
            int id = Integer.valueOf(idStr);
            Employee employee = service.selectById(id);
            if (employee == null) {
//                selectAll(req, resp);
                selectPage(req, resp);
            } else {
                // 查询所有的职位名
                List<Job> jobs = jobService.selectAllIdAndName();
                // 查询所有的部门名称
                List<Dept> depts = deptService.selectAllIdAndName();
                // 查询薪资范围
                List<Level> levels = levelService.selectAllIdAndRange();
                req.setAttribute("jobs", jobs);
                req.setAttribute("depts", depts);
                req.setAttribute("levels", levels);
                // 将查询到的用户对象加入request域
                req.setAttribute("employee", employee);
                // 跳转到修改页面
                req.getRequestDispatcher("employee/showUpdateEmployee.jsp").forward(req, resp);
            }
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有用户
        List<Employee> employees = service.selectAll();
        // 将查询结果加入到request域中
        req.setAttribute("employees", employees);
        // 跳转到employee.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("employee/employee.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取页面传入的值
        String depid = req.getParameter("depid");
        String jobid = req.getParameter("jobid");
        String name = req.getParameter("name");
        String cardid = req.getParameter("cardid");
        String address = req.getParameter("address");
        String postcode = req.getParameter("postcode");
        String tel = req.getParameter("tel");
        String phone = req.getParameter("phone");
        String qqnum = req.getParameter("qqnum");
        String email = req.getParameter("email");
        String sex = req.getParameter("sex");
        String party = req.getParameter("party");
        String birthday = req.getParameter("birthday");
        String race = req.getParameter("race");
        String education = req.getParameter("education");
        String speciality = req.getParameter("speciality");
        String hobby = req.getParameter("hobby");
        String remark = req.getParameter("remark");
        String levelid = req.getParameter("levelid");
        String salary = req.getParameter("salary");
        // 将接收到的值封装成employee对象
        Employee employee = new Employee();
        employee.setDepId(Integer.valueOf(depid));
        employee.setJobId(Integer.valueOf(jobid));
        employee.setName(name);
        employee.setCardId(cardid);
        employee.setAddress(address);
        employee.setPostCode(postcode);
        employee.setTel(tel);
        employee.setPhone(phone);
        employee.setQqNum(qqnum);
        employee.setEmail(email);
        employee.setSex(sex);
        employee.setParty(party);

        try {
            if (birthday != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(birthday);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
//                LocalDateTime dateTime = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
                employee.setBirthday(timestamp);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        employee.setRace(race);
        employee.setEducation(education);
        employee.setSpeciality(speciality);
        employee.setHobby(hobby);
        employee.setRemark(remark);
        employee.setLevelId(Integer.valueOf(levelid));
        employee.setSalary(Double.valueOf(salary));
        // 调用EmployeeService中的方法实现新增
        int row = service.insert(employee);
        if (row == 1) {
//            selectAll(req, resp);
            selectPage(req, resp);
//            req.getRequestDispatcher("employee/employee.jsp").forward(req, resp);
        } else {
            // 添加失败
            req.getRequestDispatcher("employee/showAddEmployee.jsp").forward(req, resp);
        }
    }

}
