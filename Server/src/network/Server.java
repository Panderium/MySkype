package network;

import model.IPport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ServerSocket server;
    private int port;
    private boolean isRunning;
    HashMap<String, IPport> BaseDNS;


    public Server(int port, int nbConnection, String ip) {
        this.port = port;
        this.isRunning = true;
        try {
//            server = new ServerSocket(port, 100, InetAddress.getByName(host));
            server = new ServerSocket(port);
            System.out.println(server.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                Socket client = server.accept();
                System.out.println("New client");
                Thread t = new Thread(new GestionClient(client));
                t.start();
                try {
                    Thread.sleep(400);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.isRunning = false;
    }

}
