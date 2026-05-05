package model;

public class InitiativeService {

    private LocalZeroMediator mediator;

    public InitiativeService(LocalZeroMediator mediator) {
        this.mediator = mediator;
    }

    public void addMember(User user, Initiative initiative) {
        initiative.addMember(user);
        mediator.userJoinedInitiative(initiative, user);
    }
}
