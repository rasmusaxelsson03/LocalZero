package mediator;

import iterator.NotificationIterator;
import model.Notification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NotificationService {
    private List<Notification> notifications = new ArrayList<Notification>();

    public NotificationService() {

    }

    public void sendNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotificationsForUser(String userID){
        NotificationIterator iterator = new NotificationIterator(notifications);
        return iterator.getForUser(UUID.fromString(userID));
    }

    public void markRead(long id){
        NotificationIterator iterator = new NotificationIterator(notifications);
        iterator.markRead(id);
    }

}
