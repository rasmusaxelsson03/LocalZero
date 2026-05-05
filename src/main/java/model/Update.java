package model;

import java.time.LocalDateTime;
import java.util.*;

public class Update {
    private UUID id;
    private String content;
    private String imageUrl;
    private User author;
    private Initiative initiative;
    private LocalDateTime timestamp;
    private List<String> comments = new ArrayList<>();
    private Set<UUID> likedByUserIds = new HashSet<>();

    public Update(String content, String imageUrl, User author, Initiative initiative){
        this.id = UUID.randomUUID();
        this.content = content;
        this.imageUrl = imageUrl;
        this.author = author;
        this.initiative = initiative;
        this.timestamp = LocalDateTime.now();
    }

    public void addComment(String comment){
        comments.add(comment);
    }

    public void like(User user){
        likedByUserIds.add(user.getId());
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<String> getComments() {
        return comments;
    }

    public int getLikeCount(){
        return likedByUserIds.size();
    }
}
