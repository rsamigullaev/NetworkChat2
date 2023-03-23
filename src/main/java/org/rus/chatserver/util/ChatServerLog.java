package org.rus.chatserver.util;

import org.rus.chatserver.model.ChatMessage;
import org.rus.chatserver.model.Event;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public final class ChatServerLog {
    private final File logFile;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ChatServerLog() {
        final var currentDir = new File(System.getProperty("user.dir").replaceAll("\\\\", "/"));
        logFile = new File(currentDir, "file.log");
        try {
            if (!logFile.exists()) logFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Не удалось создать файл для записи логов.");
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void writeMessage(final String message) {
        try {
            if (!logFile.exists()) logFile.createNewFile();
            Files.writeString(logFile.toPath(), message, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeMessage(final Event event, final ChatMessage message) {
        final var messagePlain = "[%2$td.%2$tm.%2$tY %2$tH:%2$tM:%2$tS]\t[%1$s]\t%3$s (%4$s): %5$s\r\n".formatted(
                event.name(),
                message.timestamp(),
                message.username(),
                message.userId(),
                message.content()
        );
        writeMessage(messagePlain);
    }
}
