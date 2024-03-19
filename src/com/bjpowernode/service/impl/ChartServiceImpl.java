package com.bjpowernode.service.impl;

import com.bjpowernode.dao.ChartDao;
import com.bjpowernode.dao.impl.ChartDaoImpl;
import com.bjpowernode.service.ChartService;

import java.util.Map;

public class ChartServiceImpl implements ChartService {

    private ChartDao chartDao = new ChartDaoImpl();

    /**
     * 统计图书分类的数量
     * @return
     */
    @Override
    public Map<String, Integer> bookTypeCount() {
        return chartDao.bookTypeCount();
    }
}
