package ACO;

import java.util.ArrayList;

public interface AntInterface {
    int move();
    void resetPath();
    int PathCost();
    ArrayList<Integer> getPath();
    boolean checkIfEndedPath();

}
