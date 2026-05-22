package mediator;

import model.Initiative;
import model.Update;
import model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateService {
    private LocalZeroMediator mediator;
    private List<Update> updates = new ArrayList<>();

    public UpdateService(LocalZeroMediator mediator){
        this.mediator = mediator;
    }

    public Update postUpdate(String content, String imageUrl, User author, Initiative initiative){
        Update update = new Update(content, imageUrl, author, initiative);
        updates.add(update);
        return update;
    }

    public void likeUpdate(UUID updateID, User user){
        findByID(updateID).ifPresent(update -> {
            update.like(user);
            mediator.newLike(update, user);
        });
    }

    public void commentOnUpdate(UUID updateID, User user, String comment){
        findByID(updateID).ifPresent(update -> {
            update.addComment(comment);
            mediator.newComment(update, user);
        });
    }

    public List<Update> getUpdates(UUID initiativeID){
        List<Update> result = new ArrayList<>();
        for(int i = 0; i < updates.size(); i++){
            if(updates.get(i).getInitiative().getId().equals(initiativeID)){
                result.add(updates.get(i));
            }
        }
        return result;
    }

    private java.util.Optional<Update> findByID(UUID id) {
        for (Update u : updates) {
            if (u.getId().equals(id)) return java.util.Optional.of(u);
        }
        return java.util.Optional.empty();
    }
}
