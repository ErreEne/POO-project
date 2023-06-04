import ACO.Ant;
import ACO.AntColony;
import GrafoPack.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nNodes = 0; // number of nodes in the graph
        int nest_node = 0; // the nest node
        float alfa = 0; // alpha, ant move event
        float beta = 0; // beta, ant move event
        float delta = 0; // delta, ant move event
        float eta = 0; // eta, pheronome evaporation event
        float ro = 0; // ro, pheronome evaporation event
        float gamma = 0; // pheronome level
        int v = 0; // ant colony size
        float tao = 0; // final instant
        int[][] matriz = new int[0][];
        Grafo grafo;
        String arg = args[0];
        System.out.println(arg); // read the argument -r or -f

        if (arg.equals("-r")) { // reads from terminal
            if (args.length != 11) {
                System.out.println("Invalid number of arguments");
                System.exit(0); // terminates program ???
            } else {
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
            }
            grafo = new Grafo(nNodes, nNodes*2, 6); // edges e peso não são dadas no input

        } else if (arg.equals("-f")) { // reads from file
            try {
                String path = args[1];
                File file = new File(path); // Specify the path to your text file

                Scanner scanner = new Scanner(file);

                String inputLine = scanner.nextLine();
                String[] inputs = inputLine.split(" "); // split the line into an array of strings
                if (inputs.length != 10) {
                    System.out.println("Invalid number of input values");
                    System.exit(0); // terminates program ???
                }
                nNodes = Integer.parseInt(inputs[0]);
                nest_node = Integer.parseInt(inputs[1]);
                alfa = Float.parseFloat(inputs[2]);
                beta = Float.parseFloat(inputs[3]);
                delta = Float.parseFloat(inputs[4]);
                eta = Float.parseFloat(inputs[5]);
                ro = Float.parseFloat(inputs[6]);
                gamma = Float.parseFloat(inputs[7]);
                v = Integer.parseInt(inputs[8]);
                tao = Float.parseFloat(inputs[9]);

                grafo = new Grafo(nNodes); // usa o segundo construtor
                
                for(int i=1; i <= nNodes; i++){
                    grafo.CriarVertice(i);
                }
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] elements = line.split("[\\s\\t]+");
                    int k=0;
                    for (i = 1; i <= nNodes; i++) {
                        for (int j = i+1; j <= nNodes; j++) {
                            grafo.AdicionarLiga(i, j, elements[k])
                            k++;
                        }
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid argument, use -r or -f");
        }

        /*
         * AntColony.createAnts();
         * Ant ant = new Ant(matriz, nest_node, alfa, beta, gamma, delta, eta);
         * 
         * float[] pheromone = new float[nNodes];
         * float[] heuristic = new float[nNodes];
         * ant.setPheromone(pheromone);
         * heuristic = ant.getNormalizedProbabilities(0);
         * for (int i=0; i < nNodes; i++){
         * System.out.println(heuristic[i]);
         * }
         */
        // Grafo grafo = new Grafo(2);
        // grafo.MostrarVerticeInfo(1);

    }
}