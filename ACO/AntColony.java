package ACO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

import DSS.*;
import GrafoPack.*;

/**
 * Class that represents the ant colony
 */
public class AntColony implements AntColonyInterface{
    private ArrayList<Ant> colony;
    public int nest_node;
    public float beta;
    public float alpha;
    public float gamma;
    public float delta;
    public float eta;
    public int ant_colony_size;
    public GrafoInterface Grafo;
    public HashMap<Integer, Hashtable<Integer, Miguel>> pheromones;
    public int totalWeights;
    public float ro;
    public int totalVertex;
    public float timelimit;

    /**
     * @param Graph - graph to be used
     * @param nest_node - node to be used as nest
     * @param alpha - value for calculating the probability of choosing a node
     * @param beta  - value for calculating the probability of choosing a node
     * @param gamma - value for calculating the level of pheromone
     * @param delta - value for calculating next time to move the ant
     * @param eta   - time between the evaporation events
     * @param ant_colony_size   - number of ants in the colony
     * @param ro    - value for decreasing pheromones
     * @param tao   - time of simulation
     */
    public AntColony(GrafoInterface Graph, int nest_node, float alpha, float beta, float gamma, float delta, float eta,
            int ant_colony_size, float ro, float tao) {
        this.Grafo = Graph;
        this.nest_node = nest_node;
        this.beta = beta;
        this.alpha = alpha;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.ant_colony_size = ant_colony_size;
        this.colony = new ArrayList<>();
        this.ro = ro;
        this.totalWeights = Grafo.totalEdgesSum();
        this.totalVertex = Grafo.totalVertex();
        this.pheromones = new HashMap<>();
        this.timelimit = tao;
        initializePheromones();
        createAnts();

    }

    /**
     * Initialize the pheromones for each edge in the graph
     */
    public void initializePheromones() {
        for (int i = 1; i <= totalVertex; i++) {
            Hashtable<Integer, Miguel> pheromonesFromNode = new Hashtable<>();
            Hashtable<Integer, Integer> possibleNodes = Grafo.getEdges(i);
            for (int j = 1; j <= totalVertex; j++) {
                if (possibleNodes.containsKey(j)) {
                    pheromonesFromNode.put(j, new Miguel(ant_colony_size, totalWeights, gamma, ro));
                }
            }
            pheromones.put(i, pheromonesFromNode);
        }
    }

    /**
     * Set the level of pheromones in the edges that the ant took
     * @param path - path that the ant took
     */
    public void setPheromones(ArrayList<Integer> path) {
        int currentNode;
        int nextNode;
        int sumOfWeights = sumOfWeightsPath(path);
        System.out.println("Sum of weights: " + sumOfWeights);
        System.out.println("totalweights: " + totalWeights);
        for (int i = 0; i < path.size() - 1; i++) {
            currentNode = path.get(i);
            nextNode = path.get(i + 1);

            setPheromone(currentNode, nextNode, sumOfWeights);
            System.out.println("Pheromone from " + currentNode + " to " + nextNode + " is " + pheromones.get(currentNode).get(nextNode).getPheromone());
        }
    }

    /**
     * Get the sum of weights of a path
     * @param path - path to calculate the sum of weights
     * @return the sum of weights of the path
     */
    public int sumOfWeightsPath(ArrayList<Integer> path) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            sum += Grafo.GetCusto(path.get(i), path.get(i + 1));
        }
        sum += Grafo.GetCusto(path.get(path.size() - 1), nest_node);
        return sum;
    }

    /**
     * Set the level of pheromones in the edge between two nodes
     * @param currentNode  - current node
     * @param nextNode   - next node
     * @param sumOfWeights  - sum of weights of the path
     */
    public void setPheromone(int currentNode, int nextNode, int sumOfWeights) {
        pheromones.get(currentNode).get(nextNode).setPheromone(sumOfWeights);
    }

    /**
     * Get the ants in the colony
     * @return the ants in the colony
     */
    public ArrayList<Ant> getAnts() {
        return colony;
    }

    /**
     * Create all ants in the colony
     */
    public void createAnts() {
        // Ant ant = new Ant(this);
        Ant ant;
        for (int i = 0; i < ant_colony_size; i++) {
            ant = new Ant(this);
            colony.add(ant);
        }
    }

    /**
     * Get the cost between two nodes in the graph
     * @param node1 - first node
     * @param node2 - second node
     * @return  the cost of the edge between node1 and node2
     */
    int getCost(int node1, int node2) {
        return Grafo.GetCusto(node1, node2);
    }

    /**
     * Get the leve of pheromone in edge of node
     * @param node - node to get the pheromones from
     * @return the pheromones from the node
     */
    public Hashtable<Integer, Miguel> getPheromonesFromNode(int node) {
        return pheromones.get(node);
    }

    /**
     * Get the weights for the adjacent nodes of a node
     * @param node - node to get the weights from
     * @return the weights from the adjacent nodes of the node
     */
    public Hashtable<Integer, Integer> getWeightsFromNode(int node) {
        return Grafo.getEdges(node);
    }

    /**
     * Run the simulation
     */
    public void run() {
        colony.get(0).move();
    }
}
