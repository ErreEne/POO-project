package ACO;

import GrafoPack.Grafo;
import java.util.ArrayList;

public class AntColony {
    private final ArrayList<Ant> colony;
    public Grafo grafo;
    public int nest_node;
    public float beta, alpha, gamma, delta, eta;
    public int ant_colony_size;

    public AntColony(Grafo grafo, int nest_node, float alpha, float beta, float gamma, float delta, float eta, int ant_colony_size) {
        this.grafo = grafo;
        this.nest_node = nest_node;
        this.beta = beta;
        this.alpha = alpha;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.ant_colony_size = ant_colony_size;
        this.colony = new ArrayList<Ant>();
    }

    public void addAnt(Ant ant) {
        this.colony.add(ant);
    }

    public void createAnts() {
        for (int i = 0; i < this.ant_colony_size; i++) {
            Ant ant = new Ant();
            addAnt(ant);
        }
    }

}
