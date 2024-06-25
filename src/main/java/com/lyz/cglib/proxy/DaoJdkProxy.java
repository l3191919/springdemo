package com.lyz.cglib.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DaoJdkProxy implements InvocationHandler {
    private  Object targetObject;
    public DaoJdkProxy (Object target){
        this.targetObject = target;
    }
    public DaoJdkProxy(){

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------DaoJdkProxy invoke----------");
        return method.invoke(targetObject,args);
    }
}
