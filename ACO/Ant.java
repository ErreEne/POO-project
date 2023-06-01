package ACO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Ant {
    public ArrayList<Integer> path;
    public ArrayList<Integer> unVisitedNodes;
    public int nest_node;
    public float alpha;
    public float beta;
    public float gamma;
    public float delta;
    public float eta;
    private float[] pheromone;
    private int tamanhoMax = 0;

    public Ant(int[][] matrizAdj, int nest_node, float alpha, float beta, float gamma, float delta, float eta) {
        this.nest_node = nest_node;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.delta = delta;
        this.eta = eta;
        this.path = new ArrayList<Integer>();
        this.unVisitedNodes = new ArrayList<Integer>();
        for(int i = 0; i < matrizAdj.length; i++) {
            this.unVisitedNodes.add(i);
        }
        System.out.println("Ant created");
    }

    public float[] getWeights(int currentNode) {
        float[] weights = new float[tamanhoMax];

        for (int i = 0; i < tamanhoMax; i++) {
            weights[i] = 10;
        }
        return weights;
    }

    public float[] getPheromone(int currentNode) {
        float[] pheromone = new float[tamanhoMax];

        for (int i = 0; i < tamanhoMax; i++) {
            pheromone[i] = this.pheromone[i]; // mudar para feromonas
        }
        return pheromone;
    }

    public float[] getNormalizedProbabilities(int currentNode) {
        float[] weights = getWeights(currentNode);

        float[] pheromone = getPheromone(currentNode);

        float[] probability = new float[tamanhoMax];
        float sum = 0;
        float Ci = 0;
        float Cijk = 0;

        for (int i = 0; i < tamanhoMax; i++) {
            Ci += ((this.alpha + pheromone[i])/(this.beta + weights[i]));
        }

        for (int i = 0; i < tamanhoMax; i++) {
            if(weights[i] == 0) {
                probability[i] = 0;
            } else {
                Cijk = ((this.alpha + pheromone[i])/(this.beta + weights[i]));
                probability[i] = Cijk/Ci;
            }
            sum += probability[i];
        }

        for(int i = 0; i < tamanhoMax; i++) {
            probability[i] = probability[i]/sum;
        }

        return probability;
    }

    public int chooseNode(float[] probability) {
        Random rand = new Random();
        float node = rand.nextFloat(0,1);
        float partialSum = 0;
        for (int i = 0; i < probability.length; i++) {
            partialSum += probability[i];
            if (node < partialSum) {
                return i;
            }
        }
        return probability.length;
    }

    public Boolean updatePath(int currentNode) {
        if(checkIfEndedPath()) {
            return true;
        }

        float[] NormalizedProbabilities = getNormalizedProbabilities(currentNode);
        int newNode = chooseNode(NormalizedProbabilities);

        int loop = checkLoop(newNode);
        if(loop == -1) { // loop
            removeLoop(newNode);
            return false;
        }
        this.path.add(newNode);

        return false;
    }

    public void removeLoop(int nodeToRemove) {
        int flag = 0;
        for (int i = 0; i < getSize(this.path) - 1; i++) {
            if (i == nodeToRemove || flag == 1) {
                flag = 1;
                removeFromList(this.path, i);
                addToList(this.unVisitedNodes, i);
            }
        }
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
        for (int i = 0; i < this.getSize(this.path) - 1; i++) {
            if(this.path.get(i) == newNode) {
                return i;
            }
        }
        return -1;
    }

    public Boolean checkIfEndedPath() {
        if (this.getSize(this.path) == tamanhoMax) {
            for(int i = 0; i < tamanhoMax; i++) {
                if() {
                    this.path.add(this.nest_node);
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public double pheromoneLevel (int gama) {
        int custo = 0;
        double miu=1; ///Miu o que é, de onde vem??
        return (gama*custo)/miu;
    }

    public void setPheromone(float[] pheromone) {
        this.pheromone = pheromone;
    }
}
