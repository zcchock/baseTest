package com.zc.designpattern;

import java.io.*;

/**
 * @author chock
 * @since 2020/2/21
 */
public class TestSerializable {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        People people = new People("chock", 18);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testSer"));
        oos.writeObject(people);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSer"));
        People read = ((People) ois.readObject());
        ois.close();
        System.out.println(people == read);

    }


}


class People implements Serializable{
    private String name;
    private Integer age;

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}