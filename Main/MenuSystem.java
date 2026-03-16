package Main;

import java.util.*;

import Booking.*;
import Search.*;
import User.*;

public class MenuSystem {

    private static Map<String, Seat[]> vehicleSeats = new HashMap<>();
    private static Map<String, Booking> bookingsById = new HashMap<>();

    private static BookingQueue bookingQueue = new BookingQueue();
    private static UndoStack undoStack = new UndoStack();

    private static BookingManager bookingManager = new BookingManager();
    private static SeatAllocator seatAllocator = new SeatAllocator();

    private static RefundPriorityQueue refundPQ = new RefundPriorityQueue();
    private static RefundProcessor refundProcessor = new RefundProcessor();
    private static RefundManager refundManager = new RefundManager();

    private static PassengerList passengerList = new PassengerList();

    public static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================================");
            System.out.println("      TRAVELFLOW SMART BOOKING SYSTEM");
            System.out.println("==============================================");

            System.out.println("1. New Booking (Bus/Train/Flight)");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Process Refund Queue");
            System.out.println("4. Undo Last Action");
            System.out.println("5. Demo Search (Linear/Binary)");
            System.out.println("6. Exit");

            System.out.print("Enter Choice : ");

            int choice = readInt(sc);

            switch (choice) {

                case 1 -> newBooking(sc);
                case 2 -> cancelBooking(sc);
                case 3 -> processRefunds();
                case 4 -> undoStack.undo();
                case 5 -> demoSearch();
                case 6 -> { return; }

                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void newBooking(Scanner sc) {

        System.out.println("\nSelect Transport Mode:");
        System.out.println("1. Bus");
        System.out.println("2. Train");
        System.out.println("3. Flight");

        int mode = readInt(sc);

        System.out.print("Enter Source : ");
        String source = sc.nextLine();

        System.out.print("Enter Destination : ");
        String dest = sc.nextLine();

        switch (mode) {

            // BUS BOOKING
            case 1 -> {

                System.out.println("B101   Super Luxury   10:00 AM   ₹450");
                System.out.println("B102   Express        12:30 PM   ₹350");

                System.out.print("Select Bus Number : ");
                String vehicleNo = sc.nextLine();

                int fare = vehicleNo.equalsIgnoreCase("B102") ? 350 : 450;

                vehicleSeats.putIfAbsent(vehicleNo, createSeats(20));

                Seat[] seats = vehicleSeats.get(vehicleNo);

                seatAllocator.printLayout(seats);

                System.out.print("Enter Seat Number : ");
                int seatNo = readInt(sc);

                Seat seat = seats[seatNo - 1];

                Booking booking =
                        completeBooking(sc, "Bus", vehicleNo, source, dest, fare, seat);

                booking.printTicket();

                bookingsById.put(booking.getBookingId(), booking);
            }

            // TRAIN BOOKING
            case 2 -> {

                System.out.println("T201   Charminar Exp   08:30 PM   ₹650");
                System.out.println("T202   Godavari Exp    11:15 PM   ₹720");

                System.out.print("Select Train Number : ");
                String vehicleNo = sc.nextLine();

                int fare = vehicleNo.equalsIgnoreCase("T202") ? 720 : 650;

                int seatNo = new Random().nextInt(72) + 1;
                String coach = "S" + (new Random().nextInt(3) + 1);

                String[] berthTypes = {
                        "Lower","Middle","Upper","Side Lower","Side Upper"
                };

                String berth = berthTypes[new Random().nextInt(berthTypes.length)];

                System.out.println("Allocated Seat : "
                        + coach + "-" + seatNo + " " + berth);

                Seat seat = new Seat(seatNo);

                Booking booking =
                        completeBooking(sc,"Train",vehicleNo,source,dest,fare,seat);

                booking.setTrainDetails(coach, berth);

                booking.printTicket();

                bookingsById.put(booking.getBookingId(), booking);
            }

            // FLIGHT BOOKING
            case 3 -> {

                System.out.println("F301   Indigo       09:45 AM   ₹4500");
                System.out.println("F302   Air India    02:30 PM   ₹5200");

                System.out.print("Select Flight Number : ");
                String vehicleNo = sc.nextLine();

                int fare = vehicleNo.equalsIgnoreCase("F302") ? 5200 : 4500;

                int row = new Random().nextInt(30) + 1;
                char seatChar = (char) ('A' + new Random().nextInt(6));

                String flightSeat = row + "" + seatChar;

                System.out.println("Allocated Seat : " + flightSeat);

                Seat seat = new Seat(row);

                Booking booking =
                        completeBooking(sc,"Flight",vehicleNo,source,dest,fare,seat);

                booking.setFlightSeat(flightSeat);

                booking.printTicket();

                bookingsById.put(booking.getBookingId(), booking);
            }
        }
    }

    private static Booking completeBooking(Scanner sc,
                                           String type,
                                           String vehicleNo,
                                           String source,
                                           String dest,
                                           int fare,
                                           Seat seat) {

        System.out.print("Enter Passenger Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Age : ");
        int age = readInt(sc);

        Passenger p = new Passenger(name, age);

        passengerList.addPassenger(name);

        Booking booking =
                bookingManager.createBooking(type, vehicleNo, source, dest, p, seat, fare);

        bookingQueue.addBooking(booking.getBookingId());
        bookingQueue.processBooking();

        undoStack.pushAction("BOOK " + booking.getBookingId());

        return booking;
    }

    private static void cancelBooking(Scanner sc) {

        System.out.print("Enter Booking ID : ");
        String id = sc.nextLine();

        Booking b = bookingsById.get(id);

        if (b == null) {
            System.out.println("Booking not found");
            return;
        }

        b.getSeat().freeSeat();

        int priority = new Random().nextInt(3) + 1;

        refundManager.initiateRefund(id, priority);
        refundPQ.addRequest(new RefundRequest(id, priority));

        System.out.println("Booking cancelled");
    }

    private static void processRefunds() {

        if (refundPQ.isEmpty()) {
            System.out.println("No refunds pending");
            return;
        }

        RefundRequest r = refundPQ.processRefund();

        refundProcessor.processRefund(r.getBookingId());
    }

    private static void demoSearch() {

        int[] arr = {10,20,30,40,50};
        int key = 40;

        System.out.println("Linear Search index : "
                + SearchManager.linearSearch(arr,key));

        System.out.println("Binary Search index : "
                + SearchManager.binarySearch(arr,key));
    }

    private static Seat[] createSeats(int n) {

        Seat[] seats = new Seat[n];

        for(int i=0;i<n;i++)
            seats[i] = new Seat(i+1);

        return seats;
    }

    private static int readInt(Scanner sc) {

        while(!sc.hasNextInt()) {
            sc.next();
        }

        int v = sc.nextInt();
        sc.nextLine();

        return v;
    }
}