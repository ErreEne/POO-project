package DSS;

import ACO.*;

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

    }

    @Override
    public void execute() {
        int aux = this.Fomiga.move();
        System.out.println("alo");
        this.setTime(this.timestamp + aux);
        if (aux == 0) {

            teste.alterarPath(Fomiga.getPath());

        }

    };

}
