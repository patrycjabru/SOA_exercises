import ejb.dto.Post;
import ejb.dto.Thread;
import ejb.interfaces.ContentManager;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@ManagedBean
@Named
@SessionScoped
public class ContentBean implements Serializable{

    @EJB(lookup="java:global/EjbImplementation-1.0/ContentManagerImpl")
    private ContentManager contentManagerBean;

    private int chosenThreadId;
    private Thread chosenThread;

    public List<Thread> getThreads() {
        return contentManagerBean.getThreads();
    }

    public List<Post> getPosts() {
        chosenThread = contentManagerBean.getThreadById(chosenThreadId);
        return chosenThread.getPosts();
    }

    public Thread getChosenThread() {
        return chosenThread;
    }

    public void setChosenThread(Thread chosenThread) {
        this.chosenThread = chosenThread;
    }

    public int getChosenThreadId() {
        return chosenThreadId;
    }

    public void setChosenThreadId(int chosenThreadId) {
        this.chosenThreadId = chosenThreadId;
    }

}
