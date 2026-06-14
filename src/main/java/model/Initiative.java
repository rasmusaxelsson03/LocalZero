package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Initiative {

    public enum Category { TOOL_SHARING, FOOD_SWAP, GARDENING, RECYCLING, RIDESHARING }
    public enum Visibility { PUBLIC, NEIGHBORHOOD }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // byt UUID mot Long

    private String title;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @ManyToOne
    private User creator;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Update> updates = new ArrayList<>();

    protected Initiative() {}

    public Initiative(String title, String description, String location,
                      LocalDate startDate, LocalDate endDate,
                      Category category, Visibility visibility, User creator) {
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

    public synchronized void addMember(User user){
        if(!isJoinedBy(user.getId())) {
            members.add(user);
        }
    }

    public void removeMember(User user) {
        members.removeIf(m -> m.getId().equals(user.getId()));
    }

    public void addUpdate(Update update) { updates.add(update); }

    public double getCarbonSavings()   { return members.size() * 2.5; }



    public boolean isJoinedBy(Long userId) {
        return members.stream().anyMatch(m -> m.getId().equals(userId));
    }

    public int getMemberCount()          { return members.size(); }
    public Long getId()                  { return id; }
    public String getTitle()             { return title; }
    public String getDescription()       { return description; }
    public String getLocation()          { return location; }
    public LocalDate getStartDate()      { return startDate; }
    public LocalDate getEndDate()        { return endDate; }
    public Category getCategory()        { return category; }
    public Visibility getVisibility()    { return visibility; }
    public User getCreator()             { return creator; }
    public List<User> getMembers()       { return members; }
    public List<Update> getUpdates()     { return updates; }
}