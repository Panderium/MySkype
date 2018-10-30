package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private int port = 1111;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;


    public Server(int pPort){
        port = pPort;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startServe(){

        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){
                    try {
                        Socket client = server.accept();
                        Thread t = new Thread(new GestionClient(client));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });
        t.start();
    }

    public void close(){
        isRunning = false;
    }

}
