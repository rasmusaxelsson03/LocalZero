package iterator;

import model.Notification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class NotificationIterator implements Iterator<Notification> {
    private List<Notification> notifications;
    private int index = 0;

    public NotificationIterator(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean hasNext() {
        return index < notifications.size();
    }

    @Override
    public Notification next() {
        return notifications.get(index++);
    }

    public List<Notification> getForUser(UUID userId) {
        List<Notification> result = new ArrayList<>();
        while (hasNext()) {
            Notification n = next();
            if (n.getToUser().getId().equals(userId)) {
                result.add(n);
            }
        }
        return result;
    }

    public void markRead(UUID id) {
        while (hasNext()) {
            Notification n = next();
            if (n.getId().equals(id)) {
                n.markRead();
                return;
            }
        }
    }
}