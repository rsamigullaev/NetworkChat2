package org.rus.chatserver.controller;

import org.rus.chatserver.model.*;
import org.rus.chatserver.util.ChatServerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatServerController {
    private final Logger logger = LoggerFactory.getLogger(ChatServerController.class);
    private final ChatServerLog log = new ChatServerLog();

    @MessageMapping("/message")
    @SendTo("/chat")
    public ResponseMessage message(final ChatMessage message) {
        logger.debug("Message received: {}", message);

        log.writeMessage(Event.Message, message);
        return new ResponseMessage(Event.Message, message.userId(), message.username(), message.content(), message.timestamp());
    }

    @MessageMapping("/enter")
    @SendTo("/chat")
    public ResponseMessage enter(final ChatMessage message) {
        logger.debug("Message received: {}", message);

        log.writeMessage(Event.Enter, message);
        return new ResponseMessage(Event.Enter, message.userId(), message.username(), null, message.timestamp());
    }

    @MessageMapping("/exit")
    @SendTo("/chat")
    public ResponseMessage exit(final ChatMessage message) {
        logger.debug("Message received: {}", message);

        log.writeMessage(Event.Exit, message);
        return new ResponseMessage(Event.Exit, message.userId(), message.username(), null, message.timestamp());
    }
}
