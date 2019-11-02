package com.zc.nio2;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author chock
 * @since 2019/10/28
 */
public class TestNonBlockingNIO {

    // 客户端
    @Test
    public void client() throws IOException {

        // 获取通道 非阻塞
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9696));
        socketChannel.configureBlocking(false);

        // 分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 发送数据给服务器
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.next();
            buf.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        /*buf.put((LocalDateTime.now().toString()).getBytes());
        buf.flip();
        socketChannel.write(buf);
        buf.clear();*/

        socketChannel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // 绑定连接（端口）
        serverSocketChannel.bind(new InetSocketAddress(9696));

        // 获取选择器
        Selector selector = Selector.open();

        // 将通道注册到选择器上 指定监听键为接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮询获取选择器上的已经准备就绪的事件
        while(selector.select() > 0) {
            // 获取当前选择器中所有注册的 选择键
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            // 迭代 获取准备 就绪 的事件
            while(it.hasNext()) {
                SelectionKey sk = it.next();

                // 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 若接收就绪，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 切换非阻塞
                    socketChannel.configureBlocking(false);
                    // 注册
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 获取当前选择器上读就绪状态的通道
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                // 取消选择键
                it.remove();
            }
        }
//        serverSocketChannel.close();
    }


}
