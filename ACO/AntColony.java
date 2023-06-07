package ACO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

import DSS.*;
import GrafoPack.*;

public class AntColony {
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
    public Event Evento;
    public int totalVertex;
    public float timelimit;

    public AntColony(GrafoInterface Graph, int nest_node, float alpha, float beta, float gamma, float delta, float eta,
            int ant_colony_size, float ro, float tao, Event evento) {
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
        this.Evento = evento;
        initializePheromones();
        createAnts();

    }

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

    public int sumOfWeightsPath(ArrayList<Integer> path) {
        int sum = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            sum += Grafo.GetCusto(path.get(i), path.get(i + 1));
        }
        sum += Grafo.GetCusto(path.get(path.size() - 1), nest_node);
        return sum;
    }

    public void setPheromone(int currentNode, int nextNode, int sumOfWeights) {
        pheromones.get(currentNode).get(nextNode).setPheromone(sumOfWeights);
    }

    public ArrayList<Ant> getAnts() {
        return colony;
    }

    public void createAnts() {
        // Ant ant = new Ant(this);
        Ant ant;
        for (int i = 0; i < ant_colony_size; i++) {
            ant = new Ant(this);
            colony.add(ant);
        }
    }

    int getCost(int node1, int node2) {
        return Grafo.GetCusto(node1, node2);
    }

    public Hashtable<Integer, Miguel> getPheromonesFromNode(int node) {
        return pheromones.get(node);
    }

    public Hashtable<Integer, Integer> getWeightsFromNode(int node) {
        return Grafo.getEdges(node);
    }

    public void Simulate() {

        while (Evento.getTime() < this.timelimit) {

            Evento.execute();

        }

    }

    public void run() {
        colony.get(0).move();
    }
}
