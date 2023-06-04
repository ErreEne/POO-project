package ACO;

import DSS.EvaporationOfPheromone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Miguel implements MiguelInter{
    Map<Integer, Hashtable<Integer, Float>> pheromones;
    int totalWeights;
    float gamma;
    float ro;

    public Miguel(int numberOfNodes, int totalWeights, float gamma, float ro) {
        this.pheromones = new HashMap<>(numberOfNodes);
        this.totalWeights = totalWeights;
        this.gamma = gamma;
        this.ro = ro;
    }

    public void setPheromones(int[] path, int sumOfWeights) {
        int currentNode;
        int nextNode;
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

    public void evaporationOfPheromone() {
        for (Map.Entry<Integer, Hashtable<Integer, Float>> entry : pheromones.entrySet()) {
            for (Map.Entry<Integer, Float> entry2 : entry.getValue().entrySet()) {
                if(entry2.getValue() <= 0) {
                    continue;
                }
                entry.getValue().put(entry2.getKey(), entry2.getValue() - ro);
            }
        }
    }
}