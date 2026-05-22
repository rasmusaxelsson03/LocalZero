package model;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private User toUser;

    @ManyToOne
    private User fromUser;

    protected Message() {}

    public Message(String message, User toUser, User fromUser) {
        this.message = message;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public Long getId()         { return id; }
    public String getMessage()  { return message; }
    public User getToUser()     { return toUser; }
    public User getFromUser()   { return fromUser; }
}