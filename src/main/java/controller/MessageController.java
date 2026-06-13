package controller;

import jakarta.servlet.http.HttpSession;
import mediator.MessageService;
import mediator.NotificationService;
import mediator.UserService;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MessageController {
    private final NotificationService notificationService;
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService, NotificationService notificationService){
        this.messageService = messageService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    //GET /inbox
    @GetMapping("/inbox")
    public String getInbox(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("userId");
        model.addAttribute("messages", messageService.getMessages(userId));
        model.addAttribute("notifications", notificationService.getNotificationsForUser(userId));
        model.addAttribute("users", userService.getUsers());
        return "inbox";
    }

    //POST /api/messages
    @PostMapping("/message/send")
    public String send(@RequestParam Long toUserId, @RequestParam String content, HttpSession session){
        Long fromUserId = (Long) session.getAttribute("userId");
        User fromUser = userService.findByID(fromUserId);
        User toUser = userService.findByID(toUserId);
        messageService.sendMessage(fromUser, toUser, content);
        return "redirect:/inbox";
    }
}
