package com.hrm.servlet;

import com.hrm.entity.Job;
import com.hrm.entity.Page;
import com.hrm.service.JobService;
import com.hrm.service.impl.JobServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 代码提示的快捷键：Ctrl + Alt + 空格    等价于Eclipse中的：Alt + /
public class JobServlet extends HttpServlet {
    private JobService service = new JobServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中标识当前操作的参数
        String operation = req.getParameter("operation");
        switch (operation) {
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
            default:
                break;
        }
    }

    private void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建保存条件的map集合
        Map<String, String> condition = new HashMap<>();
        // 获取查询条件
        String name = req.getParameter("name");
        if (name != null && !name.equals("")) {
            condition.put("name", name);
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
        List<Job> jobs = service.selectPage(page, condition);
        // 将page加入到request域中，因为页面上需要显示分页的数据
        req.setAttribute("page", page);
        // 将条件携带回页面（查询条件的数据回显）
        req.setAttribute("name", name);
        // 将查询结果加入到request域中
        req.setAttribute("jobs", jobs);
        // 跳转到job.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("job/job.jsp").forward(req, resp);
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
            String name = req.getParameter("name");
            String remark = req.getParameter("remark");
            // 封装Job对象
            Job job = new Job(id, name, remark);
            // 执行更新
            int row = service.update(job);
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
            Job job = service.selectById(id);
            if (job == null) {
//                selectAll(req, resp);
                selectPage(req, resp);
            } else {
                // 将查询到的用户对象加入request域
                req.setAttribute("job", job);
                // 跳转到修改页面
                req.getRequestDispatcher("job/showUpdateJob.jsp").forward(req, resp);
            }
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有用户
        List<Job> jobs = service.selectAll();
        // 将查询结果加入到request域中
        req.setAttribute("jobs", jobs);
        // 跳转到job.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("job/job.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取页面传入的值
        String name = req.getParameter("name");
        String remark = req.getParameter("remark");
        // 将接收到的值封装成job对象
        Job job = new Job(name, remark);
        // 调用JobService中的方法实现新增
        int row = service.insert(job);
        if (row == 1) {
//            selectAll(req, resp);
            selectPage(req, resp);
//            req.getRequestDispatcher("job/job.jsp").forward(req, resp);
        } else {
            // 添加失败
            req.getRequestDispatcher("job/showAddJob.jsp").forward(req, resp);
        }
    }

}
