package ACO;

import GrafoPack.Grafo;
import GrafoPack.Ponteiro;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    public ArrayList<Integer> path;
    public ArrayList<Integer> visitedNodes;
    public ArrayList<Integer> unVisitedNodes;
    public Grafo grafo;
    public int nest_node;
    public float alpha;
    public float beta;
    public float gamma;
    public float delta;
    public float eta;

    public Ant(Grafo grafo, int nest_node, float alpha, float beta, float gamma, float delta, float eta) {
        this.grafo = grafo; // talvez cada ant nao tem conhecimento do grafo, mas sim dos vetor de ponteiros onde pode andar para
        this.nest_node = nest_node;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.path = new ArrayList<Integer>();
        this.unVisitedNodes = new ArrayList<Integer>(); // receber todos os nos do grafo?? ainda nao sei se isto da jeito para o programa
    }

    public static double getProbability(double alfa, double beta, int no, double[] weight, double[] pheromone, int nNodes){
        double Ci = 0;
        double  Cijk = (alfa+pheromone[no])/(beta+weight[no]);
        for (int i = 0; i < nNodes; i++) {
            Ci += ((alfa+pheromone[i])/(beta+weight[i]));
        }
        return  Cijk/Ci;
    }



    public Boolean updatePath(int newNode) {
        if(!checkIfEndedPath()) {
            if(checkLoop(newNode) != -1) {
                addToList(this.path, newNode);
                removeFromList(this.unVisitedNodes, newNode);
            } else {
                removeLoop(newNode);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < getSize(this.path) - 1; i++) {
            if (i == nodeToRemove || flag == 1) {
                flag = 1;
                removeFromList(this.path, i);
            }
        }
        addToList(this.path, nodeToRemove);
    }

    public void removeFromList(ArrayList<Integer> listToRemove, int nodeToRemove) {
        listToRemove.remove(nodeToRemove);
    }

    public void addToList(ArrayList<Integer> listToAdd, int nodeToAdd) {
        listToAdd.add(nodeToAdd);
    }

    public int getSize(ArrayList<Integer> listToCheck) {
        return listToCheck.size();
    }

    public int checkLoop(int newNode) {
        for (int i = 0; i < this.getSize(this.path); i++) {
            if(this.path.get(i) == newNode) {
                return i;
            }
        }
        return -1;
    }

    public Boolean checkIfEndedPath() {
        return this.unVisitedNodes.isEmpty();
    }

    public double pheromoneLevel (int gama) {
        int custo = Grafo.getN();//mal
        double miu=1; ///Miu o que é, de onde vem??
        return (gama*custo)/miu;
    }
}
