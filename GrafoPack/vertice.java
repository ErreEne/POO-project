package GrafoPack;

import java.util.ArrayList;

public class vertice extends VerticeSuper {

    public ArrayList<Ponteiro> ListaPonteiros;

    vertice(int elementoNovo) {
        super(elementoNovo);
        this.ListaPonteiros = new ArrayList<Ponteiro>();
    }

    public void NovaLigação(int id, int custo) {

        Ponteiro NewPointer = new Ponteiro(id, custo);

        ListaPonteiros.add(NewPointer);

    }

    public boolean checkLig(VerticeSuper novo) {

        for (Ponteiro x : ListaPonteiros) {

            if (x == novo) {

                return true;
            }
        }

        return false;

    }

    public int GetVerticeInfo() {
        return this.elemento;
    }

    public ArrayList<Ponteiro> getPonteiros() {
        return this.ListaPonteiros;
    }

    public void PrintLigações() {

        System.out.println("Elementos ligados a " + this.elemento + ":");
        for (Ponteiro l : ListaPonteiros) {
            System.out.println(l.GetVerticeInfo());
        }

    }

    public int GetCustoLig(VerticeSuper Vertice) {
        for (Ponteiro x : this.ListaPonteiros) {
            if (x == Vertice) {
                return x.getCusto();
            }
        }
        return 0;
    }
}