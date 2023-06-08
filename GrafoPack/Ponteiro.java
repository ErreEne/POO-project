package GrafoPack;

/**
 * Class that represents a pointer to a vertex in a graph.
 */
public class Ponteiro extends VerticeSuper {

    int custo;

    /**
     * @param elementoLig - element to which the pointer points
     * @param weight    - weight of the pointer
     */
    Ponteiro(int elementoLig, int weight) {

        super(elementoLig);
        this.custo = weight;

    }

    /**
     * Get of the weight of the pointer
     * @return weight of the pointer
     */
    public int getCusto() {
        return this.custo;
    }

}
