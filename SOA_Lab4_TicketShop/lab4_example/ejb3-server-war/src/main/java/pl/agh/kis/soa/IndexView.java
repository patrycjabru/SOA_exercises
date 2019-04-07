package pl.agh.kis.soa;

import pl.agh.kis.soa.ejb3.server.impl.SessionBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class IndexView implements Serializable{
    @EJB
    static SessionBean sessionBean;

    private String userName;
    private String password;

    public String checkLogin() {
        boolean response = sessionBean.checkLogin(userName, password);
        if (response)
        {
            UserLoginData.setUserName(userName);
            return "reservation";
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCredit() {
        return sessionBean.getCredits();
    }
}
