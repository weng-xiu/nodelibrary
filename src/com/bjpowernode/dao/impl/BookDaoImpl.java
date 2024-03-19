package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    /**
     * 查询
     * @param book
     * @return
     */
    @Override
    public List<Book> select(Book book) {
        ObjectInputStream ois=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>)ois.readObject();
            if (list!=null){
                if (book==null||("".equals(book.getBookName())&&"".equals(book.getIsbn()))){
                    //查询全部数据
                    return list;
                }else {
                    List<Book> conditionList = new ArrayList<>();
                    //条件查询 针对2个条件
                    if (!"".equals(book.getBookName())&&!"".equals(book.getIsbn())){
                        conditionList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                        conditionList = conditionList.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    }else {
                        //条件查询 针对单个条件
                        if (!"".equals(book.getBookName())){
                            conditionList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                        }
                        if (!"".equals(book.getIsbn())){
                            conditionList = list.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                        }
                    }
                    return conditionList;
                }
            }
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
        }
        return null;
    }

    /**
     * 图书添加
     * @param book
     */
    @Override
    public void add(Book book) {

        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>)ois.readObject();

            if (list!=null){
                //生成图书id
                Book lastBook = list.get(list.size() - 1);
                book.setId(lastBook.getId()+1);
                list.add(book);
                //将图书数据写到硬盘上
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
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

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(int id) {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>)ois.readObject();
            if (list!=null){
                //从集合中查找要删除的数据、
                Book book = list.stream().filter(b -> b.getId() == id).findFirst().get();
                list.remove(book);
                //将图书数据写到硬盘上
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
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

    /**
     * 修改
     * @param book
     */
    @Override
    public void updata(Book book) {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>)ois.readObject();
            if (list!=null){
                //从集合中查找要删除的数据
                Book originbook = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();

                BeanUtil.populate(originbook,book);
//                originbook.setBookName(book.getBookName());
//                originbook.setIsbn(book.getIsbn());
//                originbook.setAuthor(book.getAuthor());

                //将图书数据写到硬盘上
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }
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
