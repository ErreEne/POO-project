/**
 * The LeitorDeArgs package provides classes for reading and parsing command-line arguments.
 * It allows developers to easily handle command-line arguments passed to a Java application.
 */
package LeitorDeArgs;

import GrafoPack.Grafo;
import java.util.*;

public class readStrategy implements stratsInterface {

    /**
     * the input parameters
     */
    private final ArrayList<Number> constantes;
    private Grafo grafo;

    /**
     * Constructor the strategy that reads from the command line
     */
    public readStrategy() {
        constantes = new ArrayList<>();
    }

    @Override
    public void readArgs(String[] args) {

        Random rand = new Random();

        if (args.length != 12) {
            System.out.println("Invalid number of arguments");
            System.exit(0); // terminates program ???
        } else {
            constantes.add(Integer.parseInt(args[1]));
            constantes.add(Integer.parseInt(args[2]));
            constantes.add(Integer.parseInt(args[3]));
            constantes.add(Float.parseFloat(args[4]));
            constantes.add(Float.parseFloat(args[5]));
            constantes.add(Float.parseFloat(args[6]));
            constantes.add(Float.parseFloat(args[7]));
            constantes.add(Float.parseFloat(args[8]));
            constantes.add(Float.parseFloat(args[9]));
            constantes.add(Integer.parseInt(args[10]));
            constantes.add(Float.parseFloat(args[11]));
        }
        int max = (Integer) constantes.get(0) * ((Integer) constantes.get(0) - 1);
        int min = (Integer) constantes.get(0);
        grafo = new Grafo((Integer) constantes.get(0), rand.nextInt(max - min + 1) + min, (Integer) constantes.get(1));
        printInputs();
        constantes.remove(0);
        constantes.remove(0);
    }

    public Grafo getGrafo() {

        return grafo;

    }

    public ArrayList<Number> getConstants() {
        return constantes;
    }

    /**
     * Prints the input parameters
     */
    private void printInputs() {
        System.out.println("Input parameters: ");
        System.out.println(constantes.get(0) + ": Number of nodes in the graph");
        System.out.println(constantes.get(2) + ": The nest node");
        System.out.println(constantes.get(3) + ": alpha, ant move event");
        System.out.println(constantes.get(4) + ": beta, ant move event");
        System.out.println(constantes.get(5) + ": delta, ant move event");
        System.out.println(constantes.get(6) + ": eta, pheromone evaporation event");
        System.out.println(constantes.get(7) + ": rho, pheromone evaporation event");
        System.out.println(constantes.get(8) + ": pheromone level");
        System.out.println(constantes.get(9) + ": ant colony size");
        System.out.println(constantes.get(10) + ": final instant");
    }
}
