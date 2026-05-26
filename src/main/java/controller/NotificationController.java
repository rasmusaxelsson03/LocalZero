package controller;

import jakarta.servlet.http.HttpSession;
import mediator.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    //GET /notifications
    @GetMapping("/notifications")
    public String getForUser(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("notifications", notificationService.getNotificationsForUser(userId));
        return "inbox";
    }

    //PUT /notifications/{ID}/read
    @PutMapping("/{id}/read")
    public String markRead(@PathVariable long id){
        notificationService.markRead(id);
        return "redirect:/inbox";
    }



}
