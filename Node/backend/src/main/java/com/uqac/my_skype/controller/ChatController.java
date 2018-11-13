package com.uqac.my_skype.controller;

import com.uqac.my_skype.model.Conversation;
import com.uqac.my_skype.model.IPport;
import com.uqac.my_skype.model.Message;
import com.uqac.my_skype.network.Connection;
import com.uqac.my_skype.network.ServerP2P;
import com.uqac.my_skype.service.ConnectionService;
import com.uqac.my_skype.service.ConversationService;
import com.uqac.my_skype.utils.ConnectionFactory;
import com.uqac.my_skype.utils.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ServerP2P serverP2P;

    private boolean firstConnection = true;
    private boolean logged = false;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody User user) {
        System.out.println("Auth");
        this.logged = connectionService.authServer(user);
        if (this.logged){
            conversationService.user = user.getName();
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean askLogged() {
        if (this.firstConnection) {
            new Thread(serverP2P).start();
            conversationService.port = serverP2P.PORT;
            connectionFactory.createServerConnection("127.0.0.1", 1111);
            this.firstConnection = false;
        }
        return this.logged;
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public List<Conversation> index() {
        if (this.logged) {
//            if (this.firstConnection) {
//                new Thread(serverP2P).start();
//                connectionFactory.createServerConnection("127.0.0.1", 1111);
//                this.firstConnection = false;
//            }
            return conversationService.listAll();
        }
        return null;
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.GET)
    public Conversation getConversation(@PathVariable("name") String name) {
        if (this.logged) {
            return conversationService.getConversationByName(name);
        }
        return null;
    }

    @RequestMapping(value = "/conversation/{name}", method = RequestMethod.PUT)
    public Conversation addMessage(@PathVariable("name") String name, @RequestBody Message message) {
        if (this.logged) {
            if (!connectionService.exist(name)) {
                System.out.println("Connection don't exist");
                IPport sock = connectionService.getServerConnection().askIP(name);
                if (sock != null) {
                    connectionFactory.createConnection(name, sock.getIP(), sock.getPort());

                } else {
                    System.out.println("Client Introuvable");
                    return conversationService.getConversationByName(name);
                }
                /*
                 * Si la connexion n'existe pas on requete le serveur pour avoir l'adresse ip de la personne qu'on essai de
                 * joindre et on créer la connection
                 * dans tous les cas on récupère la connexion correspondante via le service.
                 */

            }
            Connection connection = connectionService.getConnection(name);
            connection.sendMessage(message);

            return conversationService.newMessage(name, message);
        }
        return null;
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST)
    public void newConversation(@RequestBody Conversation conversation) {
        if (this.logged) {
            System.out.println(conversation.getName());
            System.out.println(conversation.getMessages());
            if (!connectionService.exist(conversation.getName())) {
                System.out.println("Connection don't exist");
                IPport sock = connectionService.getServerConnection().askIP(conversation.getName());
                if (sock != null) {
                    connectionFactory.createConnection(conversation.getName(), sock.getIP(), sock.getPort());
                } else {
                    System.out.println("Client Introuvable");
                }
                /*
                 * Si la connexion n'existe pas on requete le serveur pour avoir l'adresse ip de la personne qu'on essai de
                 * joindre et on créer la connection
                 * dans tous les cas on récupère la connexion correspondante via le service.
                 */

            }
            Connection connection = connectionService.getConnection(conversation.getName());
            connection.sendMessage(conversation.getMessages().get(0));
            conversationService.newConversation(conversation);
        }
    }

    @RequestMapping(value = "/conversation", method = RequestMethod.DELETE)
    public void deleteConversation(@RequestBody String name) {
        if (this.logged) {
            conversationService.deleteConversation(name);
        }
    }

}
