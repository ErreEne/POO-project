package GrafoPack;

public class vertice {

    String elemento;
    ListaAdjacencias Lista;

    vertice(String elementoNovo) {
        this.elemento = elementoNovo;
        Lista = new ListaAdjacencias();
    }

    vertice() {
    }

    public void NovaLigação(vertice teste, int custo) {
        Lista.Add(teste, custo);
    }

    public String NomeVertice() {

        return this.elemento;

    }

    public void PrintLigações() {

        System.out.println("Elementos ligados a " + this.elemento + ":");

        Lista.PrintLista();

    }

    public void RemoverLigação(vertice remove) {
        Lista.remove(remove);
    }

    public ListaAdjacencias retornarLista(){

        return this.Lista;

    }

}
