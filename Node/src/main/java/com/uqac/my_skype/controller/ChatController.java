package com.uqac.my_skype.controller;

import com.uqac.my_skype.Service.ConversationService;
import com.uqac.my_skype.model.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
