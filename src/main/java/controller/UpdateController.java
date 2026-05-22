package controller;

import controller.requests.CommentRequest;
import controller.requests.LikeRequest;
import mediator.UpdateService;
import mediator.UserService;
import model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class UpdateController {
    private UserService userService;
    private UpdateService updateService;

    @PostMapping
    public void addLike(@RequestBody LikeRequest req) {
        User sender = userService.findByID(req.sender);
        UUID update = UUID.fromString(req.update);
        updateService.likeUpdate(update, sender);
    }

    @PostMapping
    public void addComment(@RequestBody CommentRequest req) {
        User sender = userService.findByID(req.userId);
        UUID update = UUID.fromString(req.update);
        String comment = req.comment;
        updateService.commentOnUpdate(update, sender, comment);
    }
}
