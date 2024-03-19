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
     * ��ѯ
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
                    //��ѯȫ������
                    return list;
                }else {
                    List<Book> conditionList = new ArrayList<>();
                    //������ѯ ���2������
                    if (!"".equals(book.getBookName())&&!"".equals(book.getIsbn())){
                        conditionList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                        conditionList = conditionList.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    }else {
                        //������ѯ ��Ե�������
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
     * ͼ�����
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
                //����ͼ��id
                Book lastBook = list.get(list.size() - 1);
                book.setId(lastBook.getId()+1);
                list.add(book);
                //��ͼ������д��Ӳ����
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
     * ɾ��
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
                //�Ӽ����в���Ҫɾ�������ݡ�
                Book book = list.stream().filter(b -> b.getId() == id).findFirst().get();
                list.remove(book);
                //��ͼ������д��Ӳ����
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
     * �޸�
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
                //�Ӽ����в���Ҫɾ��������
                Book originbook = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();

                BeanUtil.populate(originbook,book);
//                originbook.setBookName(book.getBookName());
//                originbook.setIsbn(book.getIsbn());
//                originbook.setAuthor(book.getAuthor());

                //��ͼ������д��Ӳ����
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
