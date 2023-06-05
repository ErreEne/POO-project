package DSS;

import java.util.*;
import ACO.MiguelInter;

public class NextEvent<QueueObject> implements Comparable<NextEvent<QueueObject>> {

    QueueObject GeralObject;
    double TimeStampEvento;
    boolean FlagObject;

    NextEvent(QueueObject o) {
        this(0);
        this.GeralObject = o;
        this.FlagObject = true;

    }

    NextEvent(int time) {

        this.TimeStampEvento = time;
        this.FlagObject = false;

    }

    public void SetTimeStamp(double newTime) {

        this.TimeStampEvento = newTime;

    }

    public boolean getInfoObject() {

        return this.FlagObject;

    }

    public double getTime() { // futuro
        return this.TimeStampEvento;
    }

    public QueueObject getObjeto() {
        return this.GeralObject;
    }

    @Override
    public int compareTo(NextEvent<QueueObject> aux) {
        return this.TimeStampEvento > aux.TimeStampEvento ? 1 : -1;
    }

}