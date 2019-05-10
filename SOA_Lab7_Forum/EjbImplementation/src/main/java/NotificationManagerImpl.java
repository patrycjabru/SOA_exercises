import dao.DAO;
import dao.NotificationDAO;
import ejb.dto.Notification;
import ejb.interfaces.NotificationManager;

import ejb.dto.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class NotificationManagerImpl implements NotificationManager {
    public List<Notification> getUserNotifications(User user) {
        return NotificationDAO.getNotifications(user);
    }

    public void addNotification(Notification notification) {
        DAO.add(notification);
    }
}
