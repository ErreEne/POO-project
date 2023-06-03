package DSS;

import DSS.AntMove;
import DSS.EvaporationOfPheromone;

import java.util.Random;

public class PEC<T> {
    SortedPriorityQueue<AntMove> queue = new SortedPriorityQueue<>();

    public PEC() {
    }

    public void addEvPEC(AntMove ev) {
        queue.insertQueue(ev);
    }

    public Event nextEvPEC() {
        return queue.getFirst();
    }

    public double calcTimeStamp(double delta, double a_ij) {
        Random random = new Random();
        return (1 - Math.exp(-random.nextDouble() / delta * a_ij));
    }
}
