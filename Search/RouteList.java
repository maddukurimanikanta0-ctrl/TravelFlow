package Search;

import Main.Route;
import java.util.*;

public class RouteList {
    private List<Route> routes = new ArrayList<>();

    public void addRoute(Route route) {
        routes.add(route);
    }

    // Linear Search
    public List<Route> searchLinear(String source, String destination) {
        List<Route> result = new ArrayList<>();
        for (Route r : routes) {
            if (r.getSource().equalsIgnoreCase(source) &&
                r.getDestination().equalsIgnoreCase(destination)) {
                result.add(r);
            }
        }
        return result;
    }

    // Sort by price (Binary Search ready)
    public void sortByPrice() {
        routes.sort(Comparator.comparingDouble(Route::getPrice));
    }

    public List<Route> getRoutes() { return routes; }
}
