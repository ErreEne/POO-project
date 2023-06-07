import ACO.AntColony;
import ACO.AntColonyInterface;
import DSS.Event;
import DSS.EventManager;
import GrafoPack.Grafo;
import GrafoPack.GrafoInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainAnt {
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
        GrafoInterface grafo = null;
        Event Evento;
        String arg = args[0];
        // System.out.println(arg); // read the argument -r or -f
        AntColony colonia;

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
            grafo = new Grafo(2, 2); // edges e peso não são dadas no input

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

                /*
                 * grafo = new Grafo(nNodes); // usa o segundo construtor
                 *
                 * for(int i=1; i <= nNodes; i++){
                 * grafo.CriarVertice(i);
                 * }
                 * while (scanner.hasNextLine()) {
                 * String line = scanner.nextLine();
                 * String[] elements = line.split("[\\s\\t]+");
                 * int k=0;
                 * for (int i = 1; i <= nNodes; i++) {
                 * for (int j = i+1; j <= nNodes; j++) {
                 * grafo.AdicionarLiga(i, j, elements[k])
                 * k++;
                 * }
                 * }
                 * }
                 */
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid argument, use -r or -f");
        }
        if (grafo != null) {
            colonia = new AntColony(grafo, nest_node, alfa, beta, gamma, delta, eta, v, ro, tao);
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();
            colonia.run();


        }

    }
}
