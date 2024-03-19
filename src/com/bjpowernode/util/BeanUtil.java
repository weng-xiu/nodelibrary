package com.bjpowernode.util;

import java.lang.reflect.Field;

public class BeanUtil {
    /**
     * ��dest���ݸ�ֵ��origin
     * @param origin
     * @param dest
     */
    public static void populate(Object origin,Object dest){
        //ʹ�÷���
        //�ж����������Ƿ�ͬ����
        if (origin.getClass()!=dest.getClass()){
            throw new  RuntimeException("����������ͬ����");
        }
        Class<?> aClass= origin.getClass();
        //��ȡ����
        Field[] fields = aClass.getDeclaredFields();
        for (Field f:fields){
            //�ų�serialVersionUID
            if ("serialVersionUID".equals(f.getName())){
                continue;
            }

            //���Ʒ�װ
            f.setAccessible(true);
            //��dest�������ҵ���Ӧ������ֵ�����Ƶ���Ӧ��origin ��ֵ
            try {
                f.set(origin,f.get(dest));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
