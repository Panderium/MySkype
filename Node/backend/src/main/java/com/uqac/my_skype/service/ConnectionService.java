package com.uqac.my_skype.service;

import com.uqac.my_skype.network.Connection;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ConnectionService {
    private Connection severConnection;
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
        this.severConnection = connection;
    }

    public boolean exist(String name) {
        return connections.containsKey(name);
    }

    public Connection getConnection(String name) {
        return connections.get(name);
    }
}
