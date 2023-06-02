package ACO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import GrafoPack.Grafo;
import GrafoPack.GrafoInterface;

public class Ant {
    public ArrayList<Integer> path;
    private AntColony colony;

    public Ant(AntColony Antcolony) {
        this.colony = Antcolony;
        this.path = new ArrayList<Integer>();
    }

    public float[] getNormalizedProbabilities(int currentNode) {
        Hashtable<Integer, Integer> weights = colony.getWeightsFromNode(currentNode);

        float[] pheromone = colony.getPheromonesFromNode(currentNode);

        float[] probability = new float[colony.tamanhoMax];
        float sum = 0;
        float Ci = 0;
        float Cijk = 0;

        for (int i = 0; i < colony.tamanhoMax; i++) {
            Ci += ((colony.alpha + pheromone[i]) / (colony.beta + weights.get(i)));
        }

        for (int i = 0; i < colony.tamanhoMax; i++) {
            if (weights.get(i) == 0) {
                probability[i] = 0;
            } else {
                Cijk = ((colony.alpha + pheromone[i]) / (colony.beta + weights[i]));
                probability[i] = Cijk / Ci;
            }
            sum += probability[i];
        }

        for (int i = 0; i < colony.tamanhoMax; i++) {
            probability[i] = probability[i] / sum;
        }

        return probability;
    }

    public int chooseNode(float[] probability) {
        Random rand = new Random();
        float node = rand.nextFloat();
        float partialSum = 0;
        for (int i = 0; i < probability.length; i++) {
            partialSum += probability[i];
            if (node < partialSum) {
                return i;
            }
        }
        return probability.length;
    }

    public Boolean updatePath(int currentNode) {
        if (checkIfEndedPath()) {
            return true;
        }

        float[] NormalizedProbabilities = getNormalizedProbabilities(currentNode);
        int newNode = chooseNode(NormalizedProbabilities);

        int loop = checkLoop(newNode);
        if (loop == -1) { // loop
            removeLoop(newNode);
            return false;
        }
        addToList(this.path, newNode);

        return false;
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < getSize(this.path) - 1; i++) {
            if (i == nodeToRemove || flag == 1) {
                flag = 1;
                removeFromList(this.path, i);
            }
        }
    }

    public int checkLoop(int newNode) {
        for (int i = 0; i < this.path.size() - 1; i++) {
            if (getFromList(this.path, i) == newNode) {
                return i;
            }
        }
        return -1;
    }

    public Boolean checkIfEndedPath() {
        if (getSize(this.path) == colony.tamanhoMax) {
            for (int i = 0; i < colony.tamanhoMax; i++) {
                if (true) {
                    addToList(this.path, colony.nest_node);
                    return true;
                }
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