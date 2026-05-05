package model;

public class LocalZeroMediator {

    private Notification notification;
    private EcoAction action;

    public LocalZeroMediator(Notification notification, EcoAction action) {
        this.action = action;
    }

    public void newInitiative(Initiative initiative) {

    }

    public void newMessage(Message message) {

    }

    public void newComment(Update update) {

    }

    public void newLike(Update update) {

    }

    public void userJoinedInitiative(Initiative initiative, User user) {
        User creator = initiative.getCreator();
        String newMember = user.getName();
        String message= "New member " + newMember + "has joined " + initiative.getTitle();
        Notification notification = new Notification(message, creator);
    }
}