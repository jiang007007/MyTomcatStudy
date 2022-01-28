package io.github.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class TCPEchoClientNoblocking {
    public static void main(String[] args) throws IOException {
        byte[] argument = "helloworld".getBytes(StandardCharsets.UTF_8);
        int servetPort = 8080;
        String server = "127.0.0.1";

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        if (!channel.connect(new InetSocketAddress(server, servetPort))) {
            //轮询链接状态
            while (!channel.finishConnect()) {
                System.out.println(".");
            }
        }
        ByteBuffer writeBuf = ByteBuffer.wrap(argument);
        ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
        int totalByteRcvd = 0;
        int byteRevd;
        while (totalByteRcvd < argument.length) {
            if (writeBuf.hasRemaining()) {
                channel.write(writeBuf);
            }
            if ((byteRevd = channel.read(readBuf)) == -1) {
                throw new SocketException("Connection closed prematurel");
            }
            totalByteRcvd += byteRevd;
            System.out.print(".");
        }

        System.out.println("Received: " + new String(readBuf.array(), 0, totalByteRcvd));
        channel.close();
    }
}
