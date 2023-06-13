/**
 * The ACO package contains classes and interfaces related to Ant Colony Optimization (ACO) algorithm.
 * ACO is an algorithm inspired by the foraging behavior of ants that can be used to solve optimization problems in
 * path finding and graph traversal.
 *
 */
package ACO;

import java.util.ArrayList;

/**
 * Interface that represents the ant
 */
public interface AntInterface {

    /**
     * @return the cost of the path
     */
    int move();

    void resetPath();

    /**
     * @return the cost of the path
     */
    int PathCost();

    /**
     * @return the path
     */
    ArrayList<Integer> getPath();

    /**
     * @return true if the ant has ended the path, false if not
     */
    boolean checkIfEndedPath();

    /**
     * @return the delta
     */
    float getDelta();

}
