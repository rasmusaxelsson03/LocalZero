package controller;

import jakarta.servlet.http.HttpSession;
import mediator.InitiativeService;
import mediator.UpdateService;
import mediator.UserService;
import model.Initiative;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UpdateController {
    private UpdateService updateService;
    private UserService userService;
    private InitiativeService initiativeService;

    public UpdateController(UpdateService updateService, UserService userService, InitiativeService initiativeService){
        this.updateService = updateService;
        this.userService = userService;
        this.initiativeService = initiativeService;
    }

    //POST /updates/{initiativeId}/post
    @PostMapping("/updates/{initiativeId}/post")
    public String postUpdate(@PathVariable Long initiativeId,
                             @RequestParam String content,
                             @RequestParam(required = false) String imageUrl,
                             HttpSession session){
        User author = userService.findByID((Long) session.getAttribute("userId"));
        Initiative initiative = initiativeService.findByID(initiativeId);
        updateService.postUpdate(content, imageUrl, author, initiative);
        return "redirect:/feed";
    }

    //POST /updates/{id}/like
    @PostMapping("/updates/{id}/like")
    public String like(@PathVariable Long id, HttpSession session){
        User user = userService.findByID((Long) session.getAttribute("userId"));
        updateService.likeUpdate(id, user);
        return "redirect:/feed";
    }

    //POST /updates/{id}/comment
    @PostMapping("/updates/{id}/comment")
    public String comment(@PathVariable Long id, @RequestParam String comment, HttpSession session){
        User user = userService.findByID((Long) session.getAttribute("userId"));
        updateService.commentOnUpdate(id, user, comment);
        return "redirect:/feed";
    }
}
