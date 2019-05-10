import ejb.dto.Notification;
import ejb.interfaces.NotificationManager;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jws.soap.SOAPBinding;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Named
@SessionScoped
public class NotificationBean implements Serializable{

    @EJB(lookup="java:global/EjbImplementation-1.0/NotificationManagerImpl")
    private NotificationManager notificationManagerBean;

    public List<Notification> getNotifications() {
        return notificationManagerBean.getUserNotifications(UserData.getUser());
    }
}
