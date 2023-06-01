package ACO;

import java.util.ArrayList;
import GrafoPack.GrafoInterface;

public class AntColony {
    private static ArrayList<Ant> colony = null;
    public static int[][] matrizAdj;
    public static int nest_node;
    public static float beta;
    public static float alpha;
    public static float gamma;
    public static float delta;
    public static float eta;
    public static int ant_colony_size;
    private static GrafoInterface Grafo = null;  // final??

    public AntColony(GrafoInterface Graph, int nest_node, float alpha, float beta, float gamma, float delta, float eta, int ant_colony_size) {

        AntColony.Grafo = Graph;
        AntColony.nest_node = nest_node;
        AntColony.beta = beta;
        AntColony.alpha = alpha;
        AntColony.gamma = gamma;
        AntColony.delta = delta;
        AntColony.eta = eta;
        AntColony.ant_colony_size = ant_colony_size;
        colony = new ArrayList<Ant>();
        System.out.println("Ant Colony created");
    }

    public static void createAnts() {
        Ant ant = new Ant(matrizAdj, nest_node, alpha, beta, gamma, delta, eta, Grafo);
        int i = 0;
        for (i = 0; i < ant_colony_size; i++) {
            colony.add(ant);
        }
    }

}
