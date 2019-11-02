package com.zc.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * @author chock
 * @since 2019/10/27
 */
public class TestChannel {

    /**
     * ①通道：用于源节点与目标节点的连接，在NIO中负责缓冲区中的数据的传输，Channel本身不存储数据，需要配合缓冲区进行传输
     *
     * ②通道的主要实现类
     * java.nio.channels.Channel接口：
     *  FileChannel
     *  SocketChannel
     *  ServerSocketChannel
     *  DatagramChannel
     *
     * ③获取通道
     * Java针对支持通道的类提供了getChannel()方法
     *  本地IO
     *  FileInputStream / FileOutputStream
     *  RandomAccessFile
     *
     *  网络IO
     *  Socket
     *  ServerSocket
     *  DatagramSocket
     *
     * JDK 1.7 中的NIO2 针对各个通道提供了静态方法open()
     *
     * 在JDK 1.7 中的NIO2 的Files 工具类的newByteChannel()
     *
     * ④通道之间的数据传输
     *  transferFrom
     *  transferTo
     *
     * ⑤分散 Scatter 和 聚集 Gather
     *  分散读取：将通道中的数据分散到多个缓冲区中去
     *  聚集写入：将多个缓冲区中的数据聚集到缓冲区中
     *
     * ⑥字符集：Charset
     *  编码：字符串 -》 字节数组
     *  解码：字节数组 -》 字符串
     */

    // 利用通道完成文件的赋值, 非直接缓冲区
    @Test
    public void test1() throws IOException {

        long startTime = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;

        // 获取通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // 将通道中的数据存入缓冲区
           while (inChannel.read(buf) != -1) {

               // 切换成读取数据模式
               buf.flip();
               // 将缓冲区中的数据写入通道
                outChannel.write(buf);
                buf.clear();
           }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (outChannel != null) {
                outChannel.close();
            }
            if (inChannel != null) {
                inChannel.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));

    }

    // 使用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void test2() throws IOException {

        long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inMapBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMapBuf.limit()];
        inMapBuf.get(dst);
        outMapBuf.put(dst);

        inChannel.close();
        outChannel.close();

        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
    }

    // 通道间数据传输（直接缓冲区）
    @Test
    public void test3() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();
    }

    // 分散和聚集 （有序）
    @Test
    public void test4() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");

        // 获取通道
        FileChannel fc = raf.getChannel();

        // 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        // 分配读取
        ByteBuffer[] bufs = {buf1, buf2};
        fc.read(bufs);

        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("----------------------------------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel fc2 = raf2.getChannel();

        fc2.write(bufs);
    }

    // 字符集种类
    @Test
    public void test5() {
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    // 编码和解码
    @Test
    public void test6() throws CharacterCodingException   {
        Charset cs1 = Charset.forName("GBK");

        // 获取编码器和解码器
        CharsetEncoder encoder = cs1.newEncoder();
        CharsetDecoder decoder = cs1.newDecoder();

        CharBuffer cb = CharBuffer.allocate(1024);
        cb.put("验证编码器");
        cb.flip();

        // 编码
        ByteBuffer byteBuffer = encoder.encode(cb);

        for (int i = 0; i < 10; i++) {
            System.out.println(byteBuffer.get());
        }

        // 解码
        byteBuffer.flip();
        CharBuffer cb2 = decoder.decode(byteBuffer);
        System.out.println(cb2.toString());

        System.out.println("----------------------------");

        Charset cs2 = Charset.forName("UTF-8");
        byteBuffer.flip();
        CharBuffer cb3 = cs2.decode(byteBuffer);
        System.out.println(cb3.toString());

    }
}
