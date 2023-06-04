package DSS;

import ACO.*;

import java.util.Random;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;

public class AntMove implements AntEvent {
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

    public void GenerateQueue(ArrayList<AntInterface> obj) {

        NextEvent<AntInterface> aux = new NextEvent<AntInterface>(null);

        for(AntInterface x: obj){

            aux = new NextEvent<AntInterface>(x);
            PEC.add(aux);            

        }


    }

    @Override
    public void execute() {
        NextEvent<AntInterface> aux;

        aux = PEC.poll();
        aux.GeralObject.move();
        this.timestamp = aux.getTime();
        
        /*
         * meter aqui formula para alterar o tempo e alterar a timestamp no aux
         */
        //Random random = new Random();      // esta formula???? Falta input delta e a_ij
        //this.timestamp = this.timestamp + (1 - Math.exp(-random.nextDouble() / delta * a_ij));

         PEC.add(aux);

    };

}
