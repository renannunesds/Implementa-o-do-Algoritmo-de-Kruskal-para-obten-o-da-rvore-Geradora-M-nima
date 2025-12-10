package ED2;

class UnionFind {
    private int[] pai;
    private int[] rank;

    public UnionFind(int n) {
        pai = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            pai[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (pai[x] != x)
            pai[x] = find(pai[x]);
        return pai[x];
    }

    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rx != ry) {
            if (rank[rx] < rank[ry])
                pai[rx] = ry;
            else if (rank[rx] > rank[ry])
                pai[ry] = rx;
            else {
                pai[ry] = rx;
                rank[rx]++;
            }
        }
    }
}

