package DSS;

import java.util.ArrayList;

import ACO.MiguelInter;

public interface EventForAnt<Swarm> {

    void alterarPath(ArrayList<Integer> path, int totalCost);

    void addQueueNewEvent(double timestamp, int Id1, int Id2);

}
