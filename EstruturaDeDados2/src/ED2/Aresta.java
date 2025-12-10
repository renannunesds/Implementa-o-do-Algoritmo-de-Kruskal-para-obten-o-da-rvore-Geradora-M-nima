package ED2;

class Aresta implements Comparable<Aresta> {
    int origem, destino, peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta o) {
        return this.peso - o.peso;
    }

    @Override
    public String toString() {
        return origem + " - " + destino + " (peso: " + peso + ")";
    }
}

