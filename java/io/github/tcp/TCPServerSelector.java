package io.github.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class TCPServerSelector {
    private static final int BUFSIZE = 256;
    private static final int TIMEOUT = 3 * 1000;

    public static void main(String[] args) throws IOException {
        //创建一个selector监听多个socket链接
        Selector selector = Selector.open();

        //创建监听socket 和注册selector上
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        listnChannel.socket().bind(new InetSocketAddress(8080));
        listnChannel.configureBlocking(false);
        //把channel注册到selector
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);

        TCPProtocol protocol = new EchoSelectorProtocol(BUFSIZE);
        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println(".");
                continue;
            }
            //获取selector上的i/o事件
            Iterator<SelectionKey> kerIter = selector.selectedKeys().iterator();
            while (kerIter.hasNext()){
                SelectionKey key = kerIter.next();
                //等待链接事件
                if (key.isAcceptable()){
                    protocol.handleAccept(key);
                }

                //等待客户端socket通道数据
                if (key.isReadable()) {
                    protocol.handleRead(key);
                }

                //向客户端写入
                if (key.isValid() && key.isWritable()){
                    protocol.handleWrite(key);
                }

                kerIter.remove();
            }
        }
    }
}
