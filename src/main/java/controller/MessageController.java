package controller;

import controller.requests.SendMessageRequest;
import mediator.MessageService;
import mediator.UserService;
import model.Message;
import model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService){
        this.messageService = messageService;
        this.userService = userService;
    }

    //GET /api/messages/{userId}
    @GetMapping("/{userId}")
    public List<Message> getInbox(@PathVariable String userID){
        return messageService.getMessages(userID);
    }

    //POST /api/messages
    @PostMapping
    public Message send(@RequestBody SendMessageRequest req){
        User fromUser = userService.findByID(req.fromUserId);
        User toUser = userService.findByID(req.toUserId);
        return messageService.sendMessage(fromUser, toUser, req.content);
    }
}
