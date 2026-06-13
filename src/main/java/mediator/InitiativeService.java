package mediator;

import iterator.InitiativeIterator;
import model.Initiative;
import model.Message;
import model.Update;
import model.User;
import org.springframework.stereotype.Service;
import repository.InitiativeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InitiativeService {

    private final InitiativeRepository initiativeRepository;
    private LocalZeroMediator mediator;

    public InitiativeService(LocalZeroMediator mediator, InitiativeRepository initiativeRepository) {
        this.mediator = mediator;
        this.initiativeRepository = initiativeRepository;
    }

    public Initiative findByID(long id){
        return initiativeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Initiative not found"));
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
        initiative = initiativeRepository.save(initiative);
        mediator.newInitiative(initiative);
        return initiative;
    }

    public List<Initiative> getInitiatives(User user) {
        return initiativeRepository.findAll().stream()
                .filter(i -> i.getVisibility() == Initiative.Visibility.PUBLIC
                        || i.getLocation().equals(user.getLocation()))
                .collect(java.util.stream.Collectors.toList());
    }

    public double getTotalCarbonSavings() {
        List<Initiative> all = initiativeRepository.findAll();
        InitiativeIterator iterator = new InitiativeIterator(all);
        return iterator.totalSavings();
    }
}
