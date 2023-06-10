package ACO;

import java.util.*;

public interface AntColonyInterface {

    ArrayList<Ant> getAnts();
    HashMap<Integer, Hashtable<Integer, Miguel>> getPheromones();
}
