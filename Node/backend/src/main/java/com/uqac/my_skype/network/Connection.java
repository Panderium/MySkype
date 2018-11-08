package com.uqac.my_skype.network;

import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.service.ConversationService;
import com.uqac.my_skype.utils.StaticApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean isRunning;
    private ConversationService conversationService;

    public Connection(Socket socket) {
        conversationService = StaticApplicationContext.getContext().getBean(ConversationService.class);
        System.out.println("Creating connection...");
        this.socket = socket;
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getLocalAddress());
        System.out.println(socket.getLocalPort());
        this.isRunning = true;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("out");
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New connection created");
    }

    public void sendMessage(Message message) {
        System.out.println("Sending message...");
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Message sent");
    }


    @Override
    public void run() {
        //handle received message
        while (this.isRunning) {
            try {
                System.out.println("ready to listen");
                Object o = in.readObject();
                System.out.println("New message received");
                System.out.println(o);
                if (o instanceof Message) {
                    ((Message) o).setSender(false);
                    System.out.println(conversationService);
                    conversationService.newMessage("Roger", (Message) o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        try {
//            this.serveOut = new ObjectOutputStream(this.cSocket.getOutputStream());
//            this.serveIn = new ObjectInputStream(this.cSocket.getInputStream());
//            Message mes = new Message("ccccc", true);
//            System.out.println("send message");
//
//            sendMessage(mes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

