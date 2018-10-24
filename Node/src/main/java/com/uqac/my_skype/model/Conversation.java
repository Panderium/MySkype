package com.uqac.my_skype.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Conversation {

    private String name;
    private List<String> messages;

    public Conversation(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

}
