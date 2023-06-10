import DSS.*;
import ACO.*;
import GrafoPack.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nNodes = 0; // number of nodes in the graph
        int nest_node = 0; // the nest node
        int maxWeight = 0; // Max weight of graph (-r)
        float alfa = 0; // alpha, ant move event
        float beta = 0; // beta, ant move event
        float delta = 0; // delta, ant move event
        float eta = 0; // eta, pheronome evaporation event
        float ro = 0; // ro, pheronome evaporation event
        float gamma = 0; // pheronome level
        int v = 0; // ant colony size
        float tao = 0; // final instant
        int[][] matriz;
        GrafoInterface grafo = null;
        Event Evento;
        String arg = args[0];
        // System.out.println(arg); // read the argument -r or -f
        AntColony colonia;
    
        if (arg.equals("-r")) { // reads from terminal
            if (args.length != 12) {
                System.out.println("Invalid number of arguments");
                System.exit(0); // terminates program ???
            } else {
                nNodes = Integer.parseInt(args[1]);
                nest_node = Integer.parseInt(args[2]);
                maxWeight = Integer.parseInt(args[3]);
                alfa = Float.parseFloat(args[4]);
                beta = Float.parseFloat(args[5]);
                delta = Float.parseFloat(args[6]);
                eta = Float.parseFloat(args[7]);
                ro = Float.parseFloat(args[8]);
                gamma = Float.parseFloat(args[9]);
                v = Integer.parseInt(args[10]);
                tao = Float.parseFloat(args[11]);
            }
            grafo = new Grafo(nNodes, nNodes + 1, maxWeight); // edges e peso não são dadas no input

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

                matriz = new int[nNodes][nNodes];
                int j = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] elements = line.split("[\\s\\t]+");
                    for (int i = 0; i < nNodes; i++) {
                        matriz[j][i] = Integer.parseInt(elements[i]);
                    }
                    j++;
                }
                grafo = new Grafo(nNodes, matriz); // usa o segundo construtor
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid argument, use -r or -f");
        }

        if (grafo != null) {
            colonia = new AntColony(grafo, nest_node, alfa, beta, gamma, delta, v, ro);
            Evento = new EventManager((AntColonyInterface) colonia, tao, eta); // esta mesmo a usar a interface??????????
            Evento.simular();
            grafo.MostrarVerticeInfo();
        }
    }
}