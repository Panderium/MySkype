package network;

import Service.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;

public class GestionClient implements Runnable{

    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public GestionClient(Socket pSock){
        s = pSock;
    }

    public void run(){
        boolean closeConnexion = false;
        while(!s.isClosed()){

            try {
                this.in = new ObjectInputStream(this.s.getInputStream());
                this.out = new ObjectOutputStream(this.s.getOutputStream());
                Message mes= new Message("Saluuuuttt",false);

                out.writeObject(mes);
                try {
                    wait(10);
                }
                catch (Exception e){
                    e.printStackTrace();
                }


                if(closeConnexion){
                    in = null;
                    out = null;
                    s.close();
                    break;
                }
            }catch(SocketException e){
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}