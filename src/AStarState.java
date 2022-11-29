/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
import java.util.HashMap; //для работы с хешмапами
public class AStarState
{
    private final HashMap<Location, Waypoint> openedWaypoints = new HashMap<>();    //нестатическое (файнал) поле открытых вершин
    private final HashMap<Location, Waypoint> closedWaypoints = new HashMap<>();    //нестатическое (файнал) поле закрытых вершин
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private final Map2D map;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // COMPLETE:  Implement.
        Location minLocation = null; // если нет вершин вернет null
        float minCost = Float.MAX_VALUE;
        //Проверяем расстояние до каждой из вершин и ищем с минимальной стоимостью, возвращаем адрес
        for (Location key : openedWaypoints.keySet()) {
            float totalCost = openedWaypoints.get(key).getTotalCost();
            if (totalCost < minCost) {
                minCost = totalCost;
                minLocation = key;
            }
        }
        return openedWaypoints.get(minLocation);
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public void addOpenWaypoint(Waypoint newWP) // поменял на void
    {
        // COMPLETED:  Implement.
        Location location = newWP.getLocation();
        if (!openedWaypoints.containsKey(location) || newWP.getPreviousCost() < openedWaypoints.get(location).getPreviousCost()) {
            openedWaypoints.put(location, newWP);
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        //COMPLETED  Implement//
        return openedWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // COMPLETED:  Implement.
        Waypoint waypointToClose = openedWaypoints.remove(loc);
        if (waypointToClose != null) {
            closedWaypoints.put(loc, waypointToClose);
        }
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        // COMPLETED:  Implement.
        return closedWaypoints.containsKey(loc);
    }
}

