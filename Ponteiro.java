package GrafoPack;

public class Ponteiro {

    vertice pointer = new vertice();
    int custo = 0;

    Ponteiro() {}

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

    public String verticeInfo() {

        return this.pointer.NomeVertice();
    }

}
