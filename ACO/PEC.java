package ACO;

import DSS.AntMove;
import DSS.EvaporationOfPheromone;

import java.util.Random;

public class PEC {
    SortedPriorityQueue<AntMove> queue = new SortedPriorityQueue<>();
    public PEC(){}


    public void addEvPEC(Event ev){
    }

    public Event nextEvPEC(){
    }

    public double calcTimeStamp(double delta, double a_ij){
        Random random = new Random();
        return (1 - Math.exp(-random.nextDouble()/delta * a_ij));
    }
}
