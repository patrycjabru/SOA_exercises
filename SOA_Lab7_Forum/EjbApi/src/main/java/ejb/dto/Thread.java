package ejb.dto;

import clojure.lang.IFn;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Thread")
public class Thread extends AbstractDTO {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "thread", orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "thread", orphanRemoval = true)
    private List<Subscription> subscriptions;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
