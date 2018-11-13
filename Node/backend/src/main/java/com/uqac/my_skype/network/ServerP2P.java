package com.uqac.my_skype.network;

import com.uqac.my_skype.utils.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServerP2P implements Runnable {

    public static int PORT ;

    private ServerSocket server;

    @Autowired
    private ConnectionFactory connectionFactory;


    public ServerP2P() {
        try {
            server = new ServerSocket(0);
            PORT = server.getLocalPort();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        while (true) {
            try {
                System.out.println(connectionFactory);
                connectionFactory.createConnection("pas de nom", server.accept());

                //Socket client = server.accept();
                System.out.println("New connection from client");
//                System.out.println(client.getInetAddress());
//                System.out.println(client.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
