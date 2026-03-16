package User;

public class PassengerList {

    private static class Node {
        String name;
        Node next;
        Node(String n){ name = n; }
    }

    private Node head;

    public void addPassenger(String name) {
        Node n = new Node(name);
        if (head == null) { head = n; return; }
        Node t = head;
        while (t.next != null) t = t.next;
        t.next = n;
    }
}
