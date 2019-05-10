package ejb.interfaces;

import ejb.dto.Thread;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SubscriptionManager {
    void subscribeTopic(int userId, int topicId);
    List<Thread> getSubscribedThreads(int userId);
    void unsubscribeTopic(int userId, int topicId);
    boolean isUserSubscribed(int userId, int topicId);
}
