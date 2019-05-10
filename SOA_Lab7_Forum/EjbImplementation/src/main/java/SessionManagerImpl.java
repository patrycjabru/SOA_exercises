import dao.UserDAO;
import ejb.dto.User;
import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.SessionManager;

import javax.ejb.Stateful;

@Stateful
public class SessionManagerImpl implements SessionManager {
    private User user;

    public User getUser(Integer userId) {
        return null;
    }

    public User loginUser(String username, String password) throws InvalidLoginCredentialsException {
        user = UserDAO.getUser(username,password);
        return user;
    }

    public void addUser(String username, String password) {

    }
}
