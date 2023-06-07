package DSS;

import java.util.ArrayList;
import java.util.PriorityQueue;
import ACO.*;
import java.util.Queue;

public class EventManager implements Event, EventForObserver, EventForAnt<AntInterface> {

    double timelimit;
    int delta;
    int constante;
    double timeconstant;
    Queue<EventTypes> PEC;
    ArrayList<Integer> Bestpath;
    ArrayList<MiguelInter> TodasAsFeromonasCriadas;
    AntColonyInterface Colonia;

    public EventManager(AntColonyInterface colonia, double maxTime) {
        PEC = new PriorityQueue<>();
        this.Colonia = colonia;
        this.timelimit = maxTime;
        TodasAsFeromonasCriadas = new ArrayList<>();

    }

    public void addQueueNewEvent(MiguelInter newFeromonas, int timestamp) {

        for (MiguelInter x : this.TodasAsFeromonasCriadas) {
            if (x == newFeromonas) {
                return;
            }
        }
        TodasAsFeromonasCriadas.add(newFeromonas);
        EventTypes aux = new EvaporationEvent(timestamp + constante, newFeromonas, timeconstant);
        PEC.add(aux);

    }

    public void alterarPath(ArrayList<Integer> path) {

        this.Bestpath = path;
        System.out.println(path);

    }

    public void print() {

        System.out.println("AquiTensDeMeterOPRintcrazy");

    }

    public void simular() {
        double Timestamp = 0;
        this.GenerateQueue();
        EventTypes aux;
        while (Timestamp <= this.timelimit) {
            if (!PEC.isEmpty()) {

                aux = PEC.poll();
                Timestamp = aux.getTime();
                aux.execute();
                if (aux.getTime() <= this.timelimit) {
                    PEC.add(aux);
                }
            } else
                break;

        }

    }

    public void GenerateQueue() {

        EventTypes aux;
        EventTypes aux1 = new ObservationEvent(timelimit / 20, this);

        PEC.add(aux1);

        for (AntInterface x : Colonia.getAnts()) {

            aux = new AntMove(0, x, this);
            PEC.add(aux);

        }

    }

}