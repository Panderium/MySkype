package model;


import java.io.Serializable;
import java.util.Date;


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
