package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String location;
    private String passwordHash;
    private Set<Role> roles = new HashSet<>();

    public User(String name, String email, String location, String passwordHash){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.location = location;
        this.passwordHash = passwordHash;
        this.roles.add(Role.RESIDENT);
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
