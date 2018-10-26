package com.uqac.my_skype.Service;

import com.uqac.my_skype.model.Conversation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ConversationService {

    private List<Conversation> conversations;

    public ConversationService() {
        conversations = retrieveConversations();
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
        this.getConversationByName(name).addMessage(message);
    }

    public List<Conversation> listAll() {
        return conversations;
    }

    public void newConversation(String name, String message) {
        conversations.add(new Conversation(name, new ArrayList<>(Collections.singletonList(message))));
    }

    public void deleteConversation(String name) {
        conversations.remove(
                conversations.stream().filter(conversation -> conversation.getName().equals(name)).findFirst().get()
        );
    }
}
