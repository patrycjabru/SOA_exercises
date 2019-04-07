package pl.agh.kis.soa.ejb3.server.impl;

import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
@Local
@Lock
public class MainBean {
    public boolean areSeatsAvailable(List<Seat> chosenSeats) {
        for (Seat s:chosenSeats) {
            int row = s.getRow();
            int column = s.getColumn();
            for (Seat p:SeatList.getSeats()) {
                if (p.getRow() == row && p.getColumn() == column)
                    if (p.getIsReserved())
                        return false;
            }
        }
        return true;
    }

    public double countTotal(List<Seat> chosenSeats) {
        double total = 0;
        for (Seat s:chosenSeats) {
            total += s.getPrice();
        }
        return total;
    }

    public List<Seat> getSeatList(){
        return SeatList.getSeats();
    }

    public double getSeatCost(Seat seat) {
        return seat.getPrice();
    }

    public boolean ifSeatAvailable(Seat seat) {
        return !seat.getIsReserved();
    }
}
