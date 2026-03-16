package Booking;

import Main.Seat;

public class SeatChecker {
    public boolean isAvailable(Seat seat) {
        return seat != null && !seat.isBooked();
    }
}
