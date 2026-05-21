package model;

import java.util.ArrayList;
import java.util.List;

public class NotificationServer {
    private List<Notification> notifications = new ArrayList<Notification>();

    public NotificationServer() {

    }

    public void sendNotification(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> getNotification() {
        return notifications;
    }

}
