package DSS;

import java.util.ArrayList;

import ACO.MiguelInter;

public interface EventForAnt<Swarm> {

    public abstract void alterarPath(ArrayList<Integer> path, int totalCost);

    public abstract void addQueueNewEvent(double timestamp, int Id1, int Id2);

}
