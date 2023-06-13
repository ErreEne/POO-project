/**
 * The DSS package contains classes and interfaces related to Discrete Stochastic Simulation (DSS).
 * DSS is a simulation method that is used to analyze the behavior of a system over time.
 *
 */
package DSS;

import ACO.*;

import java.util.Random;

/**
 * Class that represents the event of an ant move
 * @see EventTypes
 */
class AntMove extends EventTypes {

    /**
     * The ant interface
     */
    private final AntInterface formiga;
    /**
     * The time that the event will be executed
     */
    private final float delta;
    /**
     * The event that will be executed
     */
    private final EventForSwarm test;

    /**
     * @param time    time that the event will be executed??
     * @param formiga ant that will move
     * @param test    event that will be executed
     */
    AntMove(double time, AntInterface formiga, EventForSwarm test,float delta) {
        super(time);
        this.formiga = formiga;
        this.test = test;
        this.delta = delta;
    }

    /**
     * @param newTime new time of the event execution
     */
    @Override
    public void setTime(double newTime) {
        this.timestamp = newTime;
    }

    /**
     * Method that executes the event
     */
    @Override
    public void execute() {
        int aij = formiga.move();
        Random rand = new Random();
        double mean = delta * aij;
        if (formiga.checkIfEndedPath()) {

            test.alterarPath(0, formiga.getPath(), formiga.PathCost());
            for (int i = 0; i < formiga.getPath().size() - 1; i++) {

                test.addQueueNewEvent(timestamp, formiga.getPath().get(i), formiga.getPath().get(i + 1));

            }
            test.addQueueNewEvent(timestamp, formiga.getPath().get(formiga.getPath().size() - 1),
                    formiga.getPath().get(0));
            formiga.resetPath();
        }
        this.setTime(timestamp + (-mean) * Math.log(1 - rand.nextDouble()));
    }

}
