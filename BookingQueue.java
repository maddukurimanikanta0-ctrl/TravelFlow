package Booking;

import java.util.LinkedList;
import java.util.Queue;

public class BookingQueue {

    private Queue<String> queue = new LinkedList<>();

    public void addBooking(String bookingId) {
        queue.add(bookingId);
        System.out.println("[QUEUE] Added booking request: " + bookingId);
    }

    public String processBooking() {
        if (queue.isEmpty()) {
            System.out.println("[QUEUE] No booking requests pending.");
            return null;
        }
        String id = queue.poll();
        System.out.println("[QUEUE] Processing booking request: " + id);
        return id;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
