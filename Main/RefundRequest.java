package Main;

public class RefundRequest implements Comparable<RefundRequest> {

    private String bookingId;
    private int priority; // lower = higher priority

    public RefundRequest(String bookingId, int priority) {
        this.bookingId = bookingId;
        this.priority = priority;
    }

    public String getBookingId() { return bookingId; }
    public int getPriority() { return priority; }

    @Override
    public int compareTo(RefundRequest o) {
        return Integer.compare(this.priority, o.priority);
    }
}
