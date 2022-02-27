package com.hrm.service.impl;

import com.hrm.dao.NoticeDao;
import com.hrm.dao.UserDao;
import com.hrm.dao.impl.NoticeDaoImpl;
import com.hrm.dao.impl.UserDaoImpl;
import com.hrm.entity.Notice;
import com.hrm.entity.Page;
import com.hrm.entity.User;
import com.hrm.service.NoticeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao dao = new NoticeDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public int insert(Notice notice) {
        return dao.insert(notice);
    }

    @Override
    public int update(Notice notice) {
        return dao.update(notice);
    }

    @Override
    public int delete(int[] ids) {
        return dao.delete(ids);
    }

    @Override
    public Notice selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public List<Notice> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<Notice> selectPage(Page page, Map<String, String> condition) {
        Map<Integer,User> map = new HashMap();
        List<Notice> notices = dao.selectPage(page, condition);
        for (Notice notice : notices) {
            // 获取发布公告的用户的id
            int userId = notice.getUserId();
            if (map.containsKey(userId)){
                // 将查询得到的用户对象设置到公告中
                notice.setUser(map.get(userId));
            } else {
                // 根据id查询用户
                User user = userDao.selectById(userId);
                System.out.println("查询用户");
                // 将查询得到的用户对象设置到公告中
                notice.setUser(user);
                map.put(userId,user);
            }
        }
        return notices;
    }

    @Override
    public int countRows(Map<String, String> condition) {
        return dao.countRows(condition);
    }
}
