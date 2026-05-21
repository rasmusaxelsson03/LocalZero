package model;

import java.time.LocalDate;

public class InitiativeService {

    private LocalZeroMediator mediator;

    public InitiativeService(LocalZeroMediator mediator) {
        this.mediator = mediator;
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

    public void newInitiative(String title, String description, String location, LocalDate startDate, LocalDate endDate, Initiative.Category category, Initiative.Visibility visibility, User creator) {
        Initiative initiative = new Initiative(title, description, location, startDate, endDate, category, visibility, creator);
        mediator.newInitiative(initiative);
    }
}
