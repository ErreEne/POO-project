package ACO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
    public int[][] matrizAdj;
    public ArrayList<Integer> path;
    public ArrayList<Integer> unVisitedNodes;
    public int nest_node;
    public float alpha;
    public float beta;
    public float gamma;
    public float delta;
    public float eta;

    public Ant(int[][] matrizAdj, int nest_node, float alpha, float beta, float gamma, float delta, float eta) {
        this.matrizAdj = matrizAdj;
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
    }

    public static float getProbability(float alfa, float beta, int weight, float pheromone, int nNodes){
        float Ci = 0;
        float weights = (float)weight;
        float Cijk = (alfa+pheromone)/(beta+weights);
        for (int i = 0; i < nNodes; i++) {
            Ci += ((alfa+pheromone)/(beta+weight));
        }
        return  Cijk/Ci;
    }

    public int[] getWeights(int currentNode) {
        int[] weights = new int[this.matrizAdj.length];

        for (int i = 0; i < this.matrizAdj.length; i++) {
            weights[i] = this.matrizAdj[currentNode][i];
        }
        return weights;
    }

    public float[] getPheromone(int currentNode) {
        float[] pheromone = new float[this.matrizAdj.length];

        for (int i = 0; i < this.matrizAdj.length; i++) {
            pheromone[i] = this.matrizAdj[currentNode][i]; // mudar para feromonas
        }
        return pheromone;
    }

    public float[] getNormalizedProbabilities(int currentNode) {
        int[] weights = getWeights(currentNode);
        float[] pheromone = getPheromone(currentNode);
        float[] probability = new float[this.matrizAdj.length];
        float sum = 0;

        for (int i = 0; i < this.matrizAdj.length; i++) {
            probability[i] = getProbability(this.alpha, this.beta, weights[i], pheromone[i], this.matrizAdj.length);
            sum += probability[i];
        }

        for(int i = 0; i < this.matrizAdj.length; i++) {
            probability[i] = probability[i]/sum;
        }
        return probability;
    }

    public int nodeChosen(float[] probability) {
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

    public Boolean updatePath(int newNode) {
        int[] possibleChoices = getPossibleChoices(this.matrizAdj[newNode]);
        int length = possibleChoices.length;
        float[] NormalizedProbabilities = getNormalizedProbabilities(newNode);
        int chosenNode = 0;
        if(checkIfEndedPath()) {
            return false;
        }else {
            while (possibleChoices.length != 0) {
                chosenNode = nodeChosen(NormalizedProbabilities);
                addToList(this.path, chosenNode);
                removeFromList(this.unVisitedNodes, chosenNode);
                int loop = checkLoop(chosenNode);
                if (loop != -1) {
                    for(int i = 0; i < possibleChoices.length; i++) {
                        if(possibleChoices[i] == chosenNode) {
                            possibleChoices[i] = -1;
                        }
                    }
                } else {
                    removeLoop(chosenNode);
                    break;
                }
            }
        }
        return null;
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

    public int[] getPossibleChoices(int[] connectedNodes) {
        int[] possibleChoices = new int[connectedNodes.length];
        int j = 0;
        for (int i = 0; i < connectedNodes.length; i++) {
            if (connectedNodes[i] != 0) {
                possibleChoices[j] = i;
                j++;
            }
        }
        return possibleChoices;
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
        return this.unVisitedNodes.isEmpty();
    }

    public double pheromoneLevel (int gama) {
        int custo = 0;
        double miu=1; ///Miu o que é, de onde vem??
        return (gama*custo)/miu;
    }
}
