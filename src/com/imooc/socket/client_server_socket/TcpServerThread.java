package com.imooc.socket.client_server_socket;

import java.io.*;
import java.net.Socket;

/**
 * 服务器线程处理类
 */
public class TcpServerThread extends Thread{
     //和本线程相关的Socket
    Socket socket;

    public TcpServerThread(Socket socket) {
        this.socket = socket;
    }

    //线程执行的操作，响应客户端德请求
    @Override
    public void run() {
        InputStream is = null;//字节输入流
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;//包装为打印流
        try {
            //获取输入流，并读取客户端信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器，客户端说：" + info);
            }
            socket.shutdownInput();//关闭输入流
            //获取输出流，响应客户端德请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("欢迎您！");
            pw.flush();//调用flush()方法将缓冲输出
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (pw != null)
                    pw.close();
                if (os != null)
                    os.close();
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (is != null)
                    is.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
