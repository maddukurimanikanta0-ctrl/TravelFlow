package Search;

import Main.Route;
import java.util.List;

public class SearchManager {
    private RouteList routeList;

    public SearchManager(RouteList routeList) {
        this.routeList = routeList;
    }

    public void search(String source, String destination) {
        long start = System.nanoTime();
        List<Route> results = routeList.searchLinear(source, destination);
        long end = System.nanoTime();

        System.out.println("Results:");
        for (Route r : results) {
            System.out.println(r.getRouteCode() + " - ₹" + r.getPrice());
        }

        System.out.println("Time Taken: " + (end - start) + " ns");
    }
}
