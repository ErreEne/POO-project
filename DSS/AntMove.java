package DSS;

import ACO.*;

import java.util.Random;

public class AntMove implements Event, Comparable<AntMove> {
    double timestamp = 0;

    public AntMove(double time, double delta, double a_ij) {
        Random random = new Random();
        timestamp = (1 - Math.exp(-random.nextDouble() / delta * a_ij));
    }

    public int compareTo(AntMove other) {
        // Compare based on the values
        return Double.compare(this.timestamp, other.timestamp);
    }

    @Override
    public void execute() {
        // Chamar coisas na Ant que façam-na mover para o próximo nó e depois gerar o
        // timestamp do próximo evento
    };

}
