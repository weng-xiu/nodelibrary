package com.bjpowernode.util;

import java.lang.reflect.Field;

public class BeanUtil {
    /**
     * 把dest数据赋值到origin
     * @param origin
     * @param dest
     */
    public static void populate(Object origin,Object dest){
        //使用反射
        //判断两个对象是否同类型
        if (origin.getClass()!=dest.getClass()){
            throw new  RuntimeException("两个对象需同类型");
        }
        Class<?> aClass= origin.getClass();
        //获取属性
        Field[] fields = aClass.getDeclaredFields();
        for (Field f:fields){
            //排除serialVersionUID
            if ("serialVersionUID".equals(f.getName())){
                continue;
            }

            //打破封装
            f.setAccessible(true);
            //从dest对象中找到对应的属性值，复制到对应的origin 赋值
            try {
                f.set(origin,f.get(dest));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
