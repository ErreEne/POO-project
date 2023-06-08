package ACO;

import java.util.*;

public class Ant implements AntInterface {
    public ArrayList<Integer> path;
    private AntColony colony;
    public int currentNode;
    public int PathEnded;

    /**
     * Constructor of the Ant class
     * @param Antcolony - AntColony object that contains all the information about
     * */
    public Ant(AntColony Antcolony) {
        this.colony = Antcolony;
        this.path = new ArrayList<>();
        path.add(colony.nest_node);
        this.currentNode = colony.nest_node;
        this.PathEnded = 0;
    }

    /**
     * Get the current node of the ant
     * @return int - the current node of the ant
     */
    public int cuNode() {
        return this.currentNode;
    }

    /**
     * Get the path of the ant
     * @return ArrayList<Integer> - the path of the ant
     */
    public ArrayList<Integer> getPath() {
        return this.path;
    }

    /**
     * Calculate the normalized probabilities to go to the adjacent nodes
     * @return Hashtable<Integer, Float> - the normalized probabilities to go to another Node
     */
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

    /**
     * Choose the next node to go to
     * @param probability - Hashtable<Integer, Float> with the probabilities to go to another Node
     * @return  int - the node that the ant will go to or -1 if probability hashmap is empty ????????????????????????????????????????????????????
     */
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

    /**
     * Move the ant to the next node
     * @return int - the cost of the ant's path
     */
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
            return getCost(currentNode, previousNode);
        }
        if (flag == 0) {
            addToList(this.path, newNode);
        }
        currentNode = newNode;
        return getCost(currentNode, previousNode);
    }

    /**
     * Get the confiration if the ant ended the Hamiltonian cycle
     * @return boolean - true if the ant has ended the path, false otherwise
     */
    public boolean checkIfEndedPath() {

        return this.PathEnded == 1 ? true : false;

    }
    /*/////////// Este é preciso para alguma situação espedifica ou o de cima chega?///////////////*/

    public Boolean checkIfEndedPath(int node) {
        return getSize(path) == colony.totalVertex && node == colony.nest_node;
    }

    /**
     * Reset the path of the ant, the ant can now start a new path?????????????????????
     */
    @Override
    public void resetPath() {
        path.clear();
        path.add(colony.nest_node);
        currentNode = colony.nest_node;
        PathEnded = 0;
    }

    /**
     * Set the pheromones in the path that the and finished
     * @param path - ArrayList<Integer> with the path of the ant where the pheremones will be added
     */
    public void setPheromones(ArrayList<Integer> path) {
        colony.setPheromones(path);
    }

    /**
     * Remove loops that the ant might has done in the graph trying to find the Hamiltonian cycle
     * @param nodeToRemove - int with the node to remove from the path
     */
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

    /**
     * Get the weights of the adjacent nodes
     * @return Hashtable<Integer, Integer> - the weights of the adjacent nodes
     */
    public Hashtable<Integer, Integer> getPossibleWeights() {
        Hashtable<Integer, Integer> possibleWeights = getWeights();
        for (Integer integer : path) {
            possibleWeights.remove(integer);
        }
        return possibleWeights;
    }

    /**
     * Get the weights of the adjacent nodes
     * @return  Hashtable<Integer, Integer> - the weights of the adjacent nodes
     */
    public Hashtable<Integer, Integer> getWeights() { // não podemos juntar com o de cima??????
        return colony.getWeightsFromNode(currentNode);
    }

    /**
     * Get the pheromones in the nodes
     * @return Hashtable<Integer, Miguel> - level of the pheromones in the nodes
     */
    public Hashtable<Integer, Miguel> getPheromones() {
        return colony.getPheromonesFromNode(currentNode);
    }

    /**
     * Get the pheromones in the nodes that the ant can go to
     * @return Hashtable<Integer, Miguel> - level of the pheromones in the nodes that the ant can go to
     */
    public Hashtable<Integer, Miguel> getPossiblePheromones() {
        Hashtable<Integer, Miguel> pheromones = getPheromones();
        // Hashtable<Integer, Miguel> possiblePheromones = (Hashtable<Integer, Miguel>)
        // pheromones.clone();
        Hashtable<Integer, Miguel> possiblePheromones = new Hashtable<>();
        possiblePheromones.putAll(pheromones);
        for (Integer integer : path) {
            possiblePheromones.remove(integer);
        }
        return possiblePheromones;
    }

    /**
     * Get the node in some position of the path
     * @param getFrom path of nodes that the ant has gone through
     * @param index  index of the node to get
     * @return int - the node that the ant has gone through
     */
    public int getFromList(ArrayList<Integer> getFrom, int index) {
        return getFrom.get(index);
    }

    /**
     * Remove a node from the path
     * @param listToRemove - ArrayList<Integer> with the path of the ant
     * @param nodeToRemove  - int with the node to remove from the path
     */
    public void removeFromList(ArrayList<Integer> listToRemove, int nodeToRemove) {
        listToRemove.remove(nodeToRemove);
    }

    /**
     * Add a node to the path
     * @param listToAdd - ArrayList<Integer> with the path of the ant
     * @param nodeToAdd - int with the node to add to the path
     */
    public void addToList(ArrayList<Integer> listToAdd, int nodeToAdd) {
        listToAdd.add(nodeToAdd);
    }

    /**
     * @param listToCheck - ArrayList<Integer> with the path of the ant
     * @return  int - the size of the path
     */
    public int getSize(ArrayList<Integer> listToCheck) {
        return listToCheck.size();
    }

    /**
     * Get the cost between two nodes (a and b)
     * @param a - int with the node to check
     * @param b - int with the node to check
     * @return  int - the cost between the nodes
     */
    public int getCost(int a, int b) {
        return colony.getCost(a, b);
    }
}
