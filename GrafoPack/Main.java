package GrafoPack;

import ACO.AntColony;
import GrafoPack.Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nNodes = 0;       // number of nodes in the graph
        int n1 = 0;           // the nest node
        float alfa = 0;       // alpha, ant move event
        float beta = 0;       // beta, ant move event
        float delta = 0;      // delta, ant move event
        float eta = 0;        // eta, pheronome evaporation event
        float ro = 0;         // ro, pheronome evaporation event
        float gamma = 0;    // pheronome level
        int v = 0;            // ant colony size
        int tao = 0;          // final instant

        String arg = args[0];                      // read the argument -r or -f

        if (arg.equals("-r")){                     //reads from terminal
            if (args.length != 11) {
                System.out.println("Invalid number of arguments");
                System.exit(0);             //terminates program ???
            }
            nNodes = Integer.parseInt(args[1]);
            n1 = Integer.parseInt(args[2]);
            alfa = Float.parseFloat(args[3]);
            beta = Float.parseFloat(args[4]);
            delta = Float.parseFloat(args[5]);
            eta = Float.parseFloat(args[6]);
            ro = Float.parseFloat(args[7]);
            gamma = Float.parseFloat(args[8]);
            v = Integer.parseInt(args[9]);
            tao = Integer.parseInt(args[10]);
        }
        else if (arg.equals("-f")){          //reads from file
            try {
                String path = args[1];
                File file = new File(path);  // Specify the path to your text file

                Scanner scanner = new Scanner(file);

                String inputLine = scanner.nextLine();
                String[] inputs = inputLine.split(" "); // split the line into an array of strings

                nNodes = Integer.parseInt(args[0]);
                n1 = Integer.parseInt(args[1]);
                alfa = Float.parseFloat(args[2]);
                beta = Float.parseFloat(args[3]);
                delta = Float.parseFloat(args[4]);
                eta = Float.parseFloat(args[5]);
                ro = Float.parseFloat(args[6]);
                gamma = Float.parseFloat(args[7]);
                v = Integer.parseInt(args[8]);
                tao = Integer.parseInt(args[9]);

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());}
        }
        else{
            System.out.println("Invalid argument, use -r or -f");
        }


        Grafo grafo = new Grafo(nNodes,5,8);

        AntColony antColony = new AntColony(grafo, 1, alfa, beta, gamma, delta, eta,10);
        grafo.MostrarVerticeInfo(1);

    }
}