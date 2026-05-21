package model;

import java.util.UUID;

public class Message {
    private UUID id;
    private String message;
    private User toUser;
    private User fromUser;

    public Message(String message, User toUser, User fromUser) {
        this.id = UUID.randomUUID();
        this.message = message;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public UUID getId() {
        return id;
    }

    public User getToUser(){
        return toUser;
    }

    public User getFromUser() {
        return fromUser;
    }
}
