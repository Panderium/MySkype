package network;

import model.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;


public class GestionClient implements Runnable{

    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public GestionClient(Socket pSock){
        s = pSock;
    }

    public void run(){
        boolean closeConnexion = false;
        try {
            this.out = new ObjectOutputStream(this.s.getOutputStream());

            this.in = new ObjectInputStream(this.s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i =0;
        while(!s.isClosed()){

            try {


                Message mes= new Message("Saluuuuttt",false);

                while (i < 3) {
                    out.writeObject(mes);
                    out.flush();
                    System.out.println("Envoie message");
                    i++;
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