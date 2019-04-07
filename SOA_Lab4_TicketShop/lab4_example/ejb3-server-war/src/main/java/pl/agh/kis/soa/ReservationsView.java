package pl.agh.kis.soa;

import pl.agh.kis.soa.ejb3.server.impl.MainBean;
import pl.agh.kis.soa.ejb3.server.impl.Seat;
import pl.agh.kis.soa.ejb3.server.impl.SessionBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
public class ReservationsView implements Serializable{

    @EJB
    MainBean mainBean;

    List<Seat> seatsList;

    private void init() {
        if(seatsList == null)
            seatsList = mainBean.getSeatList();
        if(SeatsWithCheckListContainer.seatWithCheckList == null) {
            SeatsWithCheckListContainer.seatWithCheckList = new LinkedList<SeatWithCheck>() {
                {
                    for (Seat aSeatsList : seatsList) {
                        add(new SeatWithCheck(aSeatsList, false));
                    }
                }
            };
        }
    }

    private void update() {
        seatsList = mainBean.getSeatList();
        for (int i=0; i<seatsList.size(); i++) {
            boolean isReserved = seatsList.get(i).getIsReserved();
            SeatsWithCheckListContainer.seatWithCheckList.get(i).getSeat().setIsReserved(isReserved);
        }
    }

    public List<SeatWithCheck> getSeatWithCheckList() {
        init();
        return SeatsWithCheckListContainer.seatWithCheckList;
    }

    public void setSeatWithCheckList(List<SeatWithCheck> seatWithCheckList) {
        SeatsWithCheckListContainer.seatWithCheckList = seatWithCheckList;
    }

    public String buy() {
        update();
        boolean areAvailable = checkIfAllAvailable();
        if (!areAvailable)
            return "";
        double credits = calculateExpectedCredits();
        boolean enoughCredits = IndexView.sessionBean.checkIfSufficientCredits(credits);
        if (!enoughCredits)
            return "";
        return "summary";
    }

    private double calculateExpectedCredits() {
        double credits = 0;
        for (int i = 0; i< SeatsWithCheckListContainer.seatWithCheckList.size(); i++) {
            if (SeatsWithCheckListContainer.seatWithCheckList.get(i).isChecked())
                credits += SeatsWithCheckListContainer.seatWithCheckList.get(i).getSeat().getPrice();
        }
        return credits;
    }

    private boolean checkIfAllAvailable() {
        for (int i = 0; i< SeatsWithCheckListContainer.seatWithCheckList.size(); i++) {
            if (SeatsWithCheckListContainer.seatWithCheckList.get(i).isChecked() && SeatsWithCheckListContainer.seatWithCheckList.get(i).getSeat().getIsReserved())
                return false;
        }
        return true;
    }
}
