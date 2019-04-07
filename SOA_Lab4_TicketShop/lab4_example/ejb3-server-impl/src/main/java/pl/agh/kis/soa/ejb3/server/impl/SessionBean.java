package pl.agh.kis.soa.ejb3.server.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.List;

@Local
@Stateful
public class SessionBean {
    @EJB
    MainBean mainBean;


    private User currentUser;

    public boolean checkLogin(String name, String password) {
        User u = UserList.checkLogin(name, password);
        if (u!=null) {
            currentUser = u;
            return true;
        }
        return false;
    }

    public boolean buySeats(List<Seat> chosenSeats) {
        if (chosenSeats == null || currentUser==null)
            return false;
        double total = mainBean.countTotal(chosenSeats);
        boolean canBuy = checkIfSufficientCredits(total);
        if (!canBuy)
            return false;
        canBuy = mainBean.areSeatsAvailable(chosenSeats);
        if (!canBuy)
            return false;
        setReservedSeats(chosenSeats);
        makePayment(total);
        return true;
    }

    private void makePayment(double total) {
        double creditsBefore = currentUser.getCredits();
        double creditsAfter = creditsBefore - total;
        currentUser.setCredits(creditsAfter);
    }

    public boolean checkIfSufficientCredits(double expectedCredits) {
        if (currentUser.getCredits()>=expectedCredits)
            return true;
        return false;
    }


    private void setReservedSeats(List<Seat> chosenSeats) {
        for (Seat s:chosenSeats) {
            int row = s.getRow();
            int column = s.getColumn();
            for (Seat p:SeatList.getSeats()) {
                if (p.getRow() == row && p.getColumn() == column)
                    p.setIsReserved(true);
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public double getCredits() {
        return currentUser.getCredits();
    }
}
