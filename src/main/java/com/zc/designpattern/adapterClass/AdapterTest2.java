package com.zc.designpattern.adapterClass;

/**
 * @author chock
 * @since 2020/3/1
 */
public class AdapterTest2 {

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.outputMatch();
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

class Adapter extends Adaptee implements Target{

    @Override
    public int outputMatch() {
        int out = output220();
        int result = 5;
        // TODO 特殊处理
        System.out.println(String.format("原始电压为：%d , 处理后适配转化为：%d", out, result));
        return result;
    }
}
