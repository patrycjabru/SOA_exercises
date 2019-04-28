package ejb.interfaces;

import ejb.dto.User;
import ejb.exceptions.InvalidLoginCredentialsException;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface SessionManager {
    User getUser(Integer userId);
    User loginUser(String username, String password) throws InvalidLoginCredentialsException;
    void addUser(String username, String password);
}
