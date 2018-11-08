package network;

import Service.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ServerSocket server;
    private int port;
    private boolean isRunning;


    public Server(int port, int nbConnection, String ip) {
        this.port = port;
        this.isRunning = true;
        try {
//            server = new ServerSocket(port, 100, InetAddress.getByName(host));
            server = new ServerSocket(port, nbConnection, InetAddress.getByName(ip));
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
                this.in = new ObjectInputStream(client.getInputStream());
                this.out = new ObjectOutputStream(client.getOutputStream());
                Message mes= new Message("Saluuuuttt",false);
                out.writeObject(mes);
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
