package DSS;

import ACO.MiguelInter;

/**
 *  Class that presents the Evaporation Event of the pheromones
 */
public class EvaporationEvent extends EventTypes {

    MiguelInter feromonas;
    double timeconstant;

    /**
     * @param time time that the event will be executed
     * @param newFeromona pheromone that will be evaporated
     * @param teta time constant of the evaporation
     */
    EvaporationEvent(int time, MiguelInter newFeromona, double teta) {

        super(time);
        this.feromonas = newFeromona;
        this.timeconstant = teta;

    }

    /**
     * @param newTime new time of the event execution
     */
    public void setTime(double newTime) {

        this.timestamp = newTime;
    }

    /**
     * Method that executes the event
     */
    @Override
    public void execute() {
        this.setTime(timestamp + timeconstant);

    }

}