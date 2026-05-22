package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    private User toUser;

    private boolean read = false;
    private LocalDateTime timestamp;

    protected Notification() {}

    public Notification(String message, User toUser) {
        this.message = message;
        this.toUser = toUser;
        this.timestamp = LocalDateTime.now();
    }

    public void markRead()                  { read = true; }
    public Long getId()                     { return id; }
    public String getMessage()              { return message; }
    public User getToUser()                 { return toUser; }
    public boolean isRead()                 { return read; }
    public LocalDateTime getTimestamp()     { return timestamp; }
}