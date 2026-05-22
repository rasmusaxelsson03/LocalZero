package mediator;

import model.Message;
import model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private LocalZeroMediator mediator;
    private List<Message> messages = new ArrayList<>();

    public MessageService(LocalZeroMediator mediator){
        this.mediator = mediator;
    }

    public Message sendMessage(User fromUser, User toUser, String content){
        Message message = new Message(content, toUser, fromUser);
        messages.add(message);
        mediator.newMessage(message);
        return message;
    }

    public List<Message> getMessages(String userID){
        List<Message> result = new ArrayList<>();
        for(int i = 0; i < messages.size(); i++){
            if(messages.get(i).getToUser().getId().equals(UUID.fromString(userID))){
                result.add(messages.get(i));
            }
        }
        return result;
    }
}
