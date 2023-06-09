package DSS;

import ACO.*;
import java.util.*;

/**
 * Class that represents the event manager
 */
public class EventManager implements Event, EventForObserver, EventForAnt<AntInterface> {

    double timelimit;
    int delta;
    double constante;
    int eevents;
    int mevents;
    double timeconstant;
    Queue<EventTypes> PEC;
    ArrayList<Integer> Bestpath;
    int BestPrice;
    ArrayList<MiguelInter> TodasAsFeromonasCriadas;
    AntColonyInterface Colonia;

    /**
     * @param colonia the ant colony
     * @param maxTime the maximum time of simulation
     */
    public EventManager(AntColonyInterface colonia, double maxTime, double timeconstant) {
        PEC = new PriorityQueue<>();
        this.timeconstant = timeconstant;
        this.Colonia = colonia;
        this.timelimit = maxTime;
        TodasAsFeromonasCriadas = new ArrayList<>();
        this.mevents = 0;
        this.eevents = 0;

    }

    /**
     * Add to the Priority Queue the events of the simulation
     * 
     * @param newFeromonas the new pheromones
     * @param timestamp    the time of the event
     */
    public void addQueueNewEvent(double timestamp, int id1, int id2) {

        MiguelInter aux1 = Colonia.getPheromones().get(id1).get(id2);

        for (MiguelInter x : this.TodasAsFeromonasCriadas) {
            if (x == aux1) {
                return;
            }
        }
        TodasAsFeromonasCriadas.add(aux1);
        EventTypes aux = new EvaporationEvent(timestamp + constante, aux1, timeconstant);
        PEC.add(aux);
        System.out.println("d");
    }

    /**
     * Set path to a better path discovered
     * 
     * @param path the path to be altered
     */
    public void alterarPath(ArrayList<Integer> path, int TotalPrice) {
        if (this.BestPrice > TotalPrice) {
            this.BestPrice = TotalPrice;
            try {
                this.Bestpath = (ArrayList<Integer>) path.clone();

            } catch (Exception e) {
            }
            System.out.println(path);
        } else if (this.BestPrice == 0) {
            this.BestPrice = TotalPrice;
            this.Bestpath = (ArrayList<Integer>) path.clone();

        }
    }

    public void print(double PresentTime) {
        System.out.println("Present instant: " + PresentTime);
        System.out.println("mevents: " + mevents);
        System.out.println("eevents: " + eevents);
        System.out.println("Best hamilton Cycle: " + Bestpath + " - " + BestPrice);

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
                if (aux instanceof AntMove) {
                    mevents++;
                } else if (aux instanceof EvaporationEvent) {
                    eevents++;
                }
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