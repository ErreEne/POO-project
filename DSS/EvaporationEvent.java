package DSS;

import ACO.MiguelInter;

public class EvaporationEvent extends EventTypes {

    MiguelInter feromonas;
    double timeconstant;

    EvaporationEvent(int time, MiguelInter newFeromona, double teta) {

        super(time);
        this.feromonas = newFeromona;
        this.timeconstant = teta;

    }

    public void setTime(double newTime) {

        this.timestamp = newTime;
    }

    @Override
    public void execute() {
        this.setTime(timestamp + timeconstant);

    }

}