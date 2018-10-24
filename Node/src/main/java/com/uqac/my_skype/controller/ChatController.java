package com.uqac.my_skype.controller;

import com.uqac.my_skype.Service.ConversationService;
import com.uqac.my_skype.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ChatController {

    private List<Conversation> conversations;

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = {"/", "/index"})
    public List<Conversation> index() {
        if (conversationService.isEmpty()) {
            conversations = conversationService.retrieveConversations();
        }
        return conversations;
    }

    @RequestMapping(value = "/conversation/{name}")
    public Conversation getConversation(@PathVariable("name") String name) {
        return conversationService.getConversationByName(name);
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.PUT)
    public Conversation addMessage(@PathVariable("name") String name, @RequestParam("msg") String message) {
        conversationService.newMessage(name, message);
        return conversationService.getConversationByName(name);
    }
}
