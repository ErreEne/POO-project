/**
 * This package contains classes related to graph operations.
 * It provides functionality for creating, graphs.
 */
package GrafoPack;

/**
 * Class that represents a vertex of a graph
 */
public class VerticeSuper {

    /**
     * Element of the vertex
     */
    int elemento;

    /**
     * @param elementoNovo Element that this vertex will have
     */
    VerticeSuper(int elementoNovo) {
        this.elemento = elementoNovo;
    }

    /**
     * @return Element of the vertex
     */
    int GetVerticeInfo() {
        return this.elemento;
    }
}
