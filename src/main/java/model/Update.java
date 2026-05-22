package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String imageUrl;

    @ManyToOne
    private User author;

    @ManyToOne
    private Initiative initiative;

    private LocalDateTime timestamp;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> comments = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> likedByUserIds = new HashSet<>();

    protected Update() {}

    public Update(String content, String imageUrl, User author, Initiative initiative) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.author = author;
        this.initiative = initiative;
        this.timestamp = LocalDateTime.now();
    }

    public void addComment(String comment)  { comments.add(comment); }

    public void like(User user)             { likedByUserIds.add(user.getId()); }

    public boolean isLikedBy(Long userId)   { return likedByUserIds.contains(userId); }

    public Long getId()                     { return id; }
    public String getContent()              { return content; }
    public String getImageUrl()             { return imageUrl; }
    public User getAuthor()                 { return author; }
    public Initiative getInitiative()       { return initiative; }
    public LocalDateTime getTimestamp()     { return timestamp; }
    public List<String> getComments()       { return comments; }
    public int getLikeCount()               { return likedByUserIds.size(); }
}