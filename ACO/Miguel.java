package ACO;

import DSS.EvaporationOfPheromone;

import java.util.Arrays;

public class Miguel {
    float pheromone;
    float gamma;
    int[] edgeInfo;

    Miguel(int pheromone, int entrada, int saida, int gamma) {

        this.pheromone = 0;
        this.gamma = gamma;
        this.edgeInfo = new int[2];
        this.edgeInfo[0] = entrada;
        this.edgeInfo[1] = saida;

    }

    public float getPheromone() {
        return this.pheromone;
    }

    public void PheromoneLevel(float pheromone, float[] weight, int no) {
        float W = 0;
        for (int i = 0; i < weight.length; i++)
            W += weight[i];
        this.pheromone = W * this.gamma / weight[no]; // (W*y)/u(n)
    }

    public float setPheromone(float pheromone) {
        return this.pheromone = pheromone;
    }
}