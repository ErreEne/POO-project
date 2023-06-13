/**
 * The DSS package contains classes and interfaces related to Discrete Stochastic Simulation (DSS).
 * DSS is a simulation method that is used to analyze the behavior of a system over time.
 *
 */
package DSS;

import ACO.MiguelInter;

import java.util.Random;

/**
 * Class that presents the Evaporation Event of the pheromones
 */
class EvaporationEvent extends EventTypes {

    /**
     * The pheromone that will be evaporated
     */
    private final MiguelInter feromonas;
    /**
     * The time constant of the evaporation
     */
    private final double timeConstant;

    /**
     * @param time        time that the event will be executed
     * @param newFeromona pheromone that will be evaporated
     * @param teta        time constant of the evaporation
     */
    EvaporationEvent(double time, MiguelInter newFeromona, double teta) {

        super(time);
        this.feromonas = newFeromona;
        this.timeConstant = teta;

    }

    /**
     * @param newTime new time of the event execution
     */
    public void setTime(double newTime) {

        this.timestamp = newTime;
    }

    /**
     * Method that increases the number of events
     *
     * @param eventNumber number of events
     * @return the number of events
     */
    public int eventTypeIncrase(int eventNumber) {
        if (feromonas.getPheromone() > 1)
            return eventNumber + 1;

        return eventNumber;
    }

    /**
     * Method that executes the event
     */
    @Override
    public void execute() {
        Random rand = new Random();
        this.setTime(timestamp + (-timeConstant) * Math.log(1 - rand.nextDouble()));

        if (feromonas.getPheromone() > 1) {
            feromonas.evaporationOfPheromone();
        }
    }

}