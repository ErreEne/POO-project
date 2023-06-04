package ACO;

import DSS.EvaporationOfPheromone;

import java.util.*;

public class Miguel implements MiguelInter {
    float pheromone;
    int totalWeights;
    float gamma;
    float ro;

    public Miguel(int numberOfNodes, int totalWeights, float gamma, float ro) {
        this.pheromone = 0;
        this.totalWeights = totalWeights;
        this.gamma = gamma;
        this.ro = ro;
    }

    public void setPheromone(int sumOfWeights) {
        this.pheromone += gamma * totalWeights / sumOfWeights;
    }

    public float getPheromone() {
        return pheromone;
    }

    public void evaporationOfPheromone() {
        if (pheromone > 0) {
            this.pheromone -= ro;
        } else {
            this.pheromone = 0;
        }
    }
}