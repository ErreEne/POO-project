package GrafoPack;

import java.util.ArrayList;

/**
 * Class that represents a vertex of a graph
 */
public class vertice extends VerticeSuper {

    public ArrayList<Ponteiro> ListaPonteiros;

    /**
     * @param elementoNovo Element that this vertex will have
     */
    public vertice(int elementoNovo) {
        super(elementoNovo);
        this.ListaPonteiros = new ArrayList<>();
    }

    /**
     * Creates a new pointer to a vertex
     * @param id    ID of the vertex
     * @param custo Cost of the pointer
     */
    public void NovaLigacao(int id, int custo) {
        Ponteiro NewPointer = new Ponteiro(id, custo);
        ListaPonteiros.add(NewPointer);
    }

    /**
     * Checks if a vertex is connected to this vertex
     * @param novo Vertex to be checked
     * @return  True if the vertex is connected to this vertex
     */
    public boolean checkLig(VerticeSuper novo) {
        for (Ponteiro x : ListaPonteiros) {
            if (x.GetVerticeInfo() == novo.GetVerticeInfo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Element of the vertex
     */
    public int GetVerticeInfo() {
        return this.elemento;
    }

    /**
     * @return List of pointers of this vertex
     */
    public ArrayList<Ponteiro> getPonteiros() {
        return this.ListaPonteiros;
    }

    public void printLigacoes() {

        System.out.println("Elementos ligados a " + this.elemento + ":");
        for (Ponteiro l : ListaPonteiros) {
            System.out.println(l.GetVerticeInfo() + " " + l.getCusto());
        }

    }

    /**
     * Get the cost of a pointer to a vertex
     * @param Vertice Vertex to be checked
     * @return  Cost of the pointer to the vertex
     */
    public int GetCustoLig(VerticeSuper Vertice) {
        for (Ponteiro x : this.ListaPonteiros) {
            if (x.GetVerticeInfo() == Vertice.GetVerticeInfo()) {
                return x.getCusto();
            }
        }
        return 0;
    }
}