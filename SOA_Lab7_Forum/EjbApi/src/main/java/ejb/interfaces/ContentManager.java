package ejb.interfaces;

import ejb.dto.Post;
import ejb.dto.Thread;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ContentManager {
    List<Thread> getThreads();
    Thread getThreadById(int id);
    List<Post> getPosts(Thread thread);
    Thread addThread(Thread thread);
    Post addPost(Post post);
}
