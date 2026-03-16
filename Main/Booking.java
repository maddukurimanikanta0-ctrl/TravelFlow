package Main;

public class Booking {

    private static int COUNTER = 1000;

    private String bookingId;
    private String transportType;
    private String vehicleNo;
    private String source;
    private String destination;
    private Passenger passenger;
    private Seat seat;
    private int fare;

    // Train
    private String coach;
    private String berth;

    // Flight
    private String flightSeat;

    public Booking(String transportType, String vehicleNo,
                   String source, String destination,
                   Passenger passenger, Seat seat, int fare) {

        this.bookingId = "BK" + (++COUNTER);
        this.transportType = transportType;
        this.vehicleNo = vehicleNo;
        this.source = source;
        this.destination = destination;
        this.passenger = passenger;
        this.seat = seat;
        this.fare = fare;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setTrainDetails(String coach, String berth) {
        this.coach = coach;
        this.berth = berth;
    }

    public void setFlightSeat(String seat) {
        this.flightSeat = seat;
    }

    public void printTicket() {

        System.out.println("\n==========================================");
        System.out.println("           TRAVELFLOW E-TICKET");
        System.out.println("==========================================");

        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Transport Type : " + transportType);
        System.out.println("Passenger Name : " + passenger.getName()
                + " (" + passenger.getAge() + ")");
        System.out.println("Route          : " + source + " -> " + destination);

        if (transportType.equalsIgnoreCase("Train")) {

            System.out.println("Train No       : " + vehicleNo);

            if (seat != null)
                System.out.println("Berth Allocated: " + seat.getSeatNo() + " - " + berth);
            else
                System.out.println("Berth Allocated: " + berth);

            System.out.println("Coach          : " + coach);
        }

        else if (transportType.equalsIgnoreCase("Flight")) {

            System.out.println("Flight No      : " + vehicleNo);

            if (flightSeat != null)
                System.out.println("Seat Allocated : " + flightSeat);
            else
                System.out.println("Seat Allocated : Not Assigned");
        }

        else {

            System.out.println("Bus No         : " + vehicleNo);

            if (seat != null)
                System.out.println("Seat No        : " + seat.getSeatNo());
            else
                System.out.println("Seat No        : Not Assigned");
        }

        System.out.println("Fare           : ₹" + fare);
        System.out.println("Status         : CONFIRMED");

        System.out.println("==========================================");
    }
}