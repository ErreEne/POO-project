package ACO;

import DSS.EvaporationOfPheromone;

import java.util.*;

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
        initializePheromones(numberOfNodes);
    }

    public void initializePheromones(int numberOfNodes){
        for (int i = 0; i < numberOfNodes - 1; i++) {
            Hashtable<Integer, Float> pheromonesFromNode = new Hashtable<>();
            for (int j = 0; j < numberOfNodes - 1; j++) {
                pheromonesFromNode.put(j, (float) 0);
            }
            pheromones.put(i, pheromonesFromNode);
        }
    }

    public void setPheromones(ArrayList<Integer> path, int sumOfWeights) {
        int currentNode;
        int nextNode;
        float pheromone =  gamma * sumOfWeights / totalWeights;
        for (int i = 0; i < path.size() - 1; i++) {
            currentNode =  path.get(i);
            nextNode =  path.get(i + 1);

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