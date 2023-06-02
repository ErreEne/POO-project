package ACO;

import java.util.ArrayList;
import java.util.Hashtable;

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
    public int tamanhoMax;

    public AntColony(GrafoInterface Graph, int nest_node, float alpha, float beta, float gamma, float delta, float eta, int ant_colony_size) {
        this.Grafo = Graph;
        this.nest_node = nest_node;
        this.beta = beta;
        this.alpha = alpha;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.ant_colony_size = ant_colony_size;
        colony = new ArrayList<Ant>();
        // funcao no grafo para dar total weights
        this.tamanhoMax = Grafo.totalVertex();// buscar numero de nos
        this.pheromones = new Miguel(ant_colony_size, tamanhoMax);
    }

    public void createAnts() {
        Ant ant = new Ant(this);
        int i = 0;
        for (i = 0; i < ant_colony_size; i++) {
            colony.add(ant);
        }
    }

    public float[] getPheromonesFromNode(int node) {
        return pheromones.getPheromone(node);
    }

    public Hashtable<Integer, Integer> getWeightsFromNode(int node) {
        return Grafo.getEdges(node);
    }

}
