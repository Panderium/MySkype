package com.uqac.my_skype.network;

import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;


public class Connection implements  Runnable {
    private HashMap<String, Socket> peerSocket;
    private Socket cSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    @Autowired
    private ConversationService conversationService;

    public Connection(String ip, int port) throws IOException {
        // Connexion sever + auth
        this.cSocket = new Socket(ip, port);
        this.in = new ObjectInputStream(this.cSocket.getInputStream());
        this.out = new ObjectOutputStream(this.cSocket.getOutputStream());
    }

    public void sendMessage(Message message, String ip) throws IOException {
        out.writeObject(message);
    }

    private void openNewConnection() {

    }

    public String searchPearByName(String name) {
        return null;
    }

    @Override
    public void run() {
        try {
            while (true) {

                    System.out.println("iciiiiii");
                    Message mes = (Message) in.readObject();
                    this.handleMessage(mes);

            }
        }catch (Exception e) {
                e.printStackTrace();
            }
    }
    private void handleMessage (Message mes){
        System.out.println("handle");
        conversationService.newMessage("Roger", mes);
        }


}
