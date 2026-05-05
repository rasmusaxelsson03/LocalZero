package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Initiative {
    public enum Category { TOOL_SHARING, FOOD_SWAP, GARDENING, RECYCLING, RIDESHARING }
    public enum Visibility { PUBLIC, NEIGHBORHOOD }

    private UUID id;
    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private Category category;
    private Visibility visibility;
    private User creator;
    private List<User> members = new ArrayList<>();
    private List<Update> updates = new ArrayList<>();

    public Initiative(String title, String description, String location, LocalDate startDate, LocalDate endDate, Category category, Visibility visibility, User creator){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.visibility = visibility;
        this.creator = creator;
        this.members.add(creator);
    }

    public void addMember(User user){
        members.add(user);
    }

    public void addUpdate(Update update){
        updates.add(update);
    }


    public double getCarbonSavings(){
        return members.size()*2.5; //mock value
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Category getCategory() {
        return category;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public User getCreator() {
        return creator;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Update> getUpdates() {
        return updates;
    }
}
