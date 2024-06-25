package com.lyz.cglib;

import com.lyz.cglib.proxy.DaoJdkProxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //将生成的代理类文件存到项目根目录
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //CglibDaoImpl 是实现类
        //CglibDaoInterface 是接口

        CglibDaoInterface dao =( CglibDaoInterface) Proxy.newProxyInstance(
                        CglibDaoImpl.class.getClassLoader(),//代理类加载器
                        CglibDaoImpl.class.getInterfaces(),//代理的接口
                        new DaoJdkProxy(new CglibDaoImpl()));
        long l = System.currentTimeMillis();
        for (int i=0;i<999999999;i++){
            dao.select();
        }
        long e = System.currentTimeMillis();

        System.out.println(e-l);

    }
}
