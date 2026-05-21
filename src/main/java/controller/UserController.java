package controller;

import mediator.UserService;
import model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req){
        return userService.register(req.name, req.email, req.location, req.password);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest req){
        return userService.login(req.email, req.password);
    }
}
