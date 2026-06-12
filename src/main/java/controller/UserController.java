package controller;

import jakarta.servlet.http.HttpSession;
import mediator.UserService;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String location,
                         @RequestParam String password,
                         @RequestParam List<String> roles,
                         Model model){
        try{
            userService.register(name, email, location, password, roles);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        try{
            User user = userService.login(email, password);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRoles());
            return "redirect:/feed";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }
}
