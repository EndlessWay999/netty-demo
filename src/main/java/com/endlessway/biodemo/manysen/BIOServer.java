package com.endlessway.biodemo.manysen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String msg;
            while(true){
                if((msg = bf.readLine())!= null){
                    System.out.println("服务器收到："+msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
