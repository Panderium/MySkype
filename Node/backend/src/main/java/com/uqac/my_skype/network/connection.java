package com.uqac.my_skype.network;

import com.uqac.my_skype.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class connection {
    private HashMap<String, Socket> peerSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public connection(String ip, int port) throws IOException {
        // Connexion sever + auth
        this.clientSocket = new Socket(ip, port);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(Message message, String ip) {

    }

    private void openNewConnection() {

    }

    public String searchPearByName(String name) {
        return null;
    }


}
