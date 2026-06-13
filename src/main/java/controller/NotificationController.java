package controller;

import jakarta.servlet.http.HttpSession;
import mediator.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    // POST /notifications/{id}/read
    @PostMapping("/{id}/read")
    public String markRead(@PathVariable long id){
        notificationService.markRead(id);
        return "redirect:/inbox";
    }

    // POST /notifications/read-all
    @PostMapping("/read-all")
    public String markAllRead(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        notificationService.markAllRead(userId);
        return "redirect:/inbox";
    }
}
