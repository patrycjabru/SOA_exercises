import dao.PostDAO;
import dao.ThreadDAO;
import ejb.dto.Post;
import ejb.dto.Thread;
import ejb.interfaces.ContentManager;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ContentManagerImpl implements ContentManager{
    public List<Thread> getThreads() {
        List<Thread> threads = ThreadDAO.getThreads();
        return threads;
    }

    public Thread getThreadById(int id) {
        Thread thread = ThreadDAO.getThreadById(id);
        return thread;
    }

    public List<Post> getPosts(Thread thread) {
        return null;
    }

    public Thread addThread(Thread thread) {
        return ThreadDAO.addThread(thread);
    }

    public Post addPost(Post post) {
        return PostDAO.addPost(post);
    }
}
