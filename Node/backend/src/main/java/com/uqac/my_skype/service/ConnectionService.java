package com.uqac.my_skype.service;

import com.uqac.my_skype.network.Connection;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ConnectionService {
    private Connection serverConnection;
    private HashMap<String, Connection> connections; // <pseudo/host, connection>

    public ConnectionService() {
        connections = new HashMap<>();
    }

    public HashMap<String, Connection> getConnections() {
        return connections;
    }

    public void addConnection(String pseudoOrHost, Connection connection) {
        new Thread(connection).start();
        connections.put(pseudoOrHost, connection);
    }

    public void addServer(Connection connection) {
        this.serverConnection = connection;
    }

    public boolean exist(String name) {
        return connections.containsKey(name);
    }

    public Connection getConnection(String name) {
        return connections.get(name);
    }

    public Connection getServerConnection() { return serverConnection; }

}
