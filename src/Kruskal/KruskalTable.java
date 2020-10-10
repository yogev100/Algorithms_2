package Kruskal;

/**
 * The Kruskal algorithm for MST O(mlogm + n^2)
 */
import java.util.Arrays;
public class KruskalTable {
	int vertexGroup[];
	Edge [] graph;	
	Edge [] tree;	
	int numOfEdges, numOfVertexes, numEdgesInMST;
	/// constructor
	public KruskalTable(Edge [] graph){
		numOfEdges = graph.length;
		numEdgesInMST = 0;
		int numOfVertexes = 0;
	// number of vertexes calculation
		for (int i=0; i<numOfEdges; i++){
			Edge e = graph[i];
			if (e.getVertexA() > numOfVertexes) numOfVertexes = e.getVertexA();
			if (e.getVertexB() > numOfVertexes) numOfVertexes = e.getVertexB();			
		}
		this.numOfVertexes = numOfVertexes;
		this.graph = graph;
		Arrays.sort(this.graph);
		vertexGroup = new int[numOfVertexes];
		tree = new Edge [numOfVertexes];
		for (int i=0; i<numOfVertexes; i++){
			vertexGroup[i] = i;
		}
	}
	public void MSPCreate(){
		int i=0;
		while(numEdgesInMST<numOfVertexes && i < numOfEdges){
			Edge e = graph[i];
			int grA = vertexGroup[e.getVertexA()-1];
			int grB = vertexGroup[e.getVertexB()-1];
			if (grA != grB){
				tree[numEdgesInMST++] = graph[i];
				union(graph[i].getVertexA(), graph[i].getVertexB());
			}
			i = i + 1;
		}
	}
	private void union(int vertexA, int vertexB){
		int grA = vertexGroup[vertexA-1];
		int grB = vertexGroup[vertexB-1];
		for (int i=0;i<numOfVertexes; i++){
			if (vertexGroup[i]==grB){
				vertexGroup[i] = grA;
			}
		}		
	}
	public double calcSummWieight(){
		double w = 0;
		for (int i=0; i<numEdgesInMST; i++){
			w = w + tree[i].getWeight();
		}
		return w;
	}
	public void printTree(){
		for (int i=0; i<numEdgesInMST; i++){
			System.out.println(tree[i].toString());
		}
	}
	public static void main(String[] args) {
		KruskalTable kt = new KruskalTable(InitGraphs.init3());
		kt.MSPCreate();
		kt.printTree();
		System.out.println("sum weight = "+kt.calcSummWieight());
	}
}
/**
   result init1
    (7,8,w:1)
	(6,7,w:2)
	(3,9,w:2)
	(1,2,w:4)
	(3,6,w:4)
	(3,4,w:7)
	(2,3,w:8)
	(4,5,w:9)
	sum weight = 37.0
	
  result init2
	(1,4,w:4)
	(2,3,w:5)
	(1,2,w:7)
	(4,5,w:10)
	(5,6,w:11)
	sum weight = 37.0
	
 result init3
	(5,7,w:2)
	(1,2,w:6)
	(7,8,w:8)
	(3,4,w:9)
	(7,1,w:11)
	(4,5,w:14)
	(5,6,w:21)
	sum weight = 71.0


**/
