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
        float tao = 0;          // final instant

        String arg = args[0];
        System.out.println(arg); // read the argument -r or -f

        if (arg.equals("-r")){                     //reads from terminal
            if (args.length != 11) {
                System.out.println("Invalid number of arguments");
                System.exit(0);             //terminates program ???
            }
            else {
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
            /*System.out.print(nNodes + " ");
            System.out.print(n1 + " ");
            System.out.print(alfa + " ");
            System.out.print(beta + " ");
            System.out.print(delta + " ");
            System.out.print(eta + " ");
            System.out.print(ro + " ");
            System.out.print(gamma + " ");
            System.out.print(v + " ");
            System.out.print(tao+ " ");
            System.out.println("   ");*/
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
                n1 = Integer.parseInt(inputs[1]);
                alfa = Float.parseFloat(inputs[2]);
                beta = Float.parseFloat(inputs[3]);
                delta = Float.parseFloat(inputs[4]);
                eta = Float.parseFloat(inputs[5]);
                ro = Float.parseFloat(inputs[6]);
                gamma = Float.parseFloat(inputs[7]);
                v = Integer.parseInt(inputs[8]);
                tao = Float.parseFloat(inputs[9]);
                System.out.print(nNodes + " ");
                System.out.print(n1 + " ");
                System.out.print(alfa + " ");
                System.out.print(beta + " ");
                System.out.print(delta + " ");
                System.out.print(eta + " ");
                System.out.print(ro + " ");
                System.out.print(gamma + " ");
                System.out.print(v + " ");
                System.out.print(tao+ " ");
                System.out.println("   ");

                int j=0,i=0;
                int[][] matriz = new int[nNodes][nNodes];
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
                AntColony antColony = new AntColony(matriz, 1, alfa, beta, gamma, delta, eta,10);

                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());}
        }
        else{
            System.out.println("Invalid argument, use -r or -f");
        }

        Grafo grafo = new Grafo(2);
        //matriz = grafo.matrizAdjacencia();

        //AntColony antColony = new AntColony(matriz, 1, alfa, beta, gamma, delta, eta,10);
        //grafo.MostrarVerticeInfo(1);

    }
}