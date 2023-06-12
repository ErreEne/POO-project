import DSS.*;
import ACO.*;
import GrafoPack.*;
import LeitorDeArgs.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        GrafoInterface Grafo;
        AntColonyInterface colonia;
        EventSimulation Simulacao;
        ArrayList<Number> constantes;

        stratChooser strategies = new stratChooser();
        strategies.setStrategy("-f", new fileStrategy());
        strategies.setStrategy("-r", new readStrategy());

        strategies.readArgs(args);
        Grafo = strategies.getGrafo();
        Grafo.MostrarVerticeInfo();
        constantes = strategies.getConstantes();

        colonia = new AntColony(Grafo, (Integer) constantes.get(0),
                (Float) constantes.get(1), (Float) constantes.get(2), (Float) constantes.get(6),
                (Float) constantes.get(3), (Integer) constantes.get(7), (Float) constantes.get(5));

        Simulacao = new EventManager(colonia, (float) constantes.get(8), (float) constantes.get(4));

        Simulacao.simular(Grafo.totalEdges() + 1 + (Integer) constantes.get(7));

    }
}