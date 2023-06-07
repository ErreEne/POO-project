package DSS;

import java.util.ArrayList;

import ACO.MiguelInter;

public interface EventForAnt<Swarm> {

    public abstract void alterarPath(ArrayList<Integer> path);
    public abstract void addQueueNewEvent(MiguelInter novaFeromona, int timestamp);

}
