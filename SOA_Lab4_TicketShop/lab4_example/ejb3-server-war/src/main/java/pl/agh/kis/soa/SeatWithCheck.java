package pl.agh.kis.soa;

import pl.agh.kis.soa.ejb3.server.impl.Seat;

public class SeatWithCheck {
    private Seat seat;
    private boolean isChecked = false;

    public SeatWithCheck(Seat seat, boolean isChecked) {
        this.seat = seat;
        this.isChecked = isChecked;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
