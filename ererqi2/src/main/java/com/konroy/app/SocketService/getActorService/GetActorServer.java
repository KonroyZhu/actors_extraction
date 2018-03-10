package com.konroy.app.SocketService.getActorService;

import com.konroy.app.service.getActorService.GetActorService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GetActorServer {
    ServerSocket serverSocket;
    public void service(){
        while(true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New income: " + socket.getInetAddress());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GB2312"));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "GB2312"), true);
                String msg;
                while ((msg = br.readLine()) != null) //阻塞语句，从输入流中读入一行字符串。
                {
                    System.out.println(msg);
                    String comment=msg.split("-")[0];
                    String movie=msg.split("-")[1];

                    System.out.println(new GetActorService().getActor(comment,movie));
                    pw.println(new GetActorService().getActor(comment,movie));//向输出流中输出一行字符串。
                    pw.flush();
                }

            } catch (Exception e) {

            }
        }
    }

    public GetActorServer() throws IOException {
        serverSocket = new ServerSocket(8008);//开启8008号监听端口。
        System.out.println("服务器启动..");
    }
    public static void main(String[] args) throws IOException {
        new GetActorServer().service();
    }


}
