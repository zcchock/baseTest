package com.zc.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 缓冲区，负责数据存储，用于存储不同数据类型的数组
 * 根据数据类型不同产生不同类型的缓冲区 boolean除外
 * ByteBuffer   CharBuffer  ShortBuffer
 * IntegerBuffer    LongBuffer  FloatBuffer DoubleBuffer
 *
 * allocate获取缓冲区
 *
 * put()：存入数据   get()：获取数据
 *
 * @author chock
 * @since 2019/10/23
 */
public class TestBuffer {

    @Test
    public void test1() {

        String str = "chock";

        // 分配指定大小缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("--------------allocate-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 存入数据
        buf.put(str.getBytes());
        System.out.println("--------------put-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 切换为读模式，把操作点切到position = 0   limit切到现在的数组位置
        buf.flip();
        System.out.println("--------------flip-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, 3));
        System.out.println("--------------get-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // rewind 重置指针
        buf.rewind();
        System.out.println("--------------rewind-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 清空缓冲区，只是清空状态，数据并没有清楚，处于被遗忘状态
        buf.clear();
        System.out.println("--------------clear-----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char)buf.get());
    }

    @Test
    public void test2() {
        String str = "cong1234";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();

        byte[] byteArr = new byte[buf.limit()];
        buf.get(byteArr, 0, 2);
        System.out.println(new String(byteArr, 0, 2));
        System.out.println(buf.position());

        //通过mark标记
        buf.mark();
        buf.get(byteArr, 2, 2);
        System.out.println(new String(byteArr, 2, 2));
        System.out.println(buf.position());

        buf.reset();
        buf.get(byteArr, 4, 2);
        System.out.println(new String(byteArr, 4, 2));
        System.out.println(buf.position());


    }


}
