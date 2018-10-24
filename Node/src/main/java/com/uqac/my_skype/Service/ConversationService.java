package com.uqac.my_skype.Service;

import com.uqac.my_skype.model.Conversation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return conversations.isEmpty();
    }
}
