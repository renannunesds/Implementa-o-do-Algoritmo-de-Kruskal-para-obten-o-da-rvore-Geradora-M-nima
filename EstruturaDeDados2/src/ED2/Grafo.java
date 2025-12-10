package ED2;

import java.util.*;

class Grafo {
    int vertices;
    java.util.List<Aresta> arestas = new java.util.ArrayList<>();

    public Grafo(int vertices) {
        if (vertices <= 0)
            throw new IllegalArgumentException("O número de vértices deve ser positivo.");
        this.vertices = vertices;
    }

    public void adicionarAresta(int o, int d, int p) {
        if (o < 0 || o >= vertices || d < 0 || d >= vertices)
            throw new IndexOutOfBoundsException("Vértice inexistente.");

        if (p < 0)
            throw new IllegalArgumentException("Peso não pode ser negativo.");

        arestas.add(new Aresta(o, d, p));
    }

    public List<Aresta> kruskal() {
        if (arestas.isEmpty())
            throw new IllegalStateException("Não há arestas no grafo.");

        java.util.Collections.sort(arestas);

        UnionFind uf = new UnionFind(vertices);
        java.util.List<Aresta> mst = new java.util.ArrayList<>();

        for (Aresta a : arestas) {
            if (uf.find(a.origem) != uf.find(a.destino)) {
                mst.add(a);
                uf.union(a.origem, a.destino);
            }
        }

        return mst;
    }
}

