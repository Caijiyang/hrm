package com.hrm.servlet;

import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.UserService;
import com.hrm.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 代码提示的快捷键：Ctrl + Alt + 空格    等价于Eclipse中的：Alt + /
public class UserServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中标识当前操作的参数
        String operation = req.getParameter("operation");
        switch (operation) {
            case "login":
                login(req, resp);
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
            default:
                break;
        }
    }

    private void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建保存条件的map集合
        Map<String, String> condition = new HashMap<>();
        // 获取查询条件
        String username = req.getParameter("username");
        String status = req.getParameter("status");
        if (username != null && !username.equals("")) {
            condition.put("username", username);
        }
        if (status != null && !status.equals("")) {
            condition.put("status", status);
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
        List<User> users = service.selectPage(page, condition);
        // 将page加入到request域中，因为页面上需要显示分页的数据
        req.setAttribute("page", page);
        // 将条件携带回页面（查询条件的数据回显）
        req.setAttribute("username", username);
        req.setAttribute("status", status);
        // 将查询结果加入到request域中
        req.setAttribute("users", users);
        // 跳转到user.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("user/user.jsp").forward(req, resp);
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
            String loginname = req.getParameter("loginname");
            String username = req.getParameter("username");
            String status = req.getParameter("status");
            // 封装User对象
            User user = new User(id, loginname, null, status, username);
            // 执行更新
            int row = service.update(user);
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
            User user = service.selectById(id);
            if (user == null) {
//                selectAll(req, resp);
                selectPage(req, resp);
            } else {
                // 将查询到的用户对象加入request域
                req.setAttribute("user", user);
                // 跳转到修改页面
                req.getRequestDispatcher("user/showUpdateUser.jsp").forward(req, resp);
            }
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有用户
        List<User> users = service.selectAll();
        // 将查询结果加入到request域中
        req.setAttribute("users", users);
        // 跳转到user.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("user/user.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取页面传入的值
        String username = req.getParameter("username");
        String status = req.getParameter("status");
        String loginname = req.getParameter("loginname");
        String password = req.getParameter("password");
        // 将接收到的值封装成user对象
        User user = new User(loginname, password, status, username);
        // 调用UserService中的方法实现新增
        int row = service.insert(user);
        if (row == 1) {
//            selectAll(req, resp);
            selectPage(req, resp);
//            req.getRequestDispatcher("user/user.jsp").forward(req, resp);
        } else {
            // 添加失败
            req.getRequestDispatcher("user/showAddUser.jsp").forward(req, resp);
        }
    }


    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 登录
        // 获取客户端传入的参数
        String loginname = req.getParameter("loginname");
        String password = req.getParameter("password");
        // 调用UserServiceImpl中的Service方法
        User user = service.login(loginname, password);
        // 判断user对象是否为null，如果登录名或密码输入错误时，则user对象应该为null
        if (user == null) {
            // 将登录名加入到request域中，回显到登录页（数据回显）
            req.setAttribute("loginname", loginname);
            req.setAttribute("errormsg", "用户名或密码错误！");
            // 登录失败后跳回到登录页
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            // 将登录的用户对象保存到session域中（保存登录状态）
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            // 查询所有用户
//            List<User> users = service.selectAll();
            Page page = new Page(1,2,service.countRows(null));
            List<User> users = service.selectPage(page,null);
            // 将查询结果加入到request域中
            req.setAttribute("users", users);
            // 登录成功后跳转到首页（main.jsp）
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
    }
}
