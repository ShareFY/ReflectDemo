package com.isharefy.reflect;

import java.lang.reflect.Constructor;

public class Constructors {

	public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.isharefy.reflect.Student");
        
        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }
        
        
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor constructor : conArray){
            System.out.print(constructor.getName()+"(");
            //获取构造函数的参数列表--->得到的是参数列表的类类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName()+",");
            }
            System.out.println(")");
        }
        
        
        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法,所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        System.out.println("con = " + con);
        
        //调用构造方法
        /**
         * api的解释为：
		 *		newInstance(Object... initargs)
         *  使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
		 *  它的返回值是T类型，所以newInstance是创建了一个构造方法的声明类的新实例对象。并为之调用
         */
        Object obj = con.newInstance();
    //  System.out.println("obj = " + obj);
    //  Student stu = (Student)obj;
        
        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('A');
    }
	
}
