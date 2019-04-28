package ejb.dto;

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

    @OneToMany(mappedBy = "thread", orphanRemoval = true)
    private List<Post> posts;


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
}
