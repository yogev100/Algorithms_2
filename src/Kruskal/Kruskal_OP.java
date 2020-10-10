package Kruskal;

import java.util.Arrays;
import java.util.Collections;

public class Kruskal_OP {
//	int vertexGroup[];
	Edge [] graph;	
	Edge [] tree;	
	int numOfEdges, numOfVertexes, numEdgesInMST;
	int mat[][];
	
	/// constructor
	public Kruskal_OP(Edge [] graph){
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
		Arrays.sort(this.graph,Collections.reverseOrder());
//		vertexGroup = new int[numOfVertexes];
		tree = new Edge [numOfVertexes];
//		for (int i=0; i<numOfVertexes; i++){
//			vertexGroup[i] = i;
//		}
		
		//initial matrix for bfs checking (one component)
		mat=new int[numOfVertexes][numOfVertexes];
		for (int i = 0; i < graph.length; i++) {
			mat[graph[i].getVertexA()-1][graph[i].getVertexB()-1]=1;
			mat[graph[i].getVertexB()-1][graph[i].getVertexA()-1]=1;
		}
	}
	public void MSPCreate(){
		int i=0;
		Edge[] MST=graph;
		int numV=numOfVertexes;
		int numE=graph.length;
		while(numE>numV-1){
			Edge max_e = graph[i];
			Edge[] T=MST_Minus_Max(MST,max_e);
			int[][] temp=copy_2dim_arr(mat);//deep copy
			mat[max_e.getVertexA()-1][max_e.getVertexB()-1]=0;
			mat[max_e.getVertexB()-1][max_e.getVertexA()-1]=0;
			BFS bfs= new BFS(mat);
			bfs.Run_BFS(0);
			if(bfs.get_num_comp()==1) {
				MST=T;
				numE--;
			}
			i++;
		}
	}
	
	private int[][] copy_2dim_arr(int[][] arr){
		int[][] temp=new int[arr.length][arr.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i]=Arrays.copyOf(arr[i], arr[i].length);
		}
		return temp;
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
	
	private Edge[] MST_Minus_Max(Edge []edges,Edge max) {
		Edge[] ans=new Edge[edges.length-1];
		for (int i = 0; i < ans.length; i++) {
			if(!edges[i].equals(max)) {
				ans[i]=edges[i];
			}
		}
		return ans;
	}

	
	public static void main(String[] args) {
		KruskalTable kt = new KruskalTable(InitGraphs.init3());
		kt.MSPCreate();
		kt.printTree();
		System.out.println("sum weight = "+kt.calcSummWieight());
	}
}
