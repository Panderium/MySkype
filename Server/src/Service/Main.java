package Service;

import network.Server;

public class Main {
    public static void main(String[] args) {

        final int port = 1111;
        final int nbConnection = 10;
        final String ip = "127.0.0.1";

        Server server = new Server(port, nbConnection, ip);
        new Thread(server).start();
    }
}
