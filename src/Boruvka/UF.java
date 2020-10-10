package Boruvka;

import java.util.Arrays;

public class UF {
	private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    /**
     * @param  N the number of sites
     */
    public UF(int N) {
        count = N;
        parent = new int[N];
        rank = new byte[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site 
     * @throws IndexOutOfBoundsException unless
     */
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    /**
     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
     */
    public int count() {
        return count;
    }
  
    /**
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return true if the two sites p and q are in the same component;
     *         false otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
  
    /**
     * Merges the component containing site p with the 
     * the component containing site q
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
	public String toString() {
		return "UF\np " + Arrays.toString(parent) + "\nr " + Arrays.toString(rank) + "\n>";
	}
  
    public static void main(String[] args) {
		int numSets = 5;
		UF ds = new UF(numSets);
		System.out.println(ds);
		ds.union(1,2);
		System.out.println("union 1 2");
		System.out.println("find 1 "+ds.find(1) + ", find 2 " + ds.find(2));
		System.out.println(ds);
		ds.union(3,4);
		System.out.println("union 3 4");
		System.out.println(ds);
		ds.union(1,3);
		System.out.println("union 1 3");
		System.out.println(ds);
		ds.union(1,4);
		System.out.println("union 1 4");
		System.out.println(ds);
   }
}
