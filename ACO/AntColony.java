package ACO;

import GrafoPack.Grafo;

public class AntColony {
    public Grafo grafo;
    public int nest_node;
    public int beta, alpha, gamma, delta, eta;
    public int ant_colony_size;

    public AntColony(Grafo grafo, int nest_node, int beta, int alpha, int gamma, int delta, int eta, int ant_colony_size) {
        this.grafo = grafo;
        this.nest_node = nest_node;
        this.beta = beta;
        this.alpha = alpha;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.ant_colony_size = ant_colony_size;
    }

    public void start() {
        // TODO Auto-generated method stub

    }

    public void initialize() {
        // TODO Auto-generated method stub

    }

    public void createAnts() {
        for (int i = 0; i < ant_colony_size; i++) {
            Ant ant = new Ant(grafo, nest_node, beta, alpha, gamma, delta, eta);
        }

    }

    public void updatePheromones() {
        // TODO Auto-generated method stub

    }

    public void updateBest() {
        // TODO Auto-generated method stub

    }

    public void updateAnts() {
        // TODO Auto-generated method stub

    }

    public void checkPheromones() {
        // TODO Auto-generated method stub

    }

    public void checkAnts() {
        // TODO Auto-generated method stub

    }

    public void checkBest() {
        // TODO Auto-generated method stub

    }

    public void checkNest() {
        // TODO Auto-generated method stub

    }

    public void checkParameters() {
        // TODO Auto-generated method stub

    }

    public void checkAntColony() {
        // TODO Auto-generated method stub

    }

    public void checkGrafo() {
        // TODO Auto-generated method stub

    }

    public void check() {
        // TODO Auto-generated method stub

    }

    public void checkParametersAntColony() {
        // TODO Auto-generated method stub

    }

    public void checkParametersGrafo() {
        // TODO Auto-generated method stub

    }

    public void checkParametersAnt() {
        // TODO Auto-generated method stub

    }

    public void checkParametersPheromone() {
        // TODO Auto-generated method stub

    }

    public void checkParametersNest() {
        // TODO Auto-generated method stub

    }

    public void checkParametersBest() {
        // TODO Auto-generated method stub

    }

    public void checkParametersAntColonySize() {
        // TODO Auto-generated method stub

    }

    public void checkParametersBeta() {
        // TODO Auto-generated method stub

    }

    public void checkParametersAlpha() {
        // TODO Auto-generated method stub

    }

    public void checkParametersGamma() {
        // TODO Auto-generated method stub

    }

    public void checkParametersDelta() {
        // TODO Auto-generated method stub

    }

    public void checkParametersEta() {
        // TODO Auto-generated method stub

    }

    public void checkParametersNestNode() {
        // TODO Auto-generated method stub

    }
}
