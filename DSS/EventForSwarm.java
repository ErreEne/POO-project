package DSS;

import java.util.ArrayList;

public interface EventForSwarm<Swarm> {

    void alterarPath(int flag, ArrayList<Integer> path, int totalCost);

    void addQueueNewEvent(double timestamp, int Id1, int Id2);

}
