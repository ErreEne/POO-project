package GrafoPack;

public class VerticeSuper {

    public int elemento;

    VerticeSuper(int elementoNovo) {
        this.elemento = elementoNovo;
    }

    public int GetVerticeInfo() {
        return this.elemento;
    }

    public void PrintInfo() {

        System.out.println(this.elemento);

    }
}
