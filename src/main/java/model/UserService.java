package model;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<User>();

    public UserService() {

    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
