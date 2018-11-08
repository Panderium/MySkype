package com.uqac.my_skype.controller;

import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.network.Connection;
import com.uqac.my_skype.service.ConversationService;
import com.uqac.my_skype.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    private Connection connection;

    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public List<Conversation> index() {
        try {
            System.out.println("ici");
            connection = new Connection("127.0.0.1",1111);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(connection).start();
        return conversationService.listAll();
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.GET)
    public Conversation getConversation(@PathVariable("name") String name) {
        return conversationService.getConversationByName(name);
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.PUT)
    public Conversation addMessage(@PathVariable("name") String name, @RequestBody Message message) {
        return conversationService.newMessage(name, message);
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST)
    public void newConversation(@RequestBody Conversation conversation) {
        conversationService.newConversation(conversation);
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.DELETE)
    public void deleteConversation(@RequestBody String name) {
        conversationService.deleteConversation(name);
    }
}
