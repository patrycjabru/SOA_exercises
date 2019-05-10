package ejb.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class Notification extends AbstractDTO {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String content;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
