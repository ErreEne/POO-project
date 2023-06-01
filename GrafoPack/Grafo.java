package GrafoPack;

import ACO.AntColony;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafo implements GrafoInterface {

    int MaxVertices;
    vertice[] Vertices;
    int verticenovo;
    int edges;
    int peso;

    public Grafo(int verticeNumero, int edges, int peso) {
        this.MaxVertices = verticeNumero;
        this.Vertices = new vertice[this.MaxVertices];
        this.verticenovo = 0;
        this.edges = edges;
        this.peso = peso;

        this.GenerateGraphWHamiltonCycle(peso);
    }

    public Grafo(int flag) {

        if (flag == 0) {

        } else {
            this.MaxVertices = 5;
            this.Vertices = new vertice[this.MaxVertices];
            this.verticenovo = 0;

            this.CriarVertice(1);
            this.CriarVertice(2);
            this.CriarVertice(3);
            this.CriarVertice(4);
            this.CriarVertice(5);

            this.AdicionarLiga(1, 3, 6);
            this.AdicionarLiga(1, 2, 3);
            this.AdicionarLiga(1, 4, 6);
            this.AdicionarLiga(1, 5, 2);
            this.AdicionarLiga(2, 3, 3);
            this.AdicionarLiga(2, 5, 5);
            this.AdicionarLiga(2, 4, 2);
            this.AdicionarLiga(4, 5, 1);

        }

    }

    @Override
    public Hashtable<Integer, Integer> getEdges(int vertice) {
        Hashtable<Integer, Integer> edges = new Hashtable<Integer, Integer>();
        ArrayList<Ponteiro> edgesaux = new ArrayList<Ponteiro>();
        vertice aux = this.Vertices[vertice - 1];
        edgesaux = aux.getPonteiros();

        for (Ponteiro x : edgesaux) {
            edges.put(x.GetVerticeInfo(), x.getCusto());
        }

        return edges;

    }

    @Override
    public int totalVertex() {
        return this.MaxVertices;
    };

    /**
     * Este metodo cria um vertice e guarda-o na estrutura dos grafos
     * 
     * @param verticeInfo String que identifica o vertice (isto talvez se muda para
     *                    um inteiro)
     */

    public void CriarVertice(int verticeInfo) {

        this.Vertices[this.verticenovo] = new vertice(verticeInfo);
        this.verticenovo++;

    }

    /**
     * metodo que cria o graph com o hamilton cycle
     * 
     * @param peso
     */
    /*
     * 
     * 
     * 
     * 
     * Talvez tenhamos de ver isto mais tarde
     * 
     * 
     * 
     * 
     * 
     */
    public void GenerateGraphWHamiltonCycle(int peso) {

        int aux, aux1, flag = 0;
        ArrayList<Integer> EdgeAux = new ArrayList<Integer>();
        ArrayList<Integer> ligFeitas = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 0; i < this.MaxVertices; i++) {

            this.CriarVertice(i + 1);
            EdgeAux.add(i + 1);

        }
        ligFeitas.add(1);
        EdgeAux.remove(0);
        // hamiltonCycle
        for (int i = 1; i < this.MaxVertices; i++, this.edges--) {

            aux = rand.nextInt(EdgeAux.size());
            ligFeitas.add(EdgeAux.get(aux));
            this.AdicionarLiga(ligFeitas.get(i), ligFeitas.get(i - 1), rand.nextInt(peso));
            EdgeAux.remove(aux);

        }
        this.AdicionarLiga(ligFeitas.get(ligFeitas.size() - 1), ligFeitas.get(0), rand.nextInt(peso) + 1);

        // resto do grafo
        for (; this.edges > 0;) {

            aux = rand.nextInt(this.MaxVertices);
            aux1 = rand.nextInt(this.MaxVertices);
            flag++;
            if (aux != aux1 && !this.Vertices[aux].checkLig(this.Vertices[aux1])) {
                flag = 0;
                this.AdicionarLiga(this.Vertices[aux].GetVerticeInfo(), this.Vertices[aux1].GetVerticeInfo(),
                        rand.nextInt(peso));
                this.edges--;

            }

            if (flag == 50) {
                break;
            }

        }

    }

    /**
     * Criar uma ligação entre vertices
     * 
     *
     * @param a     vertice 1 ident
     * @param b     vertice 2 ident
     * @param custo custo da ligação
     */

    public void AdicionarLiga(int a, int b, int custo) {

        vertice v1 = null;
        vertice v2 = null;

        for (int i = 0; i < this.verticenovo; i++) {

            if (this.Vertices[i].GetVerticeInfo() == a) {
                v1 = this.Vertices[i];
            }
            if (this.Vertices[i].GetVerticeInfo() == b) {
                v2 = this.Vertices[i];
            }

        }

        if (v1 != null && v2 != null) {

            v1.NovaLigação(b, custo);
            v2.NovaLigação(a, custo);
        }

    }

    public boolean CheckLiga(vertice a, vertice b) {

        return a.checkLig(b);
    }

    /**
     * Print da informação sobre um vertice especifico
     * 
     * @param ab String identificadora do vertice
     */

    public void MostrarVerticeInfo(int ab) {

        for (int i = 0; i < this.verticenovo; i++) {

            Vertices[i].PrintLigações();

        }
    }

    public int[][] matrizAdjacencia() {
        int[][] matriz = new int[this.MaxVertices][this.MaxVertices];

        for (int i = 0; i < this.MaxVertices; i++) {
            for (int j = 0; j < this.MaxVertices; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = this.Vertices[i].GetCustoLig(this.Vertices[j]);
                }

            }
        }

        return matriz;
    }

    public void GrafoFromFile(String path) {
        try {
            int nNodes = 0;       // number of nodes in the graph
            int nest_node = 0;           // the nest node
            float alfa = 0;       // alpha, ant move event
            float beta = 0;       // beta, ant move event
            float delta = 0;      // delta, ant move event
            float eta = 0;        // eta, pheronome evaporation event
            float ro = 0;         // ro, pheronome evaporation event
            float gamma = 0;    // pheronome level
            int v = 0;            // ant colony size
            float tao = 0;          // final instant
            int[][] matriz = new int[0][]; // adjacency matrix

            File file = new File(path);  // Specify the path to your text file
            Scanner scanner = new Scanner(file);

            String inputLine = scanner.nextLine();
            String[] inputs = inputLine.split(" "); // split the line into an array of strings
            if (inputs.length != 10) {
                System.out.println("Invalid number of input values");
                System.exit(0);             //terminates program ???
            }
            nNodes = Integer.parseInt(inputs[0]);
            nest_node = Integer.parseInt(inputs[1]);
            alfa = Float.parseFloat(inputs[2]);
            beta = Float.parseFloat(inputs[3]);
            delta = Float.parseFloat(inputs[4]);
            eta = Float.parseFloat(inputs[5]);
            ro = Float.parseFloat(inputs[6]);
            gamma = Float.parseFloat(inputs[7]);
            v = Integer.parseInt(inputs[8]);
            tao = Float.parseFloat(inputs[9]);

            int j=0,i=0;
            matriz = new int[nNodes][nNodes];
            for (i = 0; i < nNodes; i++) {
                for (j = 0; j < nNodes; j++) {
                    matriz[i][j] = 0;
                }
            }
            j=0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split("[\\s\\t]+");
                for (i=0; i < nNodes; i++){
                    matriz[j][i]= Integer.parseInt(elements[i]);
                }
                j++;
            }
            for(i = 0; i < nNodes; i++){
                for(j = 0; j < nNodes; j++){
                    System.out.print(matriz[i][j] + "a ");
                }
                System.out.println("   ");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());}
    }
}
