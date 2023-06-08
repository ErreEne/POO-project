package DSS;

import java.util.ArrayList;
import java.util.PriorityQueue;
import ACO.*;
import java.util.Queue;

/**
 * Class that represents the event manager
 */
public class EventManager implements Event, EventForObserver, EventForAnt<AntInterface> {

    double timelimit;
    int delta;
    int constante;
    double timeconstant;
    Queue<EventTypes> PEC;
    ArrayList<Integer> Bestpath;
    ArrayList<MiguelInter> TodasAsFeromonasCriadas;
    AntColonyInterface Colonia;

    /**
     * @param colonia the ant colony
     * @param maxTime the maximum time of simulation
     */
    public EventManager(AntColonyInterface colonia, double maxTime) {
        PEC = new PriorityQueue<>();
        this.Colonia = colonia;
        this.timelimit = maxTime;
        TodasAsFeromonasCriadas = new ArrayList<>();

    }

    /**
     * Add to the Priority Queue the events of the simulation
     * @param newFeromonas the new pheromones
     * @param timestamp   the time of the event
     */
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

    /**
     * Set path to a better path discovered
     * @param path the path to be altered
     */
    public void alterarPath(ArrayList<Integer> path) {

        this.Bestpath = path;
        System.out.println(path);

    }

    public void print() {

        System.out.println("AquiTensDeMeterOPRintcrazy");

    }

    /**
     * Simulate the events
     */
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

    /**
     * Generate the events of the simulation
     */
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