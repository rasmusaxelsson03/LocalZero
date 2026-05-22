package controller;

import controller.requests.CommentRequest;
import controller.requests.PostUpdateRequest;
import controller.requests.UserIdRequest;
import mediator.InitiativeService;
import mediator.UpdateService;
import mediator.UserService;
import model.Initiative;
import model.Update;
import model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/updates")
public class UpdateController {
    private UpdateService updateService;
    private UserService userService;
    private InitiativeService initiativeService;

    public UpdateController(UpdateService updateService, UserService userService, InitiativeService initiativeService){
        this.updateService = updateService;
        this.userService = userService;
        this.initiativeService = initiativeService;
    }

    //GET /api/updates/{initiativeID}
    @GetMapping("/{initiativeId")
    public List<Update> getForInitiative(@PathVariable String initiativeID){
        return updateService.getUpdates(UUID.fromString(initiativeID));
    }

    //POST /api/updates
    @PostMapping
    public Update postUpdate(@RequestBody PostUpdateRequest req){
        User author = userService.findByID(req.authorId);
        Initiative initiative = initiativeService.findByID(UUID.fromString(req.initiativeId));
        return updateService.postUpdate(req.content, req.imageUrl, author, initiative)
    }

    //POST /api/updates/{id}/like
    @PostMapping("/{id}/like")
    public void like(@PathVariable String id, @RequestBody UserIdRequest req){
        User user = userService.findByID(req.userId);
        updateService.likeUpdate(UUID.fromString(id), user);
    }

    //POST /api/updates/{id}/comment
    @PostMapping("/{id}/comment")
    public void comment(@PathVariable String id, @RequestBody CommentRequest req){
        User user = userService.findByID(req.userId);
        updateService.commentOnUpdate(UUID.fromString(id), user, req.comment);
    }
}
