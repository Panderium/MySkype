package com.uqac.my_skype.controller;

import com.uqac.my_skype.Service.ConversationService;
import com.uqac.my_skype.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public List<Conversation> index() {
        return conversationService.listAll();
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.GET)
    public Conversation getConversation(@PathVariable("name") String name) {
        return conversationService.getConversationByName(name);
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.PUT)
    public void addMessage(@PathVariable("name") String name, @RequestBody String message) {
        conversationService.newMessage(name, message);
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST)
    public void newConversation(@RequestBody String name, @RequestBody String message) {
        conversationService.newConversation(name, message);
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.DELETE)
    public void deleteConversation(@RequestBody String name) {
        conversationService.deleteConversation(name);
    }
}
