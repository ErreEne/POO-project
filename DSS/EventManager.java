package DSS;

import ACO.*;
import java.util.*;

/**
 * Class that represents the event manager
 */
public class EventManager implements EventSimulation, EventForObserver, EventForSwarm<AntInterface> {

    double timelimit;
    double constante;
    int eevents;
    int mevents;
    double timeconstant;
    Queue<EventTypes> PEC;
    ArrayList<Integer>[] Bestpath = new ArrayList[5];
    int[] BestPrice = new int[5];
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
     * @param timestamp the time of the event
     */
    public void addQueueNewEvent(double timestamp, int id1, int id2) {
        Random rand = new Random();
        MiguelInter aux1 = Colonia.getPheromones().get(id1).get(id2);

        for (MiguelInter x : this.TodasAsFeromonasCriadas) {
            if (x == aux1) {
                return;
            }
        }
        TodasAsFeromonasCriadas.add(aux1);
        EventTypes aux = new EvaporationEvent(timestamp + (-timeconstant) * Math.log(1 - rand.nextDouble()), aux1, timeconstant);
        PEC.add(aux);
    }

    /**
     * Set path to a better path discovered
     * 
     * @param path the path to be altered
     */
    public void alterarPath(int flag, ArrayList<Integer> path, int TotalPrice) {

        for (int l = 0; l < 5; l++) {
            if (this.Bestpath[l] != null)
                if (this.Bestpath[l].equals(path))
                    return;
        }

        for (int i = flag; i < 5; i++) {

            if (this.BestPrice[i] == 0 || this.BestPrice[i] > TotalPrice) {

                if (this.Bestpath[i] != null) {
                    alterarPath(i, this.Bestpath[i], BestPrice[i]);
                }
                this.Bestpath[i] = (ArrayList<Integer>) path.clone();
                this.BestPrice[i] = TotalPrice;

                break;
            }
        }

    }

    public void print(double PresentTime) {
        System.out.println("Present instant: " + PresentTime);
        System.out.println("mevents: " + mevents);
        System.out.println("eevents: " + eevents);
        System.out.println("Best hamilton Cycle: " + Bestpath[0] + " - " + BestPrice[0]);
        for (int i = 1; i < 5; i++)
            System.out.println("OldCycle: " + Bestpath[i] + " - " + BestPrice[i]);

        System.out.println(TodasAsFeromonasCriadas);
    }

    /**
     * Simulate the events
     */
    public void simular(int PQueueSize) {
        double Timestamp = 0;
        this.GenerateQueue(PQueueSize);
        EventTypes aux;
        while (Timestamp <= this.timelimit) {
            if (!PEC.isEmpty()) {

                aux = PEC.poll();
                if (aux instanceof AntMove) {
                    mevents = aux.eventTypeIncrase(mevents);
                } else if (aux instanceof EvaporationEvent) {
                    eevents = aux.eventTypeIncrase(eevents);
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
    public void GenerateQueue(int QueueSize) {
        PEC = new PriorityQueue<>(QueueSize);
        EventTypes aux;
        EventTypes aux1 = new ObservationEvent(timelimit / 20, this);

        PEC.add(aux1);

        for (AntInterface x : Colonia.getAnts()) {

            aux = new AntMove(0, x, this);
            PEC.add(aux);

        }

    }
}