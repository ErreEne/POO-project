package DSS;

import ACO.*;

import java.util.Random;

/**
 * Class that represents the event of a ant move
 */
public class AntMove extends EventTypes {
    AntInterface Fomiga;
    float delta = 0.2F;
    EventForAnt<AntInterface> teste;

    /**
     * @param time     time that the event will be executed??
     * @param formiga   ant that will move
     * @param test event that will be executed ??
     */
    public AntMove(double time, AntInterface formiga, EventForAnt<AntInterface> test) {
        super(time);
        this.Fomiga = formiga;
        this.teste = test;
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
        int aij = Fomiga.move();
        Random rand = new Random();
        double mean = delta * aij;
        this.setTime(timestamp + (-mean) * Math.log(1 - rand.nextDouble()));
        if (Fomiga.checkIfEndedPath()) {


            teste.alterarPath(Fomiga.getPath());
            //teste.addQueueNewEvent(null, timestamp);
            Fomiga.resetPath();

        }

    };

}
