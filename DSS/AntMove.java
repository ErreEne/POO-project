package DSS;

import ACO.*;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class AntMove implements EventTypes<AntInterface,Double>{
    double timestamp;
    Queue<NextEvent<AntInterface>> PEC;
    float delta;

    public AntMove() {
    }

    public double GetEventTypeTime() { // current time
        return this.timestamp;
    }

    @Override
    public Double execute(AntInterface a) {
        
        return (double)a.move();

    };

}
