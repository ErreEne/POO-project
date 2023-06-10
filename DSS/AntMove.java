package DSS;

import ACO.*;

import java.util.Random;

/**
 * Class that represents the event of an ant move
 */
public class AntMove extends EventTypes {
    AntInterface formiga;
    float delta = 0.2F;
    EventForAnt<AntInterface> test;

    /**
     * @param time    time that the event will be executed??
     * @param formiga ant that will move
     * @param test    event that will be executed ??
     */
    public AntMove(double time, AntInterface formiga, EventForAnt<AntInterface> test) {
        super(time);
        this.formiga = formiga;
        this.test = test;
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
            test.alterarPath(formiga.getPath(), formiga.PathCost());
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
