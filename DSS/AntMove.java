package DSS;

import ACO.*;

import java.util.Random;

public class AntMove extends EventTypes {
    AntInterface Fomiga;
    float delta;
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
        System.out.println("alo");
        Random rand = new Random();
        double mean = delta*aij;
        this.setTime(timestamp + (-mean)*Math.log(1-rand.nextDouble()));
        if (aij == 0) {

            teste.alterarPath(Fomiga.getPath());

        }

    };

}
