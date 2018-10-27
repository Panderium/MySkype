package com.uqac.my_skype.service;

import com.uqac.my_skype.model.Conversation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConversationService {

    private List<Conversation> conversations = new ArrayList<>(Arrays.asList(
            new Conversation("Roger", new ArrayList<>(Arrays.asList("Holà ! como està"))),
            new Conversation("Bernard", new ArrayList<>(Arrays.asList("Holà !"))),
            new Conversation("Françine", new ArrayList<>(Arrays.asList("T ki wsh"))),
            new Conversation("Madeleine la grosse moche", new ArrayList<>(Arrays.asList("Wai Wai !!!!")))
    ));

    public ConversationService() {
    }

    private List<Conversation> retrieveConversations() {
        return null;
    }

    public void saveConversations() {
        JSONArray root = new JSONArray();

        for (Conversation conv : this.conversations) {
            JSONObject conversation = new JSONObject();
            conversation.put("name", conv.getName());
            JSONArray messagesArray = new JSONArray(conv.getMessages());
            conversation.put("messages", messagesArray);
            root.put(conversation);
        }
        System.out.println(root);
    }

    public Conversation getConversationByName(String name) {
        return conversations.stream().filter(conversation -> conversation.getName().equals(name)).findFirst().get();
    }

    public void newMessage(String name, String message) {
        System.out.println(message);
        this.getConversationByName(name).addMessage(message);
        this.saveConversations();
    }

    public List<Conversation> listAll() {
        return conversations;
    }

    public void newConversation(Conversation conversation) {
        conversations.add(conversation);
        this.saveConversations();
    }

    public void deleteConversation(String name) {
        conversations.remove(
                conversations.stream().filter(conversation -> conversation.getName().equals(name)).findFirst().get()
        );
    }
}
