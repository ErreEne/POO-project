package DSS;

import ACO.*;

import java.util.Random;

public class AntMove extends EventTypes {
    AntInterface Fomiga;
    float delta = 0.2F;
    EventForAnt<AntInterface> teste;

    public AntMove(double time, AntInterface formiga, EventForAnt<AntInterface> test) {
        super(time);
        this.Fomiga = formiga;
        this.teste = test;
    }

    @Override
    public void setTime(double newTime) {
        this.timestamp = newTime;
    }

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
