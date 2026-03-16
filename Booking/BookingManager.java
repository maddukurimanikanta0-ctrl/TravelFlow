package Booking;

import Main.*;
import java.util.ArrayList;

public class BookingManager {

    private ArrayList<Booking> history = new ArrayList<>();

    // Create booking
    public Booking createBooking(String transportType, String vehicleNo,
                                 String source, String dest,
                                 Passenger p, Seat seat, int fare) {

        if (seat == null || seat.isBooked()) {
            System.out.println("Seat not available.");
            return null;
        }

        seat.bookSeat();

        Booking booking = new Booking(
                transportType, vehicleNo, source, dest, p, seat, fare
        );

        history.add(booking);
        System.out.println("Booking Successful!");

        return booking;
    }

    // Cancel booking (merged CancellationManager)
    public void cancelBooking(Booking booking) {

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        booking.getSeat().freeSeat();
        history.add(booking);

        System.out.println("Booking Cancelled: " + booking.getBookingId());
    }

    // Show booking history (merged BookingHistory)
    public void showHistory() {

        System.out.println("\n=== BOOKING HISTORY ===");

        if (history.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        for (Booking b : history) {
            System.out.println(b);
        }
    }
}