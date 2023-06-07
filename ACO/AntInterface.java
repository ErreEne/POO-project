package ACO;

import java.util.ArrayList;

public interface AntInterface {
    public abstract int move();
    public abstract void resetPath();
    public int cuNode();
    public abstract ArrayList<Integer> getPath();
    public boolean checkIfEndedPath();

}
