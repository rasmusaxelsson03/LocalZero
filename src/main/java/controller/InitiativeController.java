package controller;

import jakarta.servlet.http.HttpSession;
import mediator.InitiativeService;
import mediator.UserService;
import model.Initiative;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/api/initiatives")
public class InitiativeController {
    private InitiativeService initiativeService;
    private UserService userService;

    public InitiativeController(InitiativeService initiativeService, UserService userService){
        this.initiativeService = initiativeService;
        this.userService = userService;
    }

    //GET /feed
    @GetMapping("/feed")
    public String getFeed(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("initiatives", initiativeService.getInitiatives());
        model.addAttribute("userId", userId);
        return "feed";
    }

    //GET /initiatives/new
    @GetMapping("/initiatives/new")
    public String showCreateForm(){
        return "create-initiative";
    }

    @PostMapping
    public String  create(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam String location,
                             @RequestParam int durationDays,
                             @RequestParam String category,
                             @RequestParam String visibility,
                             HttpSession session,
                             Model model){
        try {
            User creator = userService.findByID((Long) session.getAttribute("userId"));
            initiativeService.newInitiative(
                    title, description, location,
                    LocalDate.now(), LocalDate.now().plusDays(durationDays),
                    Initiative.Category.valueOf(category),
                    Initiative.Visibility.valueOf(visibility),
                    creator
            );
            return "redirect:/feed";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "create-initiative";
        }
    }

    @GetMapping
    public double getCarbonsSavings(){
        return initiativeService.getTotalCarbonSavings();
    }

    //POST /initiatives/{id}/join
    @PostMapping("/initiatives/{id}/join")
    public String join(@PathVariable Long id, HttpSession session){
        User member = userService.findByID((Long) session.getAttribute("userId"));
        Initiative initiative = initiativeService.findByID(id);
        initiativeService.addMember(member, initiative);
        return "redirect:/feed";
    }
}
