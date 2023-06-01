package GrafoPack;

public class Ponteiro extends VerticeSuper {

    int custo;

    Ponteiro(int elementoLig, int weight) {

        super(elementoLig);
        this.custo = weight;

    }

    public int getCusto() {
        return this.custo;
    }

}
