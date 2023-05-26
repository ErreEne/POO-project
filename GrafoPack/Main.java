package GrafoPack;

import ACO.AntColony;

public class Main {
    public static void main(String[] args) {
        int nNodes= 5;
        float alfa=1;
        float beta=1;
        float gamma = 1;
        float delta = 1;
        float eta = 1;

        Grafo grafo = new Grafo(nNodes,5,8);

        AntColony antColony = new AntColony(grafo, 1, alfa, beta, gamma, delta, eta,69);
        grafo.MostrarVerticeInfo(1);

    }
}