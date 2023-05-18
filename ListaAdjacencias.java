package GrafoPack;

public class ListaAdjacencias {
    
    
    int maxLig = 7;
    
    Ponteiro pointers[] = new Ponteiro[this.maxLig];

    ListaAdjacencias() {

        for (int aux = 0; aux < this.maxLig; aux++) {

            pointers[aux] = new Ponteiro();

        }
    }

    public void Add(vertice newVertice, int custo) {

        for (int i = 0; i < this.maxLig; i++) {

            if (this.pointers[i].returnCusto() == 0) {

                this.pointers[i].CriarPointer(newVertice, custo);

                break;
            }

        }

    }

    public void remove(vertice remove) {

        for (int i = 0; i < this.maxLig; i++) {

            if (remove == this.pointers[i].returnPointer()) {
                this.pointers[i].removePointer();
            }

        }

    }

    public void PrintLista() {

        for (int i = 0; i < maxLig; i++) {

            if (this.pointers[i] != null) {

                if (this.pointers[i].returnCusto() != 0) {
                    System.out.println("  "+this.pointers[i].verticeInfo());
                }
            }
        }

    }

}