package controller;

import mediator.NotificationService;
import model.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    //GET /api/notifications/{userID}
    @GetMapping("/{userID}")
    public List<Notification> getForUser(@PathVariable String userID){
        return notificationService.getNotificationsForUser(userID);
    }

    //PUT /api/notifications/{ID}/read
    @PutMapping("/{ID}/read")
    public void markRead(@PathVariable String ID){
        notificationService.markRead(UUID.fromString(ID));
    }



}
