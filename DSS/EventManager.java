package DSS;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.*;

import ACO.*;
import java.util.Queue;
import java.util.Random;

public class EventManager implements Event {

    double time;
    double Constante;
    int delta;
    AntMove eventoFormigas;
    EvaporationEvent evaporation;
    Queue<NextEvent<AntInterface,MiguelInter>> PEC;

    public EventManager() {

        PEC = new PriorityQueue<>();
        this.eventoFormigas = new AntMove();

    }

    public void AddPheromonesEvent(MiguelInter idk, int time){}


    public void execute() {

        NextEvent<AntInterface,MiguelInter> aux;
        double a_ij;
        aux = PEC.peek();

        if (aux.getObjeto() == null) {
            evaporation.execute(null);
            aux.SetTimeStamp(this.time + this.Constante);
            this.time = PEC.poll().getTime();
            PEC.add(aux);

        }

        a_ij = eventoFormigas.execute(aux.GeralObject);
        if (a_ij == 0) {
            System.out.println("cheguei AQUI");
        } else {
            PEC.poll();
            this.time = aux.getTime();
            Random random = new Random();
            aux.SetTimeStamp(this.time + (1 - Math.exp(-random.nextDouble() / delta * a_ij)));

            PEC.add(aux);
        }
    }

    public double getTime() {

        return this.time;

    }

    public void GenerateQueue(ArrayList<AntInterface> obj) {

        NextEvent<AntInterface,MiguelInter> aux = null;

        for (AntInterface x : obj) {

            aux = new NextEvent<AntInterface,MiguelInter>(x);
            PEC.add(aux);

        }

    }

    public void PrintQueue() {
        while (!PEC.isEmpty()) {
            System.out.println(PEC.poll().TimeStampEvento);
        }

    }

}