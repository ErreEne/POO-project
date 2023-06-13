/**
 * This package contains classes related to graph operations.
 * It provides functionality for creating, graphs.
 */
package GrafoPack;

import java.util.Hashtable;

/**
 * Interface that represents a graph
 */
public interface GrafoInterface {

    /**
     *
     * @param vertice vertice to get information
     * @return information of the vertice
     */
    Hashtable<Integer, Integer> getEdges(int vertice);

    /**
     *
     * @return the total number of vertices
     */
    int totalVertex();

    /**
     *
     * @return the total weight of the edges
     */
    int totalEdgesSum();

    /**
     *
     * @return the total number of edges
     */
    int totalEdges();

    /**
     *
     * @param vertice1 vertice to add
     * @param vertice2 vertice to add
     * @param peso    weight of the edge
     */
    void AdicionarLiga(int vertice1, int vertice2, int peso);

    /**
     *
     * @param a first vertice
     * @param b second vertice
     * @return the weight of the edge
     */
    int GetCusto(int a, int b);

    /**
     * info of the vertice
     */
    void MostrarVerticeInfo();

}