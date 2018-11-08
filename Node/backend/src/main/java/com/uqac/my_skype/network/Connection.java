package com.uqac.my_skype.network;

import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;


public class Connection implements Runnable {
    private HashMap<String, Socket> peerSocket;
    private Socket cSocket;
    private ObjectInputStream serveIn;
    private ObjectOutputStream serveOut;

    public Connection(String ip, int port) throws IOException {
        // Connexion sever + auth
        System.out.println("Connection instanciée");
        new ServerP2P(2222).startServe();
        this.cSocket = new Socket(ip, port);
    }

    public void sendMessage(Message message) throws IOException {
        serveOut.writeObject(message);
        serveOut.flush();
    }

    private void openNewConnection() {


    }

    public String searchPearByName(String name) {
        return null;
    }

    @Override
    public void run() {

        try {
            this.serveOut = new ObjectOutputStream(this.cSocket.getOutputStream());
            this.serveIn = new ObjectInputStream(this.cSocket.getInputStream());
            Message mes = new Message("ccccc", true);
            System.out.println("send message");

            sendMessage(mes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

