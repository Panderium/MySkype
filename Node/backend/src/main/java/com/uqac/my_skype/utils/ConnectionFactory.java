package com.uqac.my_skype.utils;

import com.uqac.my_skype.network.Connection;
import com.uqac.my_skype.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.Socket;

public class ConnectionFactory {

    @Autowired
    private ConnectionService connectionService;

    public void createConnection(String name, String host, int port) {
        try {
            connectionService.addConnection(name ,new Connection(new Socket(host, port)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createConnection(String name, Socket socket) {
        System.out.println("Create connection...");
        connectionService.addConnection(name, new Connection(socket));
        System.out.println("done");
    }

    public void createServerConnection(String host, int port) {
        try {
            connectionService.addServer(new Connection(new Socket(host, port)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
