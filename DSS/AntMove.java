package DSS;

import ACO.Ant;
import ACO.SortedPriorityQueue;

public class AntMove implements Comparable<AntMove> {
    SortedPriorityQueue<Event> queue = new SortedPriorityQueue<>();
    public AntMove(double time){
        queue.insertQueue(new AntMove(10));

    }



    public void execute() {
        // Chamar coisas na Ant que façam-na mover para o próximo nó e depois gerar o timestamp do próximo evento
    };
}
