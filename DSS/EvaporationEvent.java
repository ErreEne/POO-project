package DSS;

import ACO.MiguelInter;

public class EvaporationEvent extends EventTypes {

    MiguelInter feromonas;

    EvaporationEvent(int time, MiguelInter newFeromona) {

        super(time);
        this.feromonas = newFeromona;

    }

    public void setTime(double newTime) {

    }

    @Override
    public void execute() {

    }

}