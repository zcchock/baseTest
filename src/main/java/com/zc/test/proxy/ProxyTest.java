package com.zc.test.proxy;

import java.lang.reflect.Proxy;

/**
 * @author chock
 * @since 2019/10/9
 */
public class ProxyTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        MyIntf proxyObj = (MyIntf) Proxy.newProxyInstance(MyIntf.class.getClassLoader(),
                new Class[]{MyIntf.class},
                new MyInvocationHandler());
        proxyObj.helloWorld();
    }
}
