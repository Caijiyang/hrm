package com.hrm.service.impl;

import com.hrm.dao.UserDao;
import com.hrm.dao.impl.UserDaoImpl;
import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(String loginname, String password) {
        return dao.login(loginname,password);
    }

    @Override
    public int insert(User user) {
        return dao.insert(user);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public User selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<User> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<User> selectPage(Page page, Map<String, String> condition) {
        return dao.selectPage(page,condition);
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }
}
