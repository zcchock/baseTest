package com.zc.designpattern.adapterObject;

/**
 * @author chock
 * @since 2020/3/1
 */
public class AdapterTest {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.outputMatch();
    }
}


class Adaptee {
    public int output220() {
        return 220;
    }
}

interface Target {
    int outputMatch();
}

class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public int outputMatch() {
        int out = adaptee.output220();
        int result = 5;
        // TODO 特殊处理
        System.out.println(String.format("原始电压为：%d , 处理后适配转化为：%d", out, result));
        return result;
    }
}