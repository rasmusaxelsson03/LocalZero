package mediator;

import iterator.InitiativeIterator;
import model.Initiative;
import model.Message;
import model.Update;
import model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InitiativeService {

    private List<Initiative> initiatives = new ArrayList<>();
    private LocalZeroMediator mediator;

    public InitiativeService(LocalZeroMediator mediator) {
        this.mediator = mediator;
    }

    public Initiative findByID(long id){
        return initiatives.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addMember(User user, Initiative initiative) {
        initiative.addMember(user);
        mediator.userJoinedInitiative(initiative, user);
    }

    public void addLike(Update update, User user) {
        mediator.newLike(update, user);
    }

    public void addCommentor(Update update, User user) {
        mediator.newComment(update, user);
    }

    public void sendMessage(Message message) {
        mediator.newMessage(message);
    }

    public Initiative newInitiative(String title, String description, String location, LocalDate startDate, LocalDate endDate, Initiative.Category category, Initiative.Visibility visibility, User creator) {
        Initiative initiative = new Initiative(title, description, location, startDate, endDate, category, visibility, creator);
        initiatives.add(initiative);
        mediator.newInitiative(initiative);
        return initiative;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public Initiative findByID(UUID id) {
        for (Initiative i : initiatives) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public double getTotalCarbonSavings() {
        InitiativeIterator iterator = new InitiativeIterator(initiatives);
        return iterator.totalSavings();
    }
}
