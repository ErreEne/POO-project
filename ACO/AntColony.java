package ACO;

import java.util.ArrayList;
import java.util.Hashtable;

import DSS.AntEvent;
import DSS.Event;
import DSS.FeromonasInterface;
import GrafoPack.Grafo;
import GrafoPack.GrafoInterface;

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
    public Miguel pheromones;
    public int totalWeights;
    public float ro;
    private AntEvent MoveFormiga;
    private FeromonasInterface Feromonas;

    public AntColony(GrafoInterface Graph, int nest_node, float alpha, float beta, float gamma, float delta, float eta,
            int ant_colony_size, float ro) {
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
        // funcao no grafo para dar total weights
        this.totalWeights = Grafo.totalEdgesSum();
        this.pheromones = new Miguel(ant_colony_size, totalWeights, gamma, ro); // quero totalWeights
    }

    public ArrayList<Ant> getAnts() {
        return colony;
    }

    public void createAnts() {
        Ant ant = new Ant(this);
        for (int i = 0; i < ant_colony_size; i++) {
            colony.add(ant);
        }
    }

    public Hashtable<Integer, Float> getPheromonesFromNode(int node) {
        return pheromones.getPheromone(node);
    }

    public void setPheromones(ArrayList<Integer> path) {
        pheromones.setPheromones(path, totalWeights);
    }

    public Hashtable<Integer, Integer> getWeightsFromNode(int node) {
        return Grafo.getEdges(node);
    }

}
