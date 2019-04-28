import ejb.exceptions.InvalidLoginCredentialsException;
import ejb.interfaces.SessionManager;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;

@ManagedBean
@Named
public class SessionBean {

    @EJB(lookup="java:global/EjbImplementation-1.0/SessionManagerImpl")
    private SessionManager sessionManagerBean;

    public void login(String login, String password) {
        try {
            sessionManagerBean.loginUser(login,password);
        } catch (InvalidLoginCredentialsException e) {
            e.printStackTrace();
        }
    }
}
