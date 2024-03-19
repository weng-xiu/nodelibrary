package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.util.List;

/**
 * �û�����
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * ��ѯ
     * @return
     */
    @Override
    public List<User> select() {
        //����Dao�ķ�������List����
        return userDao.select();
    }

    /**
     * ���
     * @param user
     */
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    /**
     * �޸�
     * @param user
     */
    @Override
    public void updata(User user) {
        userDao.updata(user);
    }

    /**
     * ɾ��
     * @param id
     */
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    /**
     * ����
     * @param id
     */
    @Override
    public void frozer(int id) {
        userDao.frozer(id);
    }
}
