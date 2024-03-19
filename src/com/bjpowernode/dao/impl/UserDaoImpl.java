package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;

import java.io.*;
import java.util.*;

/**
 * 用户Dao层
 */
public class UserDaoImpl implements UserDao {
    /**
     * 从硬盘文件中读取文件
     * @return
     */
    @Override
    public List<User> select() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))){
            List<User> list = (List<User>)ois.readObject();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        //如果出现异常，则返回空对象
        return new ArrayList<>();
    }

    /**
     * 添加到硬盘，进行持久化操作
     */
    @Override
    public void add(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //读取文件中的list对象
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            if (list!=null){

                //获取list最大的编号
                User lastUser = list.get(list.size() - 1);
                //生成用户编号
                user.setId(lastUser.getId()+1);

                //将user对象放入到List中，然后将list写出到文件中
                list.add(user);

                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }else {
                list=new ArrayList<>();
                user.setId(0000);
                list.add(user);
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);//异常抛到控制器 RuntimeException：运行时异常
        }finally {
            if (ois != null){
                try {
                    ois.close();//关闭流
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (oos!=null){
                try {
                    oos.close();//关闭流
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void updata(User user) {
        //读取文件中的list数据
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            if(list!=null) {
                //从list查找要修改的数据
                User originUser = list.stream().filter(u -> u.getId() == user.getId()).findFirst().get();//id不变
                //修改数据
                originUser.setName(user.getName());
                originUser.setMoney(user.getMoney());

                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);//异常抛到控制器
        }finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            //使用stream流查找
            User user = list.stream().filter(u -> {
                return u.getId() == id;
            }).findFirst().get();
            //从list中删除
            list.remove(user);
            //将list写到文件中
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);//异常抛到控制器
        }finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 冻结用户
     * @param id
     */
    @Override
    public void frozer(int id) {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            User user = list.stream().filter(u -> u.getId() == id).findFirst().get();

            //将状态修改为冻结
            user.setStatus(Constant.USER_FROZEN);

            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
