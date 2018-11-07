package model;

import network.Server;

public class Main {
    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 1111;

        Server serv = new Server( port);
        serv.startServe();

    }
}
