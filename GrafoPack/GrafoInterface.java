package GrafoPack;

import java.util.Hashtable;

public interface GrafoInterface {

    Hashtable<Integer, Integer> getEdges(int vertice);

    int totalVertex();

    int totalEdgesSum();

    void AdicionarLiga(int vertice1, int vertice2, int peso);

    int GetCusto(int a, int b);

    void MostrarVerticeInfo();

}