package LeitorDeArgs;

import GrafoPack.Grafo;
import java.util.*;

public class readStrategy implements stratsInterface {

    ArrayList<Number> constantes;
    Grafo grafo;

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

        constantes.remove(0);
        constantes.remove(0);
    }

    public Grafo getGrafo() {

        return grafo;

    }

    public ArrayList<Number> getConstants() {
        return constantes;
    }

}
