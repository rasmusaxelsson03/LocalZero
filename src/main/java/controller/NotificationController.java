package controller;

import jakarta.servlet.http.HttpSession;
import mediator.NotificationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    //GET /notifications
    @GetMapping("/notifications")
    public String getForUser(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("notifications", notificationService.getNotificationsForUser(userId));
        return "inbox";
    }

    //PUT /notifications/{ID}/read
    @PutMapping("/notifications/{id}/read")
    public String markRead(@PathVariable long ID){
        notificationService.markRead(ID);
        return "redirect:/inbox";
    }



}
