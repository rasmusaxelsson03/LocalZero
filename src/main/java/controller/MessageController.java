package controller;

import jakarta.servlet.http.HttpSession;
import mediator.MessageService;
import mediator.UserService;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    //GET /inbox
    @GetMapping("/inbox")
    public String getInbox(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");

        model.addAttribute("messages", messageService.getMessages(userId));
        return "inbox";
    }

    //POST /api/messages
    @PostMapping("/inbox/send")
    public String send(@RequestParam String toUserId, @RequestParam String content, HttpSession session){
        String fromUserId = (String) session.getAttribute("userId");
        User fromUser = userService.findByID(fromUserId);
        User toUser = userService.findByID(toUserId);
        messageService.sendMessage(fromUser, toUser, content);
        return "redirect:/inbox";
    }
}
