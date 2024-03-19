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
        //初始化用户数据
        List<User> userList = new ArrayList<>();
        userList.add(new User(1001,"李浩", Constant.USER_OK, BigDecimal.TEN));
        initData(PathConstant.USER_PATH,userList);

        //初始化图书数据
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1,"java精通","张三",Constant.TYPE_COMPUTER,"123-1","机械工程出版社",Constant.STATUS_STORAGE));
        initData(PathConstant.BOOK_PATH,bookList);

        //初始化借阅数据
        List<Lend> lendList = new ArrayList<>();
        User user = new User(1001,"李浩", Constant.USER_OK, BigDecimal.TEN);
        Book book = new Book(1, "java精通", "张三", Constant.TYPE_COMPUTER, "123-1", "机械工程出版社", Constant.STATUS_STORAGE);
        Lend lend = new Lend();
        //使用UUID生成编号
        //String id = UUID.randomUUID().toString();

        lend.setId(UUID.randomUUID().toString());
        lend.setUser(user);
        lend.setBook(book);
        lend.setStatus(Constant.STATUS_LEND);
        LocalDate bigin = LocalDate.now();//当前时间
        lend.setLendDate(bigin);
        lend.setReturnDate(bigin.plusDays(30));//30天后归还

        lendList.add(lend);

        initData(PathConstant.LEND_PATH,lendList);
    }

    /**
     * 初始化数据
     * @param
     */
    public static void initData(String path,List<?> dataList){//泛型，通配符? List<?>
        ObjectOutputStream oos=null;
        //创建相关文件
        try {
            File directory = new File(path.split("/")[0]+"/");
            File file = new File(path);
            //判断文件夹是否存在
            if (!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if (!file.exists()){
                file.createNewFile();
                //输出流写入到文件中
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(dataList);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 初始化用户数据
     */
    public static void initUser(List<User> userList){
        //创建相关文件夹
        ObjectOutputStream oos = null;
        try {
            File directory = new File("user/");
            File file = new File(PathConstant.USER_PATH);
            //判断文件夹是否存在
            if (!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if (!file.exists()){
                file.createNewFile();
                List<User> list = new ArrayList<>();
                //判断userList是否为空
                if (userList==null){
                    list.add(new User(1001,"李浩", Constant.USER_OK, BigDecimal.TEN));
                }else {
                    list=userList;
                }
                //对象输出流将list数据写到文件user.txt
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
                oos.writeObject(list);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (oos!=null){//关闭流
                try {
                    oos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 初始化book数据
     * @param bookList
     */
    public static void initBook(List<Book> bookList){
        ObjectOutputStream oos=null;
        //创建相关文件
        try {
            File directory = new File("book/");
            File file = new File(PathConstant.BOOK_PATH);
            //判断文件夹是否存在
            if (!directory.exists()){
                directory.mkdir();
            }
            //判断文件是否存在
            if (!file.exists()){
                file.createNewFile();
                List<Book> list = new ArrayList<>();
                if (bookList==null){
                    list.add(new Book(1,"java精通","张三",Constant.TYPE_COMPUTER,"123-1","机械工程出版社",Constant.STATUS_STORAGE));
                }else {
                    list=bookList;
                }

                //输出流写入到文件中
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
