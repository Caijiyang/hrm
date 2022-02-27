package com.hrm.test;

import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.UserService;
import com.hrm.service.impl.UserServiceImpl;

import java.util.List;

public class TestUserService {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        //  Page(int pageNum, int pageRow, int totalRow) {
        Page page = new Page(2,2,service.countRows(null));
        List<User> users = service.selectPage(page, null);
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getLoginName());
        }
    }
}
