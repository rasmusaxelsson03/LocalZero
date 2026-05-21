package mediator;

import model.User;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<User>();

    public UserService() {

    }

    public User findByID(String id){
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User register(String name, String email, String location, String password){
        User user = new User(name, email, location, hash(password));
        users.add(user);
        return user;
    }

    public User login(String email, String password){
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPasswordHash().equals(hash(password)))
                .findFirst()
                .orElse(null);
    }

    private String hash(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : hashed){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
