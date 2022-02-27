package com.hrm.service.impl;

import com.hrm.dao.DocumentDao;
import com.hrm.dao.impl.DocumentDaoImpl;
import com.hrm.entity.*;
import com.hrm.service.DeptService;
import com.hrm.service.DocumentService;
import com.hrm.service.JobService;
import com.hrm.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentServiceImpl implements DocumentService {
    private DocumentDao dao = new DocumentDaoImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public int insert(Document document) {
        return dao.insert(document);
    }

    @Override
    public int update(Document document) {
        return dao.update(document);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public Document selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Document> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Document> selectByIds(int[] ids) {
        return dao.selectByIds(ids);
    }

    @Override
    public List<Document> selectPage(Page page, Map<String, String> condition) {
        List<Document> documents = dao.selectPage(page, condition);
        Map<Integer,User> userMap = new HashMap<>();
        for (Document document : documents) {
            int userId = document.getUserId();
            if (userMap.containsKey(userId)){
                document.setUser(userMap.get(userId));
            } else {
                User user = userService.selectById(userId);
                userMap.put(userId,user);
                document.setUser(user);
            }
        }
        return documents;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }
}
