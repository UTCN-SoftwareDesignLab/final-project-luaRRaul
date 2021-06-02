package com.stockhelt.backend.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class MessageController {

    @MessageMapping("/topic"+"/{to}")
    @SendTo("/api/topic/messages"+"/{to}")
    public WebSocketChatMessage sendMessage(@DestinationVariable Long to, WebSocketChatMessage message){
        System.out.println("handling send message: " + message + " to: " + to);
        return new WebSocketChatMessage(HtmlUtils.htmlEscape(message.getType()),HtmlUtils.htmlEscape(message.getContent()),Long.parseLong(HtmlUtils.htmlEscape(String.valueOf(message.getSender()))));
    }
}
