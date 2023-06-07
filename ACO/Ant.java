package ACO;

import java.util.*;

public class Ant implements AntInterface {
    public ArrayList<Integer> path;
    private AntColony colony;
    public int currentNode;
    public int PathEnded;

    public Ant(AntColony Antcolony) {
        this.colony = Antcolony;
        this.path = new ArrayList<>();
        path.add(colony.nest_node);
        this.currentNode = colony.nest_node;
        this.PathEnded = 0;
    }

    public int cuNode() {
        return this.currentNode;
    }

    public ArrayList<Integer> getPath() {
        return this.path;
    }

    public Hashtable<Integer, Float> getNormalizedProbabilities() {
        Hashtable<Integer, Float> probability = new Hashtable<>();
        float sum = 0;
        float Ci = 0;
        float Cijk;

        Hashtable<Integer, Integer> weights = getPossibleWeights();
        if (weights.isEmpty()) {
            return null;
        }

        Hashtable<Integer, Miguel> pheromone = getPossiblePheromones();
        if (pheromone.isEmpty()) {
            return null;
        }

        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            Ci += ((colony.alpha + pheromone.get(entry.getKey()).getPheromone()) / (colony.beta + entry.getValue()));
        }

        for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
            Cijk = ((colony.alpha + pheromone.get(entry.getKey()).getPheromone()) / (colony.beta + entry.getValue()));
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

    @Override
    public int move() {
        Random rand = new Random();
        int newNode;
        int previousNode = currentNode;
        int flag = 0;

        Hashtable<Integer, Float> NormalizedProbabilities = getNormalizedProbabilities();
        if (NormalizedProbabilities == null) {
            Hashtable<Integer, Integer> possibleWeights = getWeights();
            Set<Integer> keys = possibleWeights.keySet();

            newNode = (int) keys.toArray()[rand.nextInt(keys.size())];
            if (!checkIfEndedPath(newNode)) {
                removeLoop(newNode);
            }
            flag = 1;
        } else {
            newNode = chooseNode(NormalizedProbabilities);
        }

        if (checkIfEndedPath(newNode)) {
            // agendar evaporação para aqui a X tempo (X definido no input)
            setPheromones(path);
            PathEnded = 1;
            return 0;
        }
        if (flag == 0) {
            addToList(this.path, newNode);
        }
        currentNode = newNode;
        return getCost(currentNode, previousNode);
    }

    @Override
    public void resetPath() {
        path.clear();
        path.add(colony.nest_node);
        currentNode = colony.nest_node;
        PathEnded = 0;
    }

    public void setPheromones(ArrayList<Integer> path) {
        colony.setPheromones(path);
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < path.size(); i++) {
            if (nodeToRemove == getFromList(path, i) && flag == 0) {
                flag = 1;
            } else if (flag == 1) {
                removeFromList(path, i);
                i--;
            }
        }
    }

    public Boolean checkIfEndedPath(int node) {
        return getSize(path) == colony.totalVertex && node == colony.nest_node;
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

    public Hashtable<Integer, Miguel> getPheromones() {
        return colony.getPheromonesFromNode(currentNode);
    }

    public Hashtable<Integer, Miguel> getPossiblePheromones() {
        Hashtable<Integer, Miguel> pheromones = getPheromones();
        Hashtable<Integer, Miguel> possiblePheromones = (Hashtable<Integer, Miguel>) pheromones.clone();

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

    public int getCost(int a, int b) {
        return colony.getCost(a, b);
    }
}
