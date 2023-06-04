package DSS;

import ACO.*;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class AntMove implements Event<AntInterface> {
    double timestamp = 0;
    Queue<NextEvent<AntInterface>> PEC;
    float delta;

    public AntMove() {

        PEC = new PriorityQueue<>();
    }

    public double getTime() { // current time
        return this.timestamp;
    }

    public void GenerateQueue(ArrayList<AntInterface> obj) {

        NextEvent<AntInterface> aux = new NextEvent<AntInterface>(null);

        for (AntInterface x : obj) {

            aux = new NextEvent<AntInterface>(x);
            PEC.add(aux);

        }

    }

    @Override
    public AntInterface execute() {
        NextEvent<AntInterface> aux;
        int a_ij;

        aux = PEC.peek();
        if(aux.GeralObject == null){

            return null;

        }
        a_ij = aux.GeralObject.move();
        if (a_ij == 0) {
            return aux.GeralObject;
        } else {
            PEC.poll();
            this.timestamp = aux.getTime();
            Random random = new Random();
            aux.SetTimeStamp(this.timestamp + (1 - Math.exp(-random.nextDouble() / delta * a_ij)));

            PEC.add(aux);

            return aux.GeralObject;
        }

    };

}
