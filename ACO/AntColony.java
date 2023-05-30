package ACO;

import GrafoPack.Grafo;
import java.util.ArrayList;

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

    public AntColony(int[][] matrizAdj, int nest_node, float alpha, float beta, float gamma, float delta, float eta, int ant_colony_size) {
        AntColony.matrizAdj = matrizAdj;
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

    public static void addAnt(Ant ant) {
        colony.add(ant);
    }

    public static void createAnts() {
        Ant ant = new Ant(matrizAdj, nest_node, alpha, beta, gamma, delta, eta);
        int i = 0;
        for (i = 0; i < ant_colony_size; i++) {
            addAnt(ant);
        }
    }

}
