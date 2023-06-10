package DSS;

import ACO.MiguelInter;

import java.util.Random;

/**
 * Class that presents the Evaporation Event of the pheromones
 */
public class EvaporationEvent extends EventTypes {

    MiguelInter feromonas;
    double timeConstant;

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
     * Method that executes the event
     */
    @Override
    public void execute() {
        Random rand = new Random();
        this.setTime(timestamp + (-timeConstant) * Math.log(1 - rand.nextDouble()));
        if (feromonas.getPheromone() > 1){
            System.out.println("Pheromones:" + feromonas.getPheromone());
            feromonas.evaporationOfPheromone();
        }
    }

}