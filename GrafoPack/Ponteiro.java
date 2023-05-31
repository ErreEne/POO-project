package GrafoPack;

public class Ponteiro extends vertice {

    int custo = 0;

    Ponteiro(int elementoLig, int weight) {

        super(elementoLig);
        this.custo = weight;

    }

    public int getCusto() {
        return this.custo;
    }

}
