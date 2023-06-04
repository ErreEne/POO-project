package DSS;

import ACO.AntInterface;

import java.util.ArrayList;

public interface AntMoveInter extends Event<AntInterface> {

    public void GenerateQueue(ArrayList<AntInterface> obj);

}