package ACO;

import DSS.EvaporationOfPheromone;

import java.util.Arrays;

public class Miguel {
    float pheromone=0;

    public float getPheromone() {
        return this.pheromone;
    }

    public void PheromoneLevel(float pheromone,float[] weight, float gamma, int no) {
        float W = 0;
        for (int i = 0; i < weight.length; i++) W += weight[i];
        this.pheromone = W*gamma/weight[no];   // (W*y)/u(n)
    }
    public float setPheromone(float pheromone){
        return this.pheromone = pheromone;
    }
}