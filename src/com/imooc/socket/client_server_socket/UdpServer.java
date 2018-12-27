package com.imooc.socket.client_server_socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 服务器端，实现基于UDP的用户登录
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        /**
         * 接受客户端发送的数据
         */
        //1.创建服务器端DatagramSocket,指定端口
        DatagramSocket socket = new DatagramSocket(8800);
        //2.创建数据报，用户接受客户端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定接受的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);
        //3.接受客户端发送的数据
        System.out.println("服务器端已启动，等待客户端发送数据...");
        socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
        //4.读取数据
        String info = new String(data, 0, packet.getLength());
        System.out.println("我是服务器，客户端说：" + info);

        /**
         * 向客户端响应数据
         */
        //1.定义客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "反馈给客户端数据".getBytes();
        //2.创建数据报，包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        //3.响应客户端
        socket.send(packet2);
        //4.关闭资源
        socket.close();
    }
}
