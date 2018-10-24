package com.uqac.my_skype.Service;

import com.uqac.my_skype.model.Conversation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private List<Conversation> conversations;

    public List<Conversation> retrieveConversations() {
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

    public boolean isEmpty() {
        return conversations == null || conversations.isEmpty();
    }

    public Conversation getConversationByName(String name) {
        return conversations.stream().filter(conversation -> conversation.getName() == name).collect(Collectors.toList()).get(0);
    }

    public void newMessage(String name, String message) {
        this.getConversationByName(name).addMessage(message);
    }
}
