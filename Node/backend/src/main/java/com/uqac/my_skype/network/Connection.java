package com.uqac.my_skype.network;

import com.uqac.my_skype.model.IPport;
import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.service.ConnectionService;
import com.uqac.my_skype.service.ConversationService;
import com.uqac.my_skype.utils.StaticApplicationContext;
import com.uqac.my_skype.utils.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Connection implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean isRunning;
    private ConversationService conversationService;
    private ConnectionService connectionService;

    public Connection(Socket socket) {
        conversationService = StaticApplicationContext.getContext().getBean(ConversationService.class);
        connectionService = StaticApplicationContext.getContext().getBean(ConnectionService.class);

        System.out.println("Creating connection...");
        this.socket = socket;
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress());
        System.out.println(socket.getLocalAddress());
        System.out.println(socket.getLocalPort());
        this.isRunning = true;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("out");
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("in");

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New connection created");
    }

    public void sendMessage(Message message) {
        System.out.println("Sending message...");
        try {
            message.setFrom(conversationService.user);
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Message sent");
    }

    public boolean sendUser(User user) {
        boolean response = false;
        System.out.println("Sending user...");
        try {
            String str = user.getName() + "," + user.getHash();

            byte[] mdp =user.getHash().getBytes("UTF-8");

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(mdp);

            byte[] hash = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1,hash);
            String hashtext = bigInt.toString(16);
            out.writeUTF(hashtext);
            out.writeUTF(user.getName());
            out.writeInt(0);
            out.flush();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("User sent");
        try {
            response = in.readBoolean();
            System.out.println("réponse auth:" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public IPport askIP(String name) {

        System.out.println("Asking server...");
        try {
            out.writeUTF(name);

            out.writeUTF(conversationService.user);
            out.writeInt(conversationService.port);
            out.flush();
            String IP = in.readUTF();
            Integer port = in.readInt();
            if (IP == "null" || port == 0) {
                return null;
            } else {
                System.out.println("IP reçu: " + IP + "-----" + port);

                return new IPport(IP, port);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void run() {
        //handle received message

        while (this.isRunning) {
            try {
                System.out.println("ready to listen");
                Object o = in.readObject();
                System.out.println("New message received");
                System.out.println(o);
                if (o instanceof Message) {
                    ((Message) o).setSender(false);
                    System.out.println(conversationService);
                    String from = ((Message) o).getFrom();

                    connectionService.editConnection(from);
                    conversationService.newMessage(from, (Message) o);
                    System.out.println("ajout message quand reçu, user : "+from);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        try {
//            this.serveOut = new ObjectOutputStream(this.cSocket.getOutputStream());
//            this.serveIn = new ObjectInputStream(this.cSocket.getInputStream());
//            Message mes = new Message("ccccc", true);
//            System.out.println("send message");
//
//            sendMessage(mes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}

