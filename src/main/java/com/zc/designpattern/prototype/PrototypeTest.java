package com.zc.designpattern.prototype;

import lombok.Data;

import java.io.*;
import java.util.Date;

/**
 * @author chock
 * @since 2020/2/23
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setBaseName("base");
        Product product = new Product("name", "com", "me", "you", baseInfo);
        Product clone = product.clone();
        System.out.println("原来的：" + product);
        System.out.println("复制的：" + clone);
        product.getBaseInfo().setBaseName("change"); // baseInfo实现Cloneable之前，这时候会同时把克隆对象的数据改掉
        System.out.println();
        System.out.println("原来的：" + product);
        System.out.println("复制的：" + clone);

    }
}


@Data
class BaseInfo implements Cloneable, Serializable {
    private String baseName;

    @Override
    public String toString() {
        return super.hashCode() + "  BaseInfo{" +
                "baseName='" + baseName + '\'' +
                '}';
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return ((BaseInfo) super.clone());
    }
}

@Data
class Product implements Cloneable, Serializable {
    private String name;
    private String company;
    private String saleBy;
    private String saleTo;
    private BaseInfo baseInfo;

    public Product() {
    }

    public Product(String name, String company, String saleBy, String saleTo, BaseInfo baseInfo) {
        this.name = name;
        this.company = company;
        this.saleBy = saleBy;
        this.saleTo = saleTo;
        this.baseInfo = baseInfo;
    }

    @Override
    protected Product clone() throws CloneNotSupportedException {

        Product clone = null;

//        return ((Product) super.clone());
        /* Version 1 浅拷贝，递归clone可变变量
        Product clone = ((Product) super.clone());
        BaseInfo cloneBase = this.baseInfo.clone();
        clone.setBaseInfo(cloneBase);
         */

        // Version 2 利用序列化深拷贝
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            clone = ((Product) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clone;
    }


    @Override
    public String toString() {
        return super.hashCode() + "  Product{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", saleBy='" + saleBy + '\'' +
                ", saleTo='" + saleTo + '\'' +
                ", baseInfo=" + baseInfo +
                '}';
    }
}