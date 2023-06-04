package DSS;

import ACO.AntInterface;

import java.util.ArrayList;

public interface Event <Object> {
    public abstract Object execute();
    public void GenerateQueue(ArrayList<Object> obj);
}
