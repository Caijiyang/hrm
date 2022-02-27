package com.hrm.servlet;

import com.hrm.entity.Dept;
import com.hrm.entity.Document;
import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.DocumentService;
import com.hrm.service.impl.DocumentServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// 代码提示的快捷键：Ctrl + Alt + 空格    等价于Eclipse中的：Alt + /
public class DocumentServlet extends HttpServlet {
    private DocumentService service = new DocumentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取operation
        String operation = req.getParameter("operation");
        // 如果包含上传文件时，则无法通过getParameter方法获取普通字段
        if (operation == null) {
            String uploadPath = "/upload/file/";
            // 获取当前项目的目录（项目在硬盘上的绝对路径）
            String path = req.getServletContext().getRealPath(uploadPath);
            File pathFile = new File(path);
            if (!pathFile.exists()) {
                // 如果指定的目录不存在，则创建目录
                pathFile.mkdirs();
            }
            // 上传文件
            // 创建DiskFileItemFactory
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建接收上传文件的对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            Map<String, String> param = new HashMap<>();
            try {
                // 解析请求
                List<FileItem> fileItems = upload.parseRequest(req);
                for (FileItem item : fileItems) {
                    // 获取上传文件的文件名
                    String fileName = item.getName();
                    if (fileName == null) {
                        // 如果获取的文件名为null，则说明当前循环到的元素不是文件，而是一个普通字段
                        // 获取字段名
                        String filedName = item.getFieldName();
                        // 获取字段的值
                        String value = item.getString();
                        // 将字段的名称和值加入map中
                        param.put(filedName, value);
                    } else {
                        if (fileName.equals("") || item.getSize() == -1) {
                            // 说明页面提交的表单中有file控件，但是页面上没有选择上传的文件
                            // 如果没有选择上传的文件
                            String id = param.get("id");
                            if (id == null || id.equals("")) {
                                selectPage(req, resp);
                            } else {
                                Document document = service.selectById(Integer.valueOf(id));
                                param.put("filepath", document.getFilePath());
                            }
                        } else {
                            // 获取到了文件名，则说明当前循环的元素是上传的文件
                            // 生成新的文件名
                            String newFileName = UUID.randomUUID().toString().replace("-", "");
                            // 截取后缀名（扩展名）
                            String extName = fileName.substring(fileName.lastIndexOf("."));
                            // 将后缀名拼接到新文件名中
                            newFileName += extName;
                            // 创建新文件
                            File newFile = new File(path, newFileName);
                            // 保存新文件
                            item.write(newFile);
                            // 拼接文件的URL
                            String filepath = uploadPath + newFileName;
                            // 加入map
                            param.put("filepath", filepath);
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            operation = param.get("operation");
            switch (operation) {
                case "insert":
                    insert(req, resp, param);
                    break;
                case "save":
                    save(req, resp, param);
                    break;
                default:
                    break;
            }


        } else {
            switch (operation) {
                case "update":
                    update(req, resp);
                    break;
                case "delete":
                    delete(req, resp);
                    break;
                case "selectPage":
                    selectPage(req, resp);
                    break;
                default:
                    break;
            }
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp, Map<String, String> param) throws ServletException, IOException {
        Document document = new Document();
        document.setId(Integer.valueOf(param.get("id")));
        document.setTitle(param.get("title"));
        document.setRemark(param.get("remark"));
        // 如果重新上传了文件，则filepath必须修改
        // 如果修改时没有重新上传文件，则filepath还要使用原来的文件地址
        document.setFilePath(param.get("filepath"));
        int row = service.update(document);
        selectPage(req, resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp, Map<String, String> param) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
        } else {
            Document document = new Document();
            document.setTitle(param.get("title"));
            document.setRemark(param.get("remark"));
            document.setFilePath(param.get("filepath"));
            document.setUserId(user.getId());
            int row = service.insert(document);
            if (row == 1) {
                selectPage(req, resp);
            } else {
                req.getRequestDispatcher("document/showAddDocument.jsp").forward(req, resp);
            }
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 点击修改按钮，通过id查询用户信息，并将查询到的用户信息显示到修改页面中
        String idStr = req.getParameter("id");
        if (idStr == null || "".equals(idStr)) {
            selectPage(req, resp);
        } else {
            int id = Integer.valueOf(idStr);
            Document doc = service.selectById(id);
            if (doc == null) {
                selectPage(req, resp);
            } else {
                // 将查询到的用户对象加入request域
                req.setAttribute("document", doc);
                // 跳转到修改页面
                req.getRequestDispatcher("document/showUpdateDocument.jsp").forward(req, resp);
            }
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] str = req.getParameterValues("ids");
        if (str != null) {
            int[] ids = new int[str.length];
            // 将String[] 转换成 int[]
            for (int i = 0; i < str.length; i++) {
                ids[i] = Integer.valueOf(str[i]);
            }
            // 查询出所有要被删除的Document对象
            List<Document> documents = service.selectByIds(ids);
            // 查询完成之后执行删除
            int row = service.delete(ids);
            if (row > 0) {
                // 获取当前项目的目录（项目在硬盘上的绝对路径）
                String path = req.getServletContext().getRealPath("");
                // 数据库中的数据删除成功
                // 循环删除上传的文件
                for (Document document : documents) {
                    File file = new File(path + document.getFilePath());
                    // 删除指定的文件
                    file.delete();
                }
            }
            selectPage(req, resp);
        }
    }

    private void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> condition = new HashMap<>();
        String title = req.getParameter("title");
        if (title != null && !"".equals(title)) {
            condition.put("title", title);
        }
        // 获取用户点击的页码
        String pageNumStr = req.getParameter("pageNum");
        // 判断前台传入的页码如果为空，则默认为第一页，否则转换传入的页码
        int pageNum = (pageNumStr == null || "".equals(pageNumStr)) ? 1 : Integer.valueOf(pageNumStr);
        pageNum = pageNum < 1 ? 1 : pageNum;
        // 每页显示的行数
        int pageRow = 2;
        // 统计总行数
        int totalRows = service.countRows(condition);
        // 创建page
        Page page = new Page(pageNum, pageRow, totalRows);
        List<Document> documents = service.selectPage(page, condition);
        req.setAttribute("title", title);
        req.setAttribute("page", page);
        req.setAttribute("documents", documents);
        req.getRequestDispatcher("document/document.jsp").forward(req, resp);
    }
}
