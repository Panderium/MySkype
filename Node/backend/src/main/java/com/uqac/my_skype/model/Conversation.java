package com.uqac.my_skype.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Conversation {

    private String name;
    private List<Message> messages;

    public Conversation(String name, ArrayList<Message> messages) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

}
