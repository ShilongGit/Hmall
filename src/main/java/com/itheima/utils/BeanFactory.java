package com.itheima.utils;


import java.util.ResourceBundle;

public class BeanFactory {

    /*
        想创建什么类型的对象 就传入什么类型的class对象
        Animal Animal.class
        Sleep  Sleep.class
     */
    public static <T> T getBean(Class<T> c){
        //获取不带包名的类名
        String simpleName = c.getSimpleName();//Animal   Sleep 就是配置文件中的键
        ResourceBundle rb = ResourceBundle.getBundle("beans");
        //根据键找到对应的值
        String value = rb.getString(simpleName);
        //反射创建对象
        Class clazz = null;
        Object o = null;
        try {
            clazz = Class.forName(value);
            //快捷方法创建对象
             o = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T)o;
    }
}
