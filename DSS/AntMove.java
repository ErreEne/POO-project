package DSS;

import ACO.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the event of a ant move
 */
public class AntMove extends EventTypes {
    AntInterface Fomiga;
    float delta = 0.2F;
    EventForAnt<AntInterface> teste;

    /**
     * @param time    time that the event will be executed??
     * @param formiga ant that will move
     * @param test    event that will be executed ??
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
        if (Fomiga.checkIfEndedPath()) {
            teste.alterarPath(Fomiga.getPath(), Fomiga.PathCost());
            for (int i = 0; i < Fomiga.getPath().size() - 1; i++) {

                teste.addQueueNewEvent(timestamp, Fomiga.getPath().get(i), Fomiga.getPath().get(i + 1));

            }
            teste.addQueueNewEvent(timestamp, Fomiga.getPath().get(Fomiga.getPath().size() - 1),
                    Fomiga.getPath().get(0));
            Fomiga.resetPath();

        }
        this.setTime(timestamp + (-mean) * Math.log(1 - rand.nextDouble()));

    };

}
