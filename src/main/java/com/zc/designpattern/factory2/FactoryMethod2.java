package com.zc.designpattern.factory2;

/**
 * @author chock
 * @since 2020/2/22
 */
public class FactoryMethod2 {

    public static void main(String[] args) {
        Application application = new ConcreteProductB();
        Product product = application.initProduct();
        product.method();
    }
}


interface Product {
    // 假设method1的方法是稳定的
    public void method();
}

class ProductA implements Product {
    @Override
    public void method() {
        System.out.println("ProductA method execute");
    }
}

class ProductB implements Product {
    @Override
    public void method() {
        System.out.println("ProductB method execute");
    }
}

abstract class Application {
    abstract Product initProduct();

    Product getObj() {
        Product product = initProduct();
        return product;
    }
}

class ConcreteProductA extends Application {
    @Override
    Product initProduct() {
        return new ProductA();
    }
}

class ConcreteProductB extends Application {
    @Override
    Product initProduct() {
        return new ProductB();
    }
}

enum productType {
    ONE,
    TWO;
}