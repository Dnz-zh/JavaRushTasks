package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    SomeInterfaceWithMethods someInterface;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterface) {
        this.someInterface = someInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("%s in",method.getName()));
        Object object = method.invoke(someInterface, args);
        System.out.println(String.format("%s out",method.getName()));
        return object;
    }
}
