package mediator;

import iterator.NotificationIterator;
import model.Notification;
import org.springframework.stereotype.Component;
import repository.NotificationRepository;

import java.util.List;

@Component
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByToUserId(userId);
    }

    public long countUnread(Long userId) {
        List<Notification> notifications = notificationRepository.findByToUserId(userId);
        NotificationIterator iterator = new NotificationIterator(notifications);
        long count = 0;
        while (iterator.hasNext()) {
            if (!iterator.next().isRead()) count++;
        }
        return count;
    }

    public void markAllRead(Long userId) {
        List<Notification> notifications = notificationRepository.findByToUserId(userId);
        NotificationIterator iterator = new NotificationIterator(notifications);
        while (iterator.hasNext()) {
            Notification n = iterator.next();
            if (!n.isRead()) {
                n.markRead();
                notificationRepository.save(n);
            }
        }
    }

    public void markRead(long id) {
        List<Notification> all = notificationRepository.findAll();
        NotificationIterator iterator = new NotificationIterator(all);
        while (iterator.hasNext()) {
            Notification n = iterator.next();
            if (n.getId().equals(id)) {
                n.markRead();
                notificationRepository.save(n);
                return;
            }
        }
    }
}
