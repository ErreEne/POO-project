package ACO;

import GrafoPack.Grafo;
import GrafoPack.Ponteiro;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    public ArrayList<Integer> path;
    public ArrayList<Integer> visitedNodes;
    public ArrayList<Integer> unVisitedNodes;


    public Ant() {
        super();
        this.path = new ArrayList<Integer>();
        this.visitedNodes = new ArrayList<Integer>;
        this.unVisitedNodes = new ArrayList<Integer>();
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

    public void removeLoop(int nodeToRemove) {
        this.path.remove(nodeToRemove - 1);
    }

    public void addToPath(int nodeToAdd) {
        this.path.add(nodeToAdd);
    }

    public int checkSize(ArrayList<Integer> listToCheck) {
        return listToCheck.size();
    }

    public int checkLoop(int newNode) {
        for (int i = 0; i < this.checkSize(this.path); i++) {
            if(this.path.get(i) == newNode) {
                return i;
            }
        }
        return -1;
    }

    public double pheromoneLevel (int gama) {
        int custo = Grafo.getN();//mal
        double miu=1; ///Miu o que é, de onde vem??
        return (gama*custo)/miu;
    }
}
