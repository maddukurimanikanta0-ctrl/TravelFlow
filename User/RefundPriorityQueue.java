package User;

import java.util.PriorityQueue;
import Main.RefundRequest;

public class RefundPriorityQueue {

    private PriorityQueue<RefundRequest> pq = new PriorityQueue<>();

    public void addRequest(RefundRequest r) {
        pq.add(r);
        System.out.println("[REFUND] Request queued (priority=" + r.getPriority() + ") for " + r.getBookingId());
    }

    public RefundRequest processRefund() {
        return pq.poll();
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }
}
