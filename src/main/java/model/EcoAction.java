package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class EcoAction {
    private UUID id;
    private String description;
    private double carbonSaved;
    private User user;
    private LocalDateTime timestamp;

    public EcoAction(String description, double carbonSaved, User user){
        this.id = UUID.randomUUID();
        this.description = description;
        this.carbonSaved = carbonSaved;
        this.user = user;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getCarbonSaved() {
        return carbonSaved;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
