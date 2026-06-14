package mediator;

import model.Initiative;
import model.Update;
import model.User;
import org.springframework.stereotype.Service;
import repository.InitiativeRepository;
import repository.UpdateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {
    private LocalZeroMediator mediator;
    private final UpdateRepository updateRepository;
    private final InitiativeRepository initiativeRepository;

    public UpdateService(UpdateRepository updateRepository, InitiativeRepository initiativeRepository, LocalZeroMediator mediator) {
        this.updateRepository = updateRepository;
        this.initiativeRepository = initiativeRepository;
        this.mediator = mediator;
    }

    public Update postUpdate(String content, String imageUrl, User author, Initiative initiative){
        Update update = new Update(content, imageUrl, author, initiative);
        Update saved = updateRepository.save(update);

        initiative.addUpdate(saved);
        initiativeRepository.save(initiative);

        return saved;
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
            update.addComment(user.getName() + ": " + comment);
            updateRepository.save(update);
            mediator.newComment(update, user);
        });
    }

    public List<Update> getUpdates(Long initiativeID){
        return updateRepository.findByInitiativeId(initiativeID);
    }

    public Optional<Update> findByID(long id) {
        return updateRepository.findById(id);
    }
}