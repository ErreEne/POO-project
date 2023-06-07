package DSS;

import java.util.PriorityQueue;
import ACO.*;
import java.util.Queue;

public class EventManager implements Event {

    double timelimit;
    int delta;
    int constante;
    Queue<EventTypes> PEC;
    AntColonyInterface Colonia;

    public EventManager(AntColonyInterface colonia,double maxTime) {

        PEC = new PriorityQueue<>();
        this.Colonia = colonia;
        this.timelimit = maxTime;
        

    }

    public void addQueueNewEvent(MiguelInter newFeromonas, int timestamp) {

        EventTypes aux = new EvaporationEvent(timestamp + constante, newFeromonas);
        PEC.add(aux);

    }

    public void print() {

        System.out.println("AquiTensDeMeterOPRintcrazy");

    }

    public void simular() {
        double Timestamp = 0;
        this.GenerateQueue();
        EventTypes aux;
        while (Timestamp < this.timelimit) {
            System.out.println("alo");
            aux = PEC.poll();
            Timestamp = aux.getTime();
            aux.execute();
            if (aux.getTime() < this.timelimit) {
                PEC.add(aux);
            }

        }

    }

    public void GenerateQueue() {

        EventTypes aux;
        EventTypes aux1 = new ObservationEvent(timelimit / 20, this);

        PEC.add(aux1);

        for (AntInterface x : Colonia.getAnts()) {

            aux = new AntMove(0, x);
            PEC.add(aux);

        }

    }

}