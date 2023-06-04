package DSS;

import java.util.ArrayList;
import ACO.AntInterface;
import java.util.Queue;
import java.util.Random;

public class EventManager implements Event<AntInterface> {

    double time;
    double Constante;
    int delta;
    AntMove eventoFormigas;
    EvaporationEvent evaporation;
    Queue<NextEvent<AntInterface>> PEC;

    EventManager() {
    }

    public AntInterface execute() {

        NextEvent<AntInterface> aux;
        double a_ij;
        aux = PEC.peek();
        if (aux.GeralObject == null) {

            evaporation.execute(null);
            aux.SetTimeStamp(this.time + this.Constante);
            this.time = PEC.poll().getTime();
            PEC.add(aux);
            return null;

        }

        a_ij = eventoFormigas.execute(aux.GeralObject);
        if (a_ij == 0) {
            return aux.GeralObject;
        } else {
            PEC.poll();
            this.time = aux.getTime();
            Random random = new Random();
            aux.SetTimeStamp(this.time + (1 - Math.exp(-random.nextDouble() / delta * a_ij)));

            PEC.add(aux);

            return null;
        }
    }

    public double getTime() {

        return this.time;

    }

    public void GenerateQueue(ArrayList<AntInterface> obj, int Interval) {

        NextEvent<AntInterface> aux;

        for (AntInterface x : obj) {

            aux = new NextEvent<AntInterface>(x);
            PEC.add(aux);

        }

    }

}