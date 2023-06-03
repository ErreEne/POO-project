package DSS;

import ACO.Ant;
import ACO.SortedPriorityQueue;

public class AntMove implements Event, Comparable<AntMove> {
    double timestamp=0;

    public AntMove(double time, double delta, double a_ij){
        Random random = new Random();
        timestamp =  (1 - Math.exp(-random.nextDouble()/delta * a_ij));
    }

    @Override
    public int compareTo(AntMove other) {
        // Compare based on the values
        return Double.compare(this.timestamp, other.timestamp);
    }

    public void execute() {
        // Chamar coisas na Ant que façam-na mover para o próximo nó e depois gerar o timestamp do próximo evento
    };
}
