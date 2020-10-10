package Prim;

public class Edge {
	int vertexA, vertexB;
    int weight;
// Constructor
    public Edge(int vertexA, int vertexB, int weight){
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
    }
    public String toString(){
        return "(" + vertexA + "," + vertexB + ",w:" + weight+")";
    }
}
