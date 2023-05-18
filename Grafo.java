package GrafoPack;

public class Grafo {
    public int MaxVertices;
    vertice Vertices[];
    int verticenovo = 0;
    int edges;
    int peso;

    Grafo(int verticeNumero, int edges, int peso) {
        this.MaxVertices = verticeNumero;
        this.Vertices = new vertice[this.MaxVertices];
        this.edges = edges;
        this.peso = peso;
    }

    Grafo(int flag) {

        if (flag != 0) {
        } else {

            this.CriarVertice("1");
            this.CriarVertice("2");
            this.CriarVertice("3");
            this.CriarVertice("4");
            this.CriarVertice("5");

            this.AdicionarLiga("1", "3", 6);
            this.AdicionarLiga("1", "2", 6);
            this.AdicionarLiga("1", "4", 3);
            this.AdicionarLiga("1", "5", 2);
            this.AdicionarLiga("2", "3", 3);
            this.AdicionarLiga("2", "5", 5);
            this.AdicionarLiga("2", "4", 2);
            this.AdicionarLiga("4", "5", 1);

            this.MostrarVerticeInfo("2");

        }

    }

    /**
     * Este metodo cria um vertice e guarda-o na estrutura dos grafos
     * 
     * @param verticeInfo String que identifica o vertice (isto talvez se muda para
     *                    um inteiro)
     */

    public void CriarVertice(String verticeInfo) {

        this.Vertices[this.verticenovo] = new vertice(verticeInfo);
        this.verticenovo++;

    }

    /**
     * metodo que cria o graph com o hamilton cycle
     * 
     * @param peso
     */

    public void GenerateGraphWHamiltonCycle(int peso) {

        // int EdgeAux = this.edge;

        for (int i = 0; i < this.MaxVertices; i++) {

            this.CriarVertice(Integer.toString(i + 1));

        }

        // hamiltonCycle

    }

    /**
     * Criar uma ligação entre vertices
     * 
     *
     * @param a     vertice 1 ident
     * @param b     vertice 2 ident
     * @param custo custo da ligação
     */

    public void AdicionarLiga(String a, String b, int custo) {

        vertice v1 = null;
        vertice v2 = null;

        for (int i = 0; i < this.verticenovo; i++) {

            if (this.Vertices[i].NomeVertice() == a) {
                v1 = this.Vertices[i];
            }
            if (this.Vertices[i].NomeVertice() == b) {
                v2 = this.Vertices[i];
            }

        }

        v1.NovaLigação(v2, custo);
        v2.NovaLigação(v1, custo);

    }

    /**
     * Remover ligação
     * 
     * @param a
     * @param b
     */

    public void RemoverLiga(vertice a, vertice b) {
        a.RemoverLigação(b);
        b.RemoverLigação(a);
    }

    /**
     * Print da informação sobre um vertice especifico
     * 
     * @param ab String identificadora do vertice
     */

    public void MostrarVerticeInfo(String ab) {
        vertice a = null;

        for (int i = 0; i < this.verticenovo; i++) {

            if (this.Vertices[i].NomeVertice() == ab) {

                a = this.Vertices[i];
                break;

            }

        }

        a.PrintLigações();
    }

}
