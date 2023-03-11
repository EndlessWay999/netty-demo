package com.endlessway.biodemo.manyclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerThreadReader extends Thread{
    private Socket socket;

    public ServerThreadReader(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String msg;
            while(true){
                if((msg = bf.readLine())!= null){
                    System.out.println("线程："+Thread.currentThread().getName()+"  服务器收到："+msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
