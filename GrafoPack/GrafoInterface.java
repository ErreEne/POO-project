package GrafoPack;

import java.util.Hashtable;

public interface GrafoInterface {

    public Hashtable<Integer, Integer> getEdges(int vertice);

    public int totalVertex();

    public int totalEdgesSum();

    public void AdicionarLiga(int vertice1, int vertice2, int peso);

    public int GetCusto(int a, int b);

    public void MostrarVerticeInfo();
}