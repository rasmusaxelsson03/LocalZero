package controller;

import controller.requests.CreateInitiativeRequest;
import controller.requests.JoinInitiativeRequest;
import mediator.InitiativeService;
import mediator.UserService;
import model.Initiative;
import model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/initiatives")
public class InitiativeController {
    private InitiativeService initiativeService;
    private UserService userService;

    public InitiativeController(InitiativeService initiativeService, UserService userService){
        this.initiativeService = initiativeService;
        this.userService = userService;
    }

    @GetMapping
    public List<Initiative> getall(){
        return initiativeService.getInitiatives();
    }

    @PostMapping
    public Initiative create(@RequestBody CreateInitiativeRequest req){
        User creator = userService.findByID(req.creatorID);
        return initiativeService.newInitiative(req.title, req.description, req.location, LocalDate.now(), LocalDate.now().plusDays(req.durationDays),
                Initiative.Category.valueOf(req.category), Initiative.Visibility.valueOf(req.visibility), creator);
    }

    @GetMapping("/carbon-saving")
    public double getCarbonsSavings(){
        Double sumSaved = initiativeService.getTotalCarbonSavings();
        return sumSaved;
    }

    @PostMapping
    public void addMember(@RequestBody JoinInitiativeRequest req){
        User member = userService.findByID(req.userId);
        Initiative initiative = initiativeService.findByID(req.initiativeId);
        initiativeService.addMember(member, initiative);
    }
}
