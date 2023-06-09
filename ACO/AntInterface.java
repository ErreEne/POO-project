package ACO;

import java.util.ArrayList;

public interface AntInterface {
    public abstract int move();
    public abstract void resetPath();
    public int PathCost();
    public abstract ArrayList<Integer> getPath();
    public boolean checkIfEndedPath();

}
