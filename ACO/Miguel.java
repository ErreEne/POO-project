package ACO;

import DSS.EvaporationOfPheromone;

import java.util.Arrays;

public class Miguel {
    float[][] pheromones; // IMPLEMENTAR MAP MAP MAP
    int[] edgeInfo;
    int totalWeights;

    public Miguel(int numberOfNodes, int totalWeights) {
        for(int i = 0; i < numberOfNodes; i++) {
            for(int j = 0; j < numberOfNodes; j++) {
                assert false;
                this.pheromones[i][j] = 0;
            }
        }
        this.totalWeights = totalWeights;
    }

    public void setPheromones(float[] weights, int[] Path, float gamma) {
        float pheromone = 0;
        int sumOfWeights = 0;
        for (float weight : weights) {
            sumOfWeights += weight;
        }
        pheromone = (gamma * this.totalWeights) / sumOfWeights;
        for(int i = 0; i < Path.length - 1; i++) {
            this.pheromones[Path[i]][Path[i + 1]] += pheromone;
        }
    }

    public float[] getPheromone(int currentNode) {
        float[] pheromone = new float[currentNode];
        for(int i = 0; i < pheromones.length; i++) {
            if(pheromone[i] != 0) {
                pheromone[i] = this.pheromones[currentNode][i];
            }
        }
        return pheromone;
    }
}