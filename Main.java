import ACO.Ant;
import ACO.AntColony;
import ACO.SortedPriorityQueue;


import java.io.File;
import java.io.FileNotFoundException;
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
            }
        }
        else if (arg.equals("-f")){          //reads from file
            try {
                String path = args[1];
                File file = new File(path);  // Specify the path to your text file

                Scanner scanner = new Scanner(file);

                String inputLine = scanner.nextLine();
                String[] inputs = inputLine.split(" "); // split the line into an array of strings
                if (inputs.length != 10) {
                    System.out.println("Invalid number of input values");
                    System.exit(0);             //terminates program ???
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

                int j=0,i=0;
                matriz = new int[nNodes][nNodes];
                for (i = 0; i < nNodes; i++) {
                    for (j = 0; j < nNodes; j++) {
                        matriz[i][j] = 0;
                    }
                }
                j=0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] elements = line.split("[\\s\\t]+");
                    for (i=0; i < nNodes; i++){
                        matriz[j][i]= Integer.parseInt(elements[i]);
                    }
                    j++;
                }
                for(i = 0; i < nNodes; i++){
                    for(j = 0; j < nNodes; j++){
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println("   ");
                }
                //AntColony antColony = new AntColony(matriz, nest_node - 1, alfa, beta, gamma, delta, eta,v);

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());}
        }
        else{
            System.out.println("Invalid argument, use -r or -f");
        }
        SortedPriorityQueue<Integer> pq = new SortedPriorityQueue<>();
        pq.enqueue(5);
        pq.enqueue(2);
        pq.enqueue(7);
        pq.enqueue(1);

        while (!pq.isEmpty()) {
            System.out.println(pq.dequeue());
        }

        /*AntColony.createAnts();
        Ant ant = new Ant(matriz, nest_node, alfa, beta, gamma, delta, eta);

        float[] pheromone = new float[nNodes];
        float[] heuristic = new float[nNodes];
        ant.setPheromone(pheromone);
        heuristic = ant.getNormalizedProbabilities(0);
        for (int i=0; i < nNodes; i++){
            System.out.println(heuristic[i]);
        }*/
        //Grafo grafo = new Grafo(2);
        //grafo.MostrarVerticeInfo(1);

    }
}