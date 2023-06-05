package DSS;

import java.util.ArrayList;

import ACO.AntInterface;

public interface Event{

    public abstract AntInterface execute();
    public abstract double getTime();
    public void GenerateQueue(ArrayList<AntInterface> obj, int Interval);

}
