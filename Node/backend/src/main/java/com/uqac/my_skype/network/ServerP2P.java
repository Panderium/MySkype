package com.uqac.my_skype.network;

import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerP2P {
    @Autowired
    private ConversationService conversationService;
    private int port = 2222;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private ObjectInputStream in;
    private ObjectOutputStream out;


    public ServerP2P(int pPort){
        port = pPort;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startServe(){

        Thread t = new Thread(new Runnable(){
            public void run(){

                while(true){
                    try {
                        Socket client = server.accept();
                        System.out.println("New connection from client");

                        out = new ObjectOutputStream(client.getOutputStream());
                        in = new ObjectInputStream(client.getInputStream());
                        Message mes = (Message) in.readObject();
                        client.close();
                        handleMessage(mes);




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void handleMessage (Message mes){
        System.out.println("handle");
        System.out.println(mes.getClass().toString());
        System.out.println(mes.getBody());

        System.out.println(conversationService.getClass().toString());

        conversationService.newMessage("Roger", mes);
    }

}
