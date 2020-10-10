package Kruskal;

/**
    The Edge class represents an edge: (A,B) with the weight 
*/
public class Edge implements Comparable<Edge>{
    private int vertexA, vertexB;
    private int weight;
// Constructors
    public Edge(int vertexA, int vertexB, int weight){
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }
//get vertex A
    public int getVertexA(){
        return vertexA;
    }
//get vertex B
    public int getVertexB(){
        return vertexB;
    }
//get weight
    public int getWeight(){
        return weight;
    }

    public String toString(){
        return "(" + vertexA + "," + vertexB + ",w:" + weight+")";
    }
// implements compareTo()function of Comparable Interface     
    public int compareTo(Edge edge){
    	int ans = 0;
    	if (this.weight < edge.weight) ans = -1;
    	else  if (this.weight > edge.weight) ans = 1; 
    	else ans = 0;
    	return ans;
    }
    public boolean equals(Edge edge){
    	boolean ans = (this.vertexA==edge.vertexA && this.vertexB==edge.vertexB) || 
    			      (this.vertexA==edge.vertexB && this.vertexB==edge.vertexA);
    	return ans;
    }
}
