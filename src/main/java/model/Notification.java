package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private UUID id;
    private String message;
    private User toUser;
    private boolean read = false;
    private LocalDateTime timestamp;

    public Notification(String message, User toUser){
        this.id = UUID.randomUUID();
        this.message = message;
        this.toUser = toUser;
        timestamp = LocalDateTime.now();
    }

    public void markRead(){
        read = true;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public User getToUser() {
        return toUser;
    }

    public boolean isRead() {
        return read;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
