package Boruvka;

import java.util.ArrayList;

public class BoruvkaMST {
	private ArrayList<Edge> mst;   // edges in MST
	private double weight;                      // weight of MST

	public BoruvkaMST(Edge[] graph, int n){//n - number of vertices
		UF uf = new UF(n);
		mst = new ArrayList<>(n-1);
		while (mst.size() < n-1) {//O(log(n)) 
			// for-each tree in forest, find closest edge
			// if edge weights are equal, ties are broken in favor of first edge in G.edges()
			Edge[] closest = new Edge[n];
			for (Edge e : graph) {//O(|E|)
				int v = e.either(), u = e.other(v);
				int vRoot = uf.find(v);
				int wRoot = uf.find(u);
				if (vRoot == wRoot) continue;   // same tree
				if (closest[vRoot] == null || e.weight() < closest[vRoot].weight()) closest[vRoot] = e;
				if (closest[wRoot] == null || e.weight() < closest[wRoot].weight()) closest[wRoot] = e;
			}
			// add newly discovered edges to MST
			for (int i = 0; i < n; i++) {
				Edge e = closest[i];
				if (e != null) {
					int v = e.either();
					int u = e.other(v);
					// don't add the same edge twice
					if (!uf.connected(v, u)) {
						mst.add(e);
						weight = weight + e.weight();
						uf.union(v, u);
					}
				}
			}
		}
	}
	/**
	 * @return the sum of the edge weights in a minimum spanning tree (or forest)
	 */
	public double weight() {
		return weight;
	}
	public ArrayList<Edge> edges() {
		return mst;
	}


	public static void main(String[] args) {
		Edge[] graph = InitGraphs.init3();
		BoruvkaMST b = new BoruvkaMST(graph, 8);
		double weight = b.weight();
		System.out.println("weight = "+weight);
		System.out.println(b.mst);
	}
}
