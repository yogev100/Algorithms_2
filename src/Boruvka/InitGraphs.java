package Boruvka;

public class InitGraphs {
	public static Edge[] init3(){
		int numOfEdges = 10;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(0,1,6);	// 1-st edge
		graph[1] = new Edge(1,2,19);	// 2-st edge
		graph[2] = new Edge(2,3,9);	// 3-st edge
		graph[3] = new Edge(3,4,14);	// 4-st edge
		graph[4] = new Edge(4,5,21);// 5-st edge
		graph[5] = new Edge(4,6,2);// 6-st edge
		graph[6] = new Edge(6,7,8);// 7-st edge
		graph[7] = new Edge(7,0,17);// 8-st edge
		graph[8] = new Edge(6,0,11);// 9-st edge
		graph[9] = new Edge(6,1,25);// 10-st edge
		return graph;
	}

	
	public static Edge[] init4(){
		int numOfEdges = 4;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(0,1,3);	// 1-st edge
		graph[1] = new Edge(1,2,12);	// 2-st edge
		graph[2] = new Edge(1,3,5);	// 3-st edge
		graph[3] = new Edge(2,3,1);	// 4-st edge
		return graph;
	}
	public static Edge[] init5(){
		int numOfEdges = 4;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(0,1,12);	// 1-st edge
		graph[1] = new Edge(1,2,3);	// 2-st edge
		graph[2] = new Edge(1,3,5);	// 3-st edge
		graph[3] = new Edge(2,3,1);	// 4-st edge
		return graph;
	}
}
