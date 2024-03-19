package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;

import java.io.*;
import java.util.*;

/**
 * �û�Dao��
 */
public class UserDaoImpl implements UserDao {
    /**
     * ��Ӳ���ļ��ж�ȡ�ļ�
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
        //��������쳣���򷵻ؿն���
        return new ArrayList<>();
    }

    /**
     * ��ӵ�Ӳ�̣����г־û�����
     */
    @Override
    public void add(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //��ȡ�ļ��е�list����
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            if (list!=null){

                //��ȡlist���ı��
                User lastUser = list.get(list.size() - 1);
                //�����û����
                user.setId(lastUser.getId()+1);

                //��user������뵽List�У�Ȼ��listд�����ļ���
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
            throw new RuntimeException(e);//�쳣�׵������� RuntimeException������ʱ�쳣
        }finally {
            if (ois != null){
                try {
                    ois.close();//�ر���
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (oos!=null){
                try {
                    oos.close();//�ر���
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * �޸�
     * @param user
     */
    @Override
    public void updata(User user) {
        //��ȡ�ļ��е�list����
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            if(list!=null) {
                //��list����Ҫ�޸ĵ�����
                User originUser = list.stream().filter(u -> u.getId() == user.getId()).findFirst().get();//id����
                //�޸�����
                originUser.setName(user.getName());
                originUser.setMoney(user.getMoney());

                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);//�쳣�׵�������
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
     * ɾ���û�
     * @param id
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>)ois.readObject();
            //ʹ��stream������
            User user = list.stream().filter(u -> {
                return u.getId() == id;
            }).findFirst().get();
            //��list��ɾ��
            list.remove(user);
            //��listд���ļ���
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
        } catch (Exception e) {
            throw new RuntimeException(e);//�쳣�׵�������
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
     * �����û�
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

            //��״̬�޸�Ϊ����
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
