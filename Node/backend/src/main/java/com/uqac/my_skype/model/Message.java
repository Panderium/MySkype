package com.uqac.my_skype.model;

import lombok.Data;

@Data
public class Message {

    private String body;
    private boolean sender;

    public Message(String body, boolean sender) {
        this.body = body;
        this.sender = sender;
    }
}
