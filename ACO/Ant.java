package ACO;

import java.util.*;

import GrafoPack.Grafo;
import GrafoPack.GrafoInterface;

public class Ant {
    public ArrayList<Integer> path;
    private AntColony colony;
    public int currentNode;

    public Ant(AntColony Antcolony) {
        this.colony = Antcolony;
        this.path = new ArrayList<>();
        this.currentNode = colony.nest_node;
    }

    public Hashtable<Integer, Float> getNormalizedProbabilities(int currentNode) {
        Hashtable<Integer, Float> probability = new Hashtable<>();
        float sum = 0;
        float Ci = 0;
        float Cijk;
        Hashtable<Integer, Integer> weights = colony.getWeightsFromNode(currentNode);

        Hashtable<Integer, Float> pheromone = colony.getPheromonesFromNode(currentNode);

        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            Ci += ((colony.alpha + pheromone.get(entry.getKey())) / (colony.beta + entry.getValue()));
        }

        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            Cijk = ((colony.alpha + pheromone.get(entry.getKey())) / (colony.beta + entry.getValue()));
            probability.put(entry.getKey(), Cijk / Ci);
            sum += Cijk / Ci;
        }

        for (Map.Entry<Integer, Float> entry : probability.entrySet()) {
            probability.put(entry.getKey(), entry.getValue() / sum);
        }

        return probability;
    }

    public int chooseNode(Hashtable<Integer, Float> probability) {
        Random rand = new Random();
        float node = rand.nextFloat();
        float partialSum = 0;

        for (Map.Entry<Integer, Float> entry : probability.entrySet()) {
            partialSum += entry.getValue();
            if (node < partialSum) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public void move() {
        Hashtable<Integer, Float> NormalizedProbabilities = getNormalizedProbabilities(currentNode);
        int newNode = chooseNode(NormalizedProbabilities);

        int loop = checkLoop(currentNode);
        if (loop == -1) { // loop
            removeLoop(currentNode);
        } else {

            addToList(this.path, newNode);
            currentNode = newNode;
        }

        if (checkIfEndedPath()) {
            path.clear();
        }
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < getSize(path) - 1; i++) {
            if (i == nodeToRemove || flag == 1) {
                flag = 1;
                removeFromList(this.path, i);
            }
        }
    }

    public int checkLoop(int newNode) {
        for (int i = 0; i < getSize(path) - 1; i++) {
            if (getFromList(this.path, i) == newNode) {
                return i;
            }
        }
        return -1;
    }

    public Boolean checkIfEndedPath() {
        if (getSize(path) == colony.tamanhoMax) {
            for (int i = 0; i < colony.tamanhoMax; i++) {

                addToList(this.path, colony.nest_node);
                return false;

            }
            return true;
        }
        return false;
    }

    public int getFromList(ArrayList<Integer> getFrom, int index) {
        return getFrom.get(index);
    }

    public void removeFromList(ArrayList<Integer> listToRemove, int nodeToRemove) {
        listToRemove.remove(nodeToRemove);
    }

    public void addToList(ArrayList<Integer> listToAdd, int nodeToAdd) {
        listToAdd.add(nodeToAdd);
    }

    public int getSize(ArrayList<Integer> listToCheck) {
        return listToCheck.size();
    }

}