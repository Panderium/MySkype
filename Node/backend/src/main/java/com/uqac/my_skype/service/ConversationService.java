package com.uqac.my_skype.service;

import com.uqac.my_skype.model.Conversation;
import com.uqac.my_skype.model.IPport;
import com.uqac.my_skype.model.Message;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ConversationService {

    //    private List<Conversation> conversations = new ArrayList<>(Arrays.asList(
//            new Conversation("Roger", new ArrayList<>(Arrays.asList(new Message("Salut", true)))),
//            new Conversation("Bernard", new ArrayList<>(Arrays.asList(new Message("Salut", false)))),
//            new Conversation("Françine", new ArrayList<>(Arrays.asList(new Message("Oïe", true)))),
//            new Conversation("Madeleine la grosse moche", new ArrayList<>(Arrays.asList(new Message("Holà", false))))
//    ));
    private List<Conversation> conversations;
    private HashMap<String, IPport> ip;
    public int port;
    public String user;

    public ConversationService() {
        conversations = new ArrayList<>();
        Conversation conversation = new Conversation();
        conversation.setName("Phillipe");
        Message message = new Message("FAIT PAS LE CON PHILLIP ! ", false);
        conversation.addMessage(message);
        conversations.add(conversation);

        ip = new HashMap<>();
    }

    private List<Conversation> retrieveConversations() {
        return null;
    }

//    public void saveConversations() {
//        JSONArray root = new JSONArray();
//
//        for (Conversation conv : this.conversations) {
//            JSONObject conversation = new JSONObject();
//            conversation.put("name", conv.getName());
//            JSONArray messagesArray = new JSONArray(conv.getMessages());
//            conversation.put("messages", messagesArray);
//            root.put(conversation);
//        }
//        System.out.println(root);
//    }

    public Conversation getConversationByName(String name) {
        if (conversations.stream().anyMatch(conversation -> conversation.getName().equals(name)))
            return conversations.stream().filter(conversation -> conversation.getName().equals(name)).findFirst().get();
        return null;
    }

    public Conversation newMessage(String name, Message message) {
        Conversation conversation = this.getConversationByName(name);
        if (conversation == null) {
            System.out.println("On créé un conversation");

            conversation = new Conversation();
            conversation.addMessage(message);
            conversation.setName(name);
            conversations.add(conversation);
        } else
            conversation.addMessage(message);

        return conversation;
    }

    public List<Conversation> listAll() {
        return conversations;
    }

    public void newConversation(Conversation conversation) {
        conversations.add(conversation);
//        this.saveConversations();
    }

    public void updateIP(String name, String addr, Integer port) {
        ip.put(name, new IPport(addr, port));

//        this.saveConversations();
    }

    public IPport getIP(String name) {
        return ip.get(name);

    }

    public void deleteConversation(String name) {
        conversations.remove(
                conversations.stream().filter(conversation -> conversation.getName().equals(name)).findFirst().get()
        );
    }

}
