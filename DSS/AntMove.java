package DSS;

import ACO.*;

public class AntMove extends EventTypes {
    AntInterface Fomiga;
    float delta;

    public AntMove(double time, AntInterface formiga) {
        super(time);
        this.Fomiga = formiga;
    }

    @Override
    public void setTime(double newTime) {

    }

    @Override
    public void execute() {
        System.out.println("alo");
        this.setTime(this.timestamp + this.Fomiga.move());

    };

}
