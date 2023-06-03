package ACO;

import DSS.EvaporationOfPheromone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Miguel {
    Map<Integer, Hashtable<Integer, Float>> pheromones;
    int totalWeights;

    public Miguel(int numberOfNodes, int totalWeights) {
        this.pheromones = new HashMap<>(numberOfNodes);
        this.totalWeights = totalWeights;
    }

    public void setPheromones(int[] path, float gamma, int sumOfWeights) {
        int currentNode = path[0];
        int nextNode = path[1];
        float pheromone =  gamma * sumOfWeights / totalWeights;
        for (int i = 0; i < path.length - 1; i++) {
            currentNode =  path[i];
            nextNode =  path[i + 1];

            Hashtable<Integer, Float> pheromonesFromNode = pheromones.get(currentNode);
            pheromonesFromNode.put(nextNode, pheromonesFromNode.get(nextNode) + pheromone);
            pheromones.put(currentNode, pheromonesFromNode);
        }
    }

    public Hashtable<Integer, Float> getPheromone(int currentNode) {
        return new Hashtable<>(pheromones.get(currentNode));
    }
}