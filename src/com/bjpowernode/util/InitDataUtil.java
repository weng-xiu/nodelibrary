package com.bjpowernode.util;

import com.bjpowernode.bean.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InitDataUtil {
    public static void main(String[] args) {
        //initUser(null);
        //��ʼ���û�����
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"���", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH,userList);

        //��ʼ��ͼ������
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java��ͨ","����",Constant.TYPE_COMPUTER,"123-1","��е���̳�����",Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH,bookList);

        //��ʼ����������
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001,"���", Constant.USER_OK, BigDecimal.TEN);
        Book book = new Book(1, "java��ͨ", "����", Constant.TYPE_COMPUTER, "123-1", "��е���̳�����", Constant.STATUS_STORAGE);
        Lend lend = new Lend();
        //ʹ��UUID���ɱ��
        //String id = UUID.randomUUID().toString();

        lend.setId(UUID.randomUUID().toString());
        lend.setUser(user);
        lend.setBook(book);
        lend.setStatus(Constant.STATUS_LEND);
        LocalDate bigin = LocalDate.now();//��ǰʱ��
        lend.setLendDate(bigin);
        lend.setReturnDate(bigin.plusDays(30));//30���黹

        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);
    }

    /**
     * ��ʼ������
     * @param
     */
    public static void initData(String path,List<?> dataList){//���ͣ�ͨ���? List<?>
        ObjectOutputStream oos=null;
        //��������ļ�
        try {
            File directory = new File(path.split("/")[0]+"/");
            File file = new File(path);
            //�ж��ļ����Ƿ����
            if (!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()){
                file.createNewFile();
                //�����д�뵽�ļ���
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(dataList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * ��ʼ���û�����
     */
    public static void initUser(List<User> userList){
        //��������ļ���
        ObjectOutputStream oos = null;
        try {
            File directory = new File("user/");
            File file = new File(PathConstant.USER_PATH);
            //�ж��ļ����Ƿ����
            if (!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()){
                file.createNewFile();
                List<User> list = new ArrayList<>();
                //�ж�userList�Ƿ�Ϊ��
                if (userList==null){
                    list.add(new User(1001,"���", Constant.USER_OK, BigDecimal.TEN));
                }else {
                    list=userList;
                }
                //�����������list����д���ļ�user.txt
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (oos!=null){//�ر���
                try {
                    oos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * ��ʼ��book����
     * @param bookList
     */
    public static void initBook(List<Book> bookList){
        ObjectOutputStream oos=null;
        //��������ļ�
        try {
            File directory = new File("book/");
            File file = new File(PathConstant.BOOK_PATH);
            //�ж��ļ����Ƿ����
            if (!directory.exists()){
                directory.mkdir();
            }
            //�ж��ļ��Ƿ����
            if (!file.exists()){
                file.createNewFile();
                List<Book> list = new ArrayList<>();
                if (bookList==null){
                    list.add(new Book(1,"java��ͨ","����",Constant.TYPE_COMPUTER,"123-1","��е���̳�����",Constant.STATUS_STORAGE));
                }else {
                    list=bookList;
                }

                //�����д�뵽�ļ���
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
