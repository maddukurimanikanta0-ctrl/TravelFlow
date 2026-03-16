package User;

public class RefundManager {

    public void initiateRefund(String bookingId, int priority) {

        System.out.println("[REFUND] Refund initiated for " + bookingId +
                " with priority " + priority);
    }
}