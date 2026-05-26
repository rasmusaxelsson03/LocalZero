package mediator;

import iterator.NotificationIterator;
import model.Notification;
import org.springframework.stereotype.Component;
import repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(Long userId){
       return notificationRepository.findByToUserId(userId);
    }

    public void markRead(long id){
        notificationRepository.findById(id).ifPresent(n -> {
            n.markRead();
            notificationRepository.save(n);
        });
    }

}
