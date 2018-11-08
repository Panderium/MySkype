package com.uqac.my_skype.network;

import com.uqac.my_skype.utils.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServerP2P implements Runnable {

    private final int PORT = 3333;
    private final String HOST = "127.0.0.1";
    private final int NB_CONNECION = 200;

    private ServerSocket server;

    @Autowired
    private ConnectionFactory connectionFactory;

    public ServerP2P() {
        try {
            server = new ServerSocket(PORT, NB_CONNECION, InetAddress.getByName(HOST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        while (true) {
            try {
                System.out.println(connectionFactory);
                connectionFactory.createConnection("Roger", server.accept());

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
