import dao.ThreadDAO;
import dao.UserDAO;
import dao.SubscriptionDAO;
import ejb.dto.Subscription;
import ejb.dto.Thread;
import ejb.dto.User;
import ejb.interfaces.SubscriptionManager;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class SubscriptionManagerImpl implements SubscriptionManager {
    public void subscribeTopic(int userId, int topicId) {
        Subscription subscription = new Subscription();
        User user = UserDAO.getUserById(userId);
        Thread thread = ThreadDAO.getThreadById(topicId);
        subscription.setThread(thread);
        subscription.setUser(user);
        subscription.setStatus(Subscription.SubscriptionStatus.ACTIVE);
        SubscriptionDAO.addSubscription(subscription);
    }

    public List<Thread> getSubscribedThreads(int userId) {
        return SubscriptionDAO.getThreadsSubscribedByUser(userId);
    }

    public void unsubscribeTopic(int userId, int threadId) {
        Subscription subscription = SubscriptionDAO.getSubscription(userId, threadId);
        subscription.setStatus(Subscription.SubscriptionStatus.INACTIVE);
        SubscriptionDAO.update(subscription);
    }

    public boolean isUserSubscribed(int userId, int topicId) {
        //TODO implement
        return false;
    }
}
