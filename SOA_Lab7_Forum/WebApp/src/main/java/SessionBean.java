import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.SessionManager;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ejb.dto.*;

import java.io.Serializable;

@ManagedBean
@Named
@SessionScoped
public class SessionBean implements Serializable{

    @EJB(lookup="java:global/EjbImplementation-1.0/SessionManagerImpl")
    private SessionManager sessionManagerBean;

    public String login(String login, String password) {
        try {
            User user = sessionManagerBean.loginUser(login,password);
            UserData.setUser(user);
        } catch (InvalidLoginCredentialsException e) {
            e.printStackTrace();
        }
        return "main";
    }

    public String getUsername() {
        return UserData.getUser().getName();
    }
}
