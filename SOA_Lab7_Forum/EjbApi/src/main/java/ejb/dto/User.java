package ejb.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User extends AbstractDTO {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Subscription> subscriptions;

    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
