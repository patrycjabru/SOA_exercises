package ejb.dto;

import javax.persistence.*;

@Entity
@Table(name = "Post")
public class Post extends AbstractDTO {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Thread thread;

    @ManyToOne
    private User user;

    @Column(name = "content")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
