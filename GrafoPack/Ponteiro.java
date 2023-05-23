package GrafoPack;

public class Ponteiro {

    vertice pointer;
    int custo = 0;

    Ponteiro(vertice Ponteiro, int weight) {

        this.pointer = Ponteiro;
        this.custo = weight;

    }

    public void CriarPointer(vertice apontador, int custo) {

        this.custo = custo;
        this.pointer = apontador;

    }

    public int returnCusto() {
        return this.custo;
    }

    public vertice returnPointer() {
        return this.pointer;
    }

    public void removePointer() {
        this.pointer = null;
        this.custo = 0;
    }

    public int verticeInfo() {

        return this.pointer.GetNomeVertice();
    }

}
