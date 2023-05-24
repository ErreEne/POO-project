package ACO;

import GrafoPack.Grafo;
import java.util.ArrayList;
import java.util.List;

public class Ant {
    Grafo grafo;
    public int distance;
    public int[] visited_nodes;
    public int[] unvisited_nodes;
    public int[] path;
    public int beta, alpha, gamma, delta, eta;
    public Ant(Grafo grafo, int nest_node, int beta, int alpha, int gamma, int delta, int eta) {
        this.grafo = grafo;
        this.visited_nodes = new int[grafo.getN()];
        this.unvisited_nodes = new int[grafo.getN()];
        this.path = new int[grafo.getN()];
        this.distance = 0;
        this.visited_nodes[0] = nest_node;
        this.unvisited_nodes[nest_node] = 0;
        this.path[0] = nest_node;
        this.beta = beta;
        this.alpha = alpha;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
    }

    public void move() {

    }

    public void chooseNextNode() {

    }

    public void updatePheromone() {

    }

    public void checkPheromone() {

    }

    public void checkNode() {

    }

    public void checkAnt() {

    }

    public void updateAnt() {

    }

    public void updateBest() {

    }

    public void updatePheromones() {

    }

    public void updateAnts() {

    }

    public void checkPheromones() {

    }

    public void checkAnts() {

    }

}
