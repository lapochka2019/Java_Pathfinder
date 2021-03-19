import java.util.Collections;
import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    //HashMap  — это реализация структуры данных хэш-таблицы (пары ключ-значение, словарь) интерфейса Map
    HashMap<Location, Waypoint> OpenedWaypoint;//объявляем словарь в котором хронится ключ-положение и значение-путь
    HashMap<Location, Waypoint> ClosedWaypoint;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");
        OpenedWaypoint = new HashMap<>();//присваиваем вышесозданным переменным значение пустого "массива"
        ClosedWaypoint = new HashMap<>();

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
    public Waypoint getMinOpenWaypoint()//Этот метод возвращает количество точек в наборе открытых вершин
    {
        // TODO:  Implement.
        final Waypoint[] MinOpenWaypoint = new Waypoint[1];//final-нельзя наследовать//кладем все открытве точки
        final float[] j = new float[1];//массив с первого элемента
        j[0] = Float.MAX_VALUE;//кладем максимальное значение
        OpenedWaypoint.forEach(//для каждого элемента набора открытых вершин
                (k,v) -> {
                        if (v.getTotalCost() < j[0]) {//вызываем метод из вэйпоинт
                            MinOpenWaypoint[0] = v;
                            j[0] = v.getTotalCost();//стоимость пути
                        }
                });
        return MinOpenWaypoint[0];//
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
    public boolean addOpenWaypoint(Waypoint newWP)//
    {
        // TODO:  Implement.
      //есть ли открытые вершины для данного место положени? если нет, то доьавим новую клеточку
        if (OpenedWaypoint.get(newWP.getLocation()) == null) OpenedWaypoint.put(newWP.getLocation(), newWP);//null - ничто, put Добавление новой пары в HashMap
        //если есть вершин, но путь короче, то заменяем текущую вершину новой
        else if (newWP.getPreviousCost() < OpenedWaypoint.get(newWP.getLocation()).getPreviousCost())
            OpenedWaypoint.replace(newWP.getLocation(), newWP);//замещает первый операнд вторым

        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {

        return OpenedWaypoint.values().size();//возвращаем количество открытых путевых точек

    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)//Эта функция перемещает вершину из набора «открытых вершин» в набор
//«закрытых вершин»
    {
        // TODO:  Implement.
        ClosedWaypoint.put(loc, OpenedWaypoint.get(loc));// put Добавление новой пары в HashMap
        OpenedWaypoint.remove(loc);//удаление элемента

    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return ClosedWaypoint.containsKey(loc);//содержит ли список путевых точек необходимую нам?
        //Эта функция должна возвращать значение true, если указанное
        //местоположение встречается в наборе закрытых вершин, и false в противном
        //случае.
    }
}
