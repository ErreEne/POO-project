import ACO.Ant;
import ACO.AntColony;
import GrafoPack.Grafo;
import GrafoPack.GrafoInterface;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(args);
        float[] input = grafo.getInput();
        int nNodes = (int)input[0];       // number of nodes in the graph
        int nest_node = (int)input[1];           // the nest node
        float alfa = input[2];       // alpha, ant move event
        float beta = input[3];       // beta, ant move event
        float delta = input[4];      // delta, ant move event
        float eta = input[5];        // eta, pheronome evaporation event
        float ro = input[6];         // ro, pheronome evaporation event
        float gamma = input[7];    // pheronome level
        int v = (int)input[8];            // ant colony size
        float tao = input[9];          // final instant
        // AntColony.createAnts();
        int[][] matrix = new int[1][1]; // apenas para o Ant não se babar
        //Ant ant = new Ant(matrix, nest_node, alfa, beta, gamma, delta, eta,null); // primeira e última para não se babar

        float[] pheromone = new float[nNodes];
        float[] heuristic = new float[nNodes];
        //ant.setPheromone(pheromone);
        //heuristic = ant.getNormalizedProbabilities(0);
        for (int i=0; i < nNodes; i++){
            System.out.println(heuristic[i]);
        }
        //Grafo grafo = new Grafo(2);
        //grafo.MostrarVerticeInfo(1);

    }
}