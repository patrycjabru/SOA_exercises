package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "Subscription")
public class Subscription extends AbstractDTO{
    public interface SubscriptionStatus {
        String ACTIVE = "A";
        String INACTIVE = "I";
    }

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Thread thread;

    @Column(name = "status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
