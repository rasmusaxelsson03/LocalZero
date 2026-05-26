package mediator;

import model.Message;
import model.User;
import org.springframework.stereotype.Service;
import repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private LocalZeroMediator mediator;
    private final MessageRepository messageRepository;

    public MessageService(LocalZeroMediator mediator, MessageRepository messageRepository){
        this.messageRepository = messageRepository;
        this.mediator = mediator;
    }

    public Message sendMessage(User fromUser, User toUser, String content){
       Message message = new Message(content, toUser, fromUser);
       message = messageRepository.save(message);
       mediator.newMessage(message);
       return message;
    }

    public List<Message> getMessages(Long userId){
        return messageRepository.findByToUserId(userId);
    }
}
