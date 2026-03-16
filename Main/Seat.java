package Main;

public class Seat {

    private int seatNo;
    private boolean booked;

    public Seat(int seatNo) {
        this.seatNo = seatNo;
        this.booked = false;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public boolean isBooked() {
        return booked;
    }

    public void bookSeat() {
        booked = true;
    }

    public void freeSeat() {
        booked = false;
    }
}