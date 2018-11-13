package network;

import model.IPport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;


public class GestionClient implements Runnable {

    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static HashMap<String, IPport> BaseDNS = new HashMap<String, IPport>();

    public GestionClient(Socket pSock) {
        s = pSock;

    }

    public void run() {

        try {
            this.out = new ObjectOutputStream(this.s.getOutputStream());
            this.out.flush();
            this.in = new ObjectInputStream(this.s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes;
        try {
            //bytes = in.readAllBytes();
            //String auth = new String(bytes, StandardCharsets.UTF_8);
            String auth = in.readUTF();
            System.out.println(auth);
            String[] credential = auth.split(",");
            System.out.println(credential);
            out.writeBoolean(true);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = 0;
        while (!s.isClosed()) {

            try {
                System.out.println("loop");
                String query = in.readUTF();
                String name = in.readUTF();
                String clientip = s.getInetAddress().getHostAddress();
                int clientport = in.readInt();
                System.out.println("demande du client reçu: query  " + query + "---" + name + "   ip: " + clientip);
                BaseDNS.put(name, new IPport(clientip, clientport));
                IPport cc = BaseDNS.get(query);
                if (cc != null) {
                    out.writeUTF(cc.IP);
                    out.writeInt(cc.port);
                    System.out.println("réponse envoyé :" + cc.IP + "  port:" + cc.port);

                } else {
                    out.writeUTF("null");
                    out.writeInt(0);
                }
                out.flush();


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