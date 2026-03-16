package Booking;

import Main.Seat;

public class SeatAllocator {

    public Seat[] createSeats(int count) {

        Seat[] seats = new Seat[count];

        for (int i = 0; i < count; i++) {
            seats[i] = new Seat(i + 1);
        }

        return seats;
    }

    public void printLayout(Seat[] seats) {

        System.out.println("\nBus Seat Layout (X = Booked)\n");

        int index = 0;

        for (int row = 0; row < 5; row++) {

            // Left side seats
            for (int col = 0; col < 2; col++) {

                printSeat(seats[index++]);
            }

            System.out.print("   "); // aisle

            // Right side seats
            for (int col = 0; col < 2; col++) {

                printSeat(seats[index++]);
            }

            System.out.println();
        }
    }

    private void printSeat(Seat s) {

        if (s.isBooked())
            System.out.print("[X] ");
        else
            System.out.print("[" + s.getSeatNo() + "] ");
    }
}