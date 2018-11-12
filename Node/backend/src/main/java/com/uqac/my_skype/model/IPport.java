package com.uqac.my_skype.model;

import lombok.Data;
@Data
public class IPport {
    public String IP;
    public int port;

    public IPport (String ip, int port){
        IP=ip;
        this.port= port;
    }
}
