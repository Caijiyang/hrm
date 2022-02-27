package com.hrm.servlet;

import com.hrm.entity.Notice;
import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.NoticeService;
import com.hrm.service.impl.NoticeServiceImpl;

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
public class NoticeServlet extends HttpServlet {
    private NoticeService service = new NoticeServiceImpl();

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
            case "selectById":
                selectById(req, resp, "notice/previewNotice.jsp");
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

    private void selectById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        // 点击修改按钮，通过id查询用户信息，并将查询到的用户信息显示到修改页面中
        String idStr = req.getParameter("id");
        if (idStr == null || "".equals(idStr)) {
//            selectAll(req, resp);
            selectPage(req, resp);
        } else {
            int id = Integer.valueOf(idStr);
            Notice notice = service.selectById(id);
            if (notice == null) {
//                selectAll(req, resp);
                selectPage(req, resp);
            } else {
                // 将查询到的用户对象加入request域
                req.setAttribute("notice", notice);
                // 跳转到修改页面
                req.getRequestDispatcher(url).forward(req, resp);
            }
        }
    }

    private void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建保存条件的map集合
        Map<String, String> condition = new HashMap<>();
        // 获取查询条件
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title != null && !title.equals("")) {
            condition.put("title", title);
        }
        if (content != null && !content.equals("")) {
            condition.put("content", content);
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
        List<Notice> notices = service.selectPage(page, condition);
        // 将page加入到request域中，因为页面上需要显示分页的数据
        req.setAttribute("page", page);
        // 将条件携带回页面（查询条件的数据回显）
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        // 将查询结果加入到request域中
        req.setAttribute("notices", notices);
        // 跳转到notice.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("notice/notice.jsp").forward(req, resp);
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
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            // 封装Notice对象
            Notice notice = new Notice(id, title, content);
            // 执行更新
            int row = service.update(notice);
//            selectAll(req, resp);
            selectPage(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 点击修改按钮，通过id查询用户信息，并将查询到的用户信息显示到修改页面中
        selectById(req, resp, "notice/showUpdateNotice.jsp");
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 查询所有用户
        List<Notice> notices = service.selectAll();
        // 将查询结果加入到request域中
        req.setAttribute("notices", notices);
        // 跳转到notice.jsp页面（显示所有的用户信息）
        req.getRequestDispatcher("notice/notice.jsp").forward(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取页面传入的值
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        // 发布公告的用户就是当前登录的用户
        // 从session中获取当前登录的用户
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null){
            resp.sendRedirect("login.jsp");
        } else {
            // 将接收到的值封装成notice对象
            Notice notice = new Notice(title, content,user.getId());
            // 调用NoticeService中的方法实现新增
            int row = service.insert(notice);
            if (row == 1) {
//            selectAll(req, resp);
                selectPage(req, resp);
//            req.getRequestDispatcher("notice/notice.jsp").forward(req, resp);
            } else {
                // 添加失败
                req.getRequestDispatcher("notice/showAddNotice.jsp").forward(req, resp);
            }
        }
    }

}
