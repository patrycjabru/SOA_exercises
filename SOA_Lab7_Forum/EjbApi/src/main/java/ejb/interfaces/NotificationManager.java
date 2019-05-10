package ejb.interfaces;

import ejb.dto.Notification;

import javax.ejb.Remote;
import ejb.dto.User;
import java.util.List;

@Remote
public interface NotificationManager {
    List<Notification> getUserNotifications(User user);
    void addNotification(Notification notification);
}
