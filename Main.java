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
        int nNodes = 0;       // number of nodes in the graph
        int nest_node = 0;           // the nest node
        float alfa = 0;       // alpha, ant move event
        float beta = 0;       // beta, ant move event
        float delta = 0;      // delta, ant move event
        float eta = 0;        // eta, pheronome evaporation event
        float ro = 0;         // ro, pheronome evaporation event
        float gamma = 0;    // pheronome level
        int v = 0;            // ant colony size
        float tao = 0;          // final instant
        int[][] matriz = new int[0][];
        String arg = args[0];
        System.out.println(arg); // read the argument -r or -f

        if (arg.equals("-r")){                     //reads from terminal
            if (args.length != 11) {
                System.out.println("Invalid number of arguments");
                System.exit(0);             //terminates program ???
            }
            else {
            nNodes = Integer.parseInt(args[1]);
            nest_node = Integer.parseInt(args[2]);
            alfa = Float.parseFloat(args[3]);
            beta = Float.parseFloat(args[4]);
            delta = Float.parseFloat(args[5]);
            eta = Float.parseFloat(args[6]);
            ro = Float.parseFloat(args[7]);
            gamma = Float.parseFloat(args[8]);
            v = Integer.parseInt(args[9]);
            tao = Integer.parseInt(args[10]);

            Grafo grafo = new Grafo(nNodes);
            }
        }
        else if (arg.equals("-f")){          //reads from file
            Grafo grafo = new Grafo(0);
            grafo.GrafoFromFile(args[1]);
        }
        AntColony.createAnts();
        int[][] matrix = new int[1][1];
        matrix[0][0] = 1;
        Ant ant = new Ant(matriz, nest_node, alfa, beta, gamma, delta, eta,null); // primeira e última para não se babar

        float[] pheromone = new float[nNodes];
        float[] heuristic = new float[nNodes];
        ant.setPheromone(pheromone);
        heuristic = ant.getNormalizedProbabilities(0);
        for (int i=0; i < nNodes; i++){
            System.out.println(heuristic[i]);
        }
        //Grafo grafo = new Grafo(2);
        //grafo.MostrarVerticeInfo(1);

    }
}