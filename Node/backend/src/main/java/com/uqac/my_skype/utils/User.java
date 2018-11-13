package com.uqac.my_skype.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String name;
    private String hash;

    public User() {

    }

    public User(String name, String hash) {
        this.name = name;
        this.hash = hash;
    }
}
