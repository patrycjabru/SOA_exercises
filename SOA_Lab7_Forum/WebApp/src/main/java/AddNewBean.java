import ejb.dto.Post;
import ejb.dto.Thread;
import ejb.interfaces.ContentManager;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@Named
@SessionScoped
public class AddNewBean implements Serializable {
    @EJB(lookup="java:global/EjbImplementation-1.0/ContentManagerImpl")
    private ContentManager contentManagerBean;

    public String addThread(String threadName, String content) {
        Thread thread = new Thread();
        thread.setTitle(threadName);
        thread = contentManagerBean.addThread(thread);

        addPost(thread, content);
        return "main";
    }

    public String addPost(Thread thread, String content) {
        Post post = new Post();
        post.setUser(UserData.getUser());
        Date date = new Date();
        post.setDate(date);
        post.setContent(content);
        post.setThread(thread);
        contentManagerBean.addPost(post);
        return "posts";
    }
}
