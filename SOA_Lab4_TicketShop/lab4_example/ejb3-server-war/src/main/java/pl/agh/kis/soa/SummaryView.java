package pl.agh.kis.soa;

import pl.agh.kis.soa.ejb3.server.impl.MainBean;
import pl.agh.kis.soa.ejb3.server.impl.Seat;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
public class SummaryView implements Serializable {

    @EJB
    MainBean mainBean;

    public List<SeatWithCheck> getList() {
        List<SeatWithCheck> onlyChecked = new LinkedList<>();
        for (SeatWithCheck c: SeatsWithCheckListContainer.seatWithCheckList) {
            if (c.isChecked())
                onlyChecked.add(c);
        }
        return onlyChecked;
    }

    public String buy() {
        updateList();
        List<SeatWithCheck> checkedSeats = getList();
        List<Seat> seats = castToSeatList(checkedSeats);

        boolean ok = IndexView.sessionBean.buySeats(seats);
        cleanData();

        if(ok)
            return "reservation";
        return "error";
    }
    private void cleanData() {
        SeatsWithCheckListContainer.seatWithCheckList = null;
    }

    private List<Seat> castToSeatList(List<SeatWithCheck> checkedSeats) {
        List<Seat> seats = new LinkedList<>();
        for (SeatWithCheck s:checkedSeats) {
            seats.add(s.getSeat());
        }
        return seats;
    }

    private void updateList() {
        List<Seat> list = mainBean.getSeatList();

        for (int i=0;i<list.size();i++) {
            boolean isReserved = list.get(i).getIsReserved();
            SeatsWithCheckListContainer.seatWithCheckList.get(i).getSeat().setIsReserved(isReserved);
        }
    }
}
