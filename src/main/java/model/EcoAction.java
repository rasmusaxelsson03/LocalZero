package model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class EcoAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private double carbonSaved;

    @ManyToOne
    private User user;

    private LocalDateTime timestamp;

    public EcoAction(String description, double carbonSaved, User user){
        this.description = description;
        this.carbonSaved = carbonSaved;
        this.user = user;
        this.timestamp = LocalDateTime.now();
    }

    public EcoAction(){
        System.out.println("sup!");
    }

    public Long getId() {
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
