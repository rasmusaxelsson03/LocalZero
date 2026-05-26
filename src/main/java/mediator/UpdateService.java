package mediator;

import model.Initiative;
import model.Update;
import model.User;
import org.springframework.stereotype.Service;
import repository.UpdateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateService {
    private LocalZeroMediator mediator;
    private final UpdateRepository updateRepository;

    public UpdateService(UpdateRepository updateRepository, LocalZeroMediator mediator) {
        this.updateRepository = updateRepository;
        this.mediator = mediator;

    }

    public Update postUpdate(String content, String imageUrl, User author, Initiative initiative){
        Update update = new Update(content, imageUrl, author, initiative);
        return updateRepository.save(update);
    }

    public void likeUpdate(long updateID, User user){
        findByID(updateID).ifPresent(update -> {
            update.like(user);
            updateRepository.save(update);
            mediator.newLike(update, user);
        });
    }

    public void commentOnUpdate(long updateID, User user, String comment){
        findByID(updateID).ifPresent(update -> {
            update.addComment(comment);
            updateRepository.save(update);
            mediator.newComment(update, user);
        });
    }

    public List<Update> getUpdates(Long initiativeID){
        return updateRepository.findByInitiativeId(initiativeID);
    }

    private java.util.Optional<Update> findByID(long id) {
        return updateRepository.findById(id);
    }
}
