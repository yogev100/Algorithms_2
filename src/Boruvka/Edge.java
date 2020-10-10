package Boruvka;

public class Edge implements Comparable<Edge>{
	private final int v;
    private final int u;
    private final int weight;

    /**
     * @param  v one vertex
     * @param  u the other vertex
     * @param  weight the weight of this edge
     */
    public Edge(int v, int u, int weight) {
        this.v = v;
        this.u = u;
        this.weight = weight;
    }

    /**
     * @return the weight of this edge
     */
    public int weight() {
        return weight;
    }

    /**
     * @return either end-point of this edge
     */
    public int either() {
        return v;
    }

    /**
 -   * @param  vertex one end-point of this edge
     * @return the other end-point of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         end-points of this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return u;
        else if (vertex == u) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by weight.
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(Edge that) {
        if      (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return +1;
        else                                    return  0;
    }

    /**
     * @return a string representation of this edge
     */
    public String toString() {
        return "[v:" + v + ",u:" + u + ",weight:"+weight +"]";
    }
    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5);
        System.out.println(e);
    }
}
