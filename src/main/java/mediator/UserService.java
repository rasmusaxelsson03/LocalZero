package mediator;

import model.Role;
import model.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByID(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User register(String name, String email, String location, String password, List<String> roles){
        List<Role> enumRoles = new ArrayList<>();
        for(String role : roles){
            if(role.equals("RESIDENT")) enumRoles.add(Role.RESIDENT);
            if(role.equals("ORGANIZER")) enumRoles.add(Role.COMMUNITY_ORGANIZER);
        }
        User user = new User(name, email, location, hash(password), enumRoles);
        return userRepository.save(user);

    }

    public User login(String email, String password){
        return userRepository.findByEmail(email)
                .filter(u -> u.getPasswordHash().equals(hash(password)))

                .orElseThrow(() -> new RuntimeException("invalid email or password"));
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



    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
