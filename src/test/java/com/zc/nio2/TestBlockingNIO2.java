package com.zc.nio2;


import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author chock
 * @since 2019/10/28
 */
public class TestBlockingNIO2 {

    // 客户端
    @Test
    public void client() throws IOException {

        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9797));
        // 分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        // 读取本地文件，发送数据到服务器
        while (inChannel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        socketChannel.shutdownOutput();

        // 接收服务端的反馈
        int len = 0;
        while((len = socketChannel.read(buf)) != -1) {
            buf.flip();
            System.out.println(new String(buf.array(), 0, len));
            buf.clear();
        }

        // 关闭通道
        inChannel.close();
        socketChannel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定连接（端口）
        serverSocketChannel.bind(new InetSocketAddress(9797));
        // 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 接收客户端的数据并保存到本地
        while(socketChannel.read(buf) != -1) {
            buf.flip();
            fileChannel.write(buf);
            buf.clear();
        }

        // 发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        socketChannel.write(buf);

        // 关闭通道
        socketChannel.close();
        serverSocketChannel.close();
        fileChannel.close();
    }
}
