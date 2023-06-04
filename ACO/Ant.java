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
        path.add(colony.nest_node);
        this.currentNode = colony.nest_node;
    }

    public Hashtable<Integer, Float> getNormalizedProbabilities(int currentNode) {
        Hashtable<Integer, Float> probability = new Hashtable<>();
        float sum = 0;
        float Ci = 0;
        float Cijk;

        Hashtable<Integer, Integer> weights = getPossibleWeights();
        if (weights.isEmpty()) {
            return null;
        }

        Hashtable<Integer, Float> pheromone = getPossiblePheromones();
        if(pheromone.isEmpty()){
            return null;
        }

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
        Random rand = new Random();
        int newNode;
        Hashtable<Integer, Float> NormalizedProbabilities = getNormalizedProbabilities(currentNode);
        if (NormalizedProbabilities == null) {
            Hashtable<Integer, Integer> possibleWeights = getPossibleWeights();
            newNode = possibleWeights.get(rand.nextInt(possibleWeights.size()));
            removeLoop(newNode);
        } else {
            newNode = chooseNode(NormalizedProbabilities);
        }

        addToList(this.path, newNode);
        currentNode = newNode;
        if (checkIfEndedPath()) {
            // agendar evaporação para aqui a X tempo  (X definido no input)
            setPheromones(path);
            path.clear();
            path.add(colony.nest_node);
            currentNode = colony.nest_node;
        }
    }

    public void setPheromones(ArrayList<Integer> path) {
        colony.setPheromones(path);
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < getSize(path); i++) {
            if (i == nodeToRemove || flag == 1) {
                flag = 1;
                removeFromList(this.path, i);
            }
        }
    }

    public Boolean checkIfEndedPath() {
        return getSize(path) == colony.ant_colony_size && currentNode == colony.nest_node;
    }

    public Hashtable<Integer, Integer> getPossibleWeights() {
        Hashtable<Integer, Integer> possibleWeights = getWeights();
        for (Integer integer : path) {
            possibleWeights.remove(integer);
        }
        return possibleWeights;
    }

    public Hashtable<Integer, Integer> getWeights() {
        return colony.getWeightsFromNode(currentNode);
    }

    public Hashtable<Integer, Float> getPheromones() {
        return colony.getPheromonesFromNode(currentNode);
    }

    public Hashtable<Integer, Float> getPossiblePheromones() {
        Hashtable<Integer, Float> possiblePheromones = getPheromones();
        for (Integer integer : path) {
            possiblePheromones.remove(integer);
        }
        return possiblePheromones;
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