package com.zc.designpattern.factory;

/**
 * @author chock
 * @since 2020/2/22
 */
public class FactoryMethod {
    public static void main(String[] args) {
        Application application = new Application();
        Product obj = application.getObj(productType.ONE);
        obj.method();
    }

}


class EntranceFactory {
    public static Product getPro(Enum in) {
        if (productType.ONE.equals(in)) {
            return new ProductA();
        } else if (productType.TWO.equals(in)) {
            return new ProductB();
        } else {
            return null;
        }
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

class Application {
    private Product initProduct(Enum type) {
        return EntranceFactory.getPro(type);
    }

    Product getObj(Enum type) {
        Product product = initProduct(type);
        return product;
    }
}


enum productType {
    ONE,
    TWO;
}