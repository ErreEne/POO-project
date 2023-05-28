package GrafoPack;

import java.util.ArrayList;

public class vertice {

    public int elemento;
    public ArrayList<Ponteiro> ListaPonteiros;

    vertice(int elementoNovo) {
        this.elemento = elementoNovo;
        this.ListaPonteiros = new ArrayList<Ponteiro>();
    }

    vertice() {
    }

    public void NovaLigação(vertice teste, int custo) {

        Ponteiro aux = new Ponteiro(teste, custo);

        ListaPonteiros.add(aux);

    }

    public boolean checkLig(vertice novo) {

        for (Ponteiro x : ListaPonteiros) {

            if (x.returnPointer() == novo) {

                return true;
            }
        }

        return false;

    }

    public int GetNomeVertice() {
        return this.elemento;
    }

    public ArrayList<Ponteiro> getPonteiros() {
        return this.ListaPonteiros;
    }

    public void PrintLigações() {

        System.out.println("Elementos ligados a " + this.elemento + ":");
        for (Ponteiro l : ListaPonteiros) {
            System.out.println(l.verticeInfo());
        }

    }


    public int GetCustoLig(int y) {
        for(Ponteiro x : this.ListaPonteiros) {
            if(x.verticeInfo() == y) {
                return x.getCusto();
            }
        }
        return 0;
    }
}
