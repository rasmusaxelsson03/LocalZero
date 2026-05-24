package mediator;

import model.Role;
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

    public User register(String name, String email, String location, String password, List<String> roles){
        boolean resident = false;
        boolean organizer = false;
        for(String role:roles){
            if(role.equals("Resident")){
                 resident = true;
            }
            if(role.equals("Organizer")){
                organizer = true;
            }
        }

        List<Role> enumRoles = new ArrayList<>();
        if(resident && organizer){
            enumRoles.add(Role.RESIDENT);
            enumRoles.add(Role.COMMUNITY_ORGANIZER);
        } else if (resident && !organizer) {
            enumRoles.add(Role.RESIDENT);
        } else if (!resident && organizer) {
            enumRoles.add(Role.COMMUNITY_ORGANIZER);
        }
        User user = new User(name, email, location, hash(password), enumRoles);
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
