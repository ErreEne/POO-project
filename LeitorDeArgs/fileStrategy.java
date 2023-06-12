package LeitorDeArgs;

import GrafoPack.Grafo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileStrategy implements stratsInterface {

    private Grafo grafo;
    private ArrayList<Number> constants;
    private int[][] matriz;

    public fileStrategy() {
        matriz = new int[100][100];
        constants = new ArrayList<>();
    }

    @Override
    public void readArgs(String[] args) {

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

            constants.add(Integer.parseInt(inputs[1]));
            constants.add(Integer.parseInt(inputs[2]));
            constants.add(Float.parseFloat(inputs[3]));
            constants.add(Float.parseFloat(inputs[4]));
            constants.add(Float.parseFloat(inputs[5]));
            constants.add(Float.parseFloat(inputs[6]));
            constants.add(Float.parseFloat(inputs[7]));
            constants.add(Float.parseFloat(inputs[8]));
            constants.add(Integer.parseInt(inputs[9]));
            constants.add(Float.parseFloat(inputs[10]));

            int j = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split("[\\s\\t]+");
                System.out.println((int) constants.get(0));
                for (int i = 0; i < (int) constants.get(0); i++) {
                    matriz[j][i] = Integer.parseInt(elements[i]);
                }
                j++;
            }
            grafo = new Grafo((int) constants.get(0), matriz);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(0);
        }
        constants.remove(0);
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public ArrayList<Number> getConstants() {
        return constants;
    }

}
