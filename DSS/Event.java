package DSS;

import java.util.ArrayList;

public interface Event<objeto> {

    public abstract objeto execute();
    public abstract double getTime();
    public void GenerateQueue(ArrayList<objeto> obj, int Interval);

}
