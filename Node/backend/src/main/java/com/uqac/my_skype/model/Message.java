package com.uqac.my_skype.model;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@Data
public class Message implements Serializable {

    private String body;
    private boolean sender;
    private Date date;
    private String from;
    private String to;

    public Message(String body, boolean sender) {
        this.body = body;
        this.sender = sender;
        this.date = new Date();
    }
}
