package model;

public class LocalZeroMediator {

    private NotificationServer server;
    private UserService userService;

    public LocalZeroMediator(NotificationServer server, UserService userService) {
        this.server = server;
        this.userService = userService;
    }

    public void newInitiative(Initiative initiative) {
        initiative.getLocation();
        userService.getUsers().forEach(user -> {
            if (user.getLocation().equals(initiative.getLocation())) {
                String message = "New initiative in your area";
                Notification notification = new Notification(message,user);
                server.sendNotification(notification);
            }
        });
    }

    public void newMessage(Message message) {
        String sender = message.getFromUser().getName();
        User receiver = message.getToUser();
        String notificationOfMessage = sender + "sent you a message";
        Notification notification = new Notification(notificationOfMessage, receiver);
        server.sendNotification(notification);
    }

    public void newComment(Update update, User user) {
        User author = update.getAuthor();
        String commentorName = user.getName();
        String message = commentorName + "commented on your post";
        Notification notification = new Notification(message, author);
        server.sendNotification(notification);
    }

    public void newLike(Update update, User user) {
        User author = update.getAuthor();
        String likerName = user.getName();
        String message = likerName + "liked your post";
        Notification notification = new Notification(message, author);
        server.sendNotification(notification);
    }

    public void userJoinedInitiative(Initiative initiative, User user) {
        User creator = initiative.getCreator();
        String newMember = user.getName();
        String message= "New member " + newMember + "has joined " + initiative.getTitle();
        Notification notification = new Notification(message, creator);
        server.sendNotification(notification);
    }
}