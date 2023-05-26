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

    public static double getProbability(double alfa, double beta, int no, double[] weight, double[] pheromone, int nNodes){
        double Ci = 0;
        double  Cijk = (alfa+pheromone[no])/(beta+weight[no]);
        for (int i = 0; i < nNodes; i++) {
            Ci += ((alfa+pheromone[i])/(beta+weight[i]));
        }
        return  Cijk/Ci;
    }

    public void updatePath(int newNode) {
        int inLoop = checkLoop(newNode);
        if(inLoop != -1) {
            addToPath(newNode);
        } else {
            removeLoop(newNode);
        }
    }

    public double pheromoneLevel (int gama) {
        double custo = Grafo.vertice.ListadePonteiros.getCusto();
        double miu=1; ///Miu o que é, de onde vem??
        return (gama*custo)/miu;
    }
}
