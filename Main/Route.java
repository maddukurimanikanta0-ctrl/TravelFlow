package Main;

public class Route {
    private String source;
    private String destination;

    public Route(String s, String d) {
        this.source = s;
        this.destination = d;
    }

    public String getSource() { return source; }
    public String getDestination() { return destination; }
}
