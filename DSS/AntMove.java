package DSS;

import ACO.*;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;

public class AntMove implements Event, AntMoveInterface {
    double timestamp = 0;
    Queue<NextEvent<AntInterface>> PEC;

    public AntMove() {
        /*
         * Random random = new Random();
         * timestamp = (1 - Math.exp(-random.nextDouble() / delta * a_ij));
         */
        PEC = new PriorityQueue<>();
    }

    public double getTime() {
        return this.timestamp;
    }

    public void GetAnts(AntInterface ant){}

    public void GenerateQueue(AntInterface obj) {

    }

    @Override
    public void execute() {
        // Chamar coisas na Ant que façam-na mover para o próximo nó e depois gerar o
        // timestamp do próximo evento
    };

}
