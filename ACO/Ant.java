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
    private float[] pheromone;

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
        System.out.println("Ant created");
    }

    public static float getProbability(float alfa, float beta, float[] weight, float[] pheromone, int nNodes, int Node){
        float Ci = 0;
        float Cijk = (alfa+pheromone[Node])/(beta+weight[Node]);
        for (int i = 0; i < nNodes; i++) {
            Ci += ((alfa+pheromone[i])/(beta+weight[i]));
        }
        return  Cijk/Ci;
    }

    public float[] getWeights(int currentNode) {
        float[] weights = new float[this.matrizAdj.length];

        for (int i = 0; i < this.matrizAdj.length; i++) {
            weights[i] = (float)this.matrizAdj[currentNode][i];
        }
        return weights;
    }

    public float[] getPheromone(int currentNode) {
        float[] pheromone = new float[this.matrizAdj.length];

        for (int i = 0; i < this.matrizAdj.length; i++) {
            pheromone[i] = this.pheromone[i]; // mudar para feromonas
        }
        return pheromone;
    }

    public float[] getNormalizedProbabilities(int currentNode) {
        float[] weights = getWeights(currentNode);

        float[] pheromone = getPheromone(currentNode);

        float[] probability = new float[this.matrizAdj.length];
        float sum = 0;

        for (int i = 0; i < this.matrizAdj.length; i++) {

            probability[i] = getProbability(this.alpha, this.beta, weights, pheromone, this.matrizAdj.length, i);
            System.out.println("Probability: " + probability[i]);
            sum += probability[i];
        }

        for(int i = 0; i < this.matrizAdj.length; i++) {
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

    public Boolean updatePath(int Node) {
        ArrayList<Integer> possibleChoices = getPossibleChoices(this.matrizAdj[Node]);

        float[] NormalizedProbabilities = getNormalizedProbabilities(Node);
        int chosenNode = 0;
        if(checkIfEndedPath()) {
            return false;
        }else {
            while (possibleChoices.size() != 0) {
                chosenNode = chooseNode(NormalizedProbabilities);
                addToList(this.path, chosenNode);
                removeFromList(this.unVisitedNodes, chosenNode);
                int loop = checkLoop(chosenNode);
                if (loop != -1) {
                    for(int i = 0; i < possibleChoices.size(); i++) {
                        if(possibleChoices.get(i) == chosenNode) {
                            possibleChoices.remove(i);
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

    public ArrayList<Integer> getPossibleChoices(int[] connectedNodes) {
        ArrayList<Integer> possibleChoices = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < connectedNodes.length; i++) {
            if (connectedNodes[i] != 0) {
                for (Integer unVisitedNode : this.unVisitedNodes) {
                    if (unVisitedNode == i) {
                        possibleChoices.add(j, i);
                        j++;
                    }
                }
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

    public void setPheromone(float[] pheromone) {
        this.pheromone = pheromone;
    }
}
