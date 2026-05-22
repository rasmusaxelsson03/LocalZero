package model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String location;
    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    protected User() {}

    public User(String name, String email, String location, String passwordHash) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.passwordHash = passwordHash;
        this.roles.add(Role.RESIDENT);
    }

    public void addRole(Role role) { roles.add(role); }

    public Long getId()             { return id; }
    public String getName()         { return name; }
    public String getEmail()        { return email; }
    public String getLocation()     { return location; }
    public String getPasswordHash() { return passwordHash; }
    public Set<Role> getRoles()     { return roles; }
}