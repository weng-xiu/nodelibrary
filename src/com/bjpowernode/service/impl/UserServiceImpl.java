package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.util.List;

/**
 * 用户服务
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 查询
     * @return
     */
    @Override
    public List<User> select() {
        //调用Dao的方法返回List即可
        return userDao.select();
    }

    /**
     * 添加
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void updata(User user) {
        userDao.updata(user);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    /**
     * 冻结
     * @param id
     */
    @Override
    public void frozer(int id) {
        userDao.frozer(id);
    }
}
