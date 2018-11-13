package network;

import model.IPport;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class GestionClient implements Runnable {

    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static HashMap<String, IPport> BaseDNS = new HashMap<String, IPport>();
    private static HashMap<String, String> users =new HashMap<>();

    public GestionClient(Socket pSock) {
        s = pSock;

    }

    public void run() {

        if( ! new File("users").exists()){
            users.put("test","9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
            users.put("arnaud","9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
            users.put("alexis","9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
            try {
                ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("users.dat"));
                ou.writeObject(users);
                ou.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ObjectInputStream lire = new ObjectInputStream(new FileInputStream("users.dat"));
            users = (HashMap<String,String>) lire.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            this.out = new ObjectOutputStream(this.s.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(this.s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes;


        while (!s.isClosed()) {

            try {
                System.out.println("loop");
                String query = in.readUTF();
                String name = in.readUTF();
                String clientip = s.getInetAddress().getHostAddress();
                int clientport = in.readInt();
                if (clientport != 0) {

                    System.out.println("demande du client reçu: query  " + query + "---" + name + "   ip: " + clientip);
                    BaseDNS.put(name, new IPport(clientip, clientport));
                    IPport cc = BaseDNS.get(query);
                    if (cc != null) {
                        out.writeUTF(cc.IP);
                        out.writeInt(cc.port);
                        System.out.println("réponse envoyé :" + cc.IP + "  port:" + cc.port);

                    } else {
                        System.out.println("User Introuvable");

                        out.writeUTF("null");
                        out.writeInt(0);
                    }
                    out.flush();
                }else{
                    try {
                        //bytes = in.readAllBytes();
                        //String auth = new String(bytes, StandardCharsets.UTF_8);
                        System.out.println("user:"+name+"   hash:"+query);
                        if (users.containsKey(name)){
                            if(users.get(name).equals(query))
                                out.writeBoolean(true);
                            else
                                out.writeBoolean(false);

                        }
                        else
                            out.writeBoolean(false);

                        out.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            } catch (SocketException e) {
                e.printStackTrace();

                break;
            } catch (IOException e) {
                e.printStackTrace();

                return;
            }
        }
    }


}