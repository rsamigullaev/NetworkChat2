package org.rus.chatserver.model;

import java.util.Date;

public record ResponseMessage(
        Event event,
        String senderId,
        String senderUsername,
        String senderContent,
        Date senderTimestamp
) {
}
