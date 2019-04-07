package pl.agh.kis.soa.ejb3.server.impl;

import java.util.LinkedList;
import java.util.List;

public class SeatList {
    private static List<Seat> seats = new LinkedList<Seat>() {
        {
            add(new Seat(1,1));
            add(new Seat(1,2));
            add(new Seat(1,3));
            add(new Seat(2,1));
            add(new Seat(2,2));
            add(new Seat(2,3));
            add(new Seat(3,1));
            add(new Seat(3,2));
            add(new Seat(3,3));
        }
    };

    public static List<Seat> getSeats() {
        return seats;
    }

    public static void setSeats(List<Seat> seats) {
        SeatList.seats = seats;
    }
}
