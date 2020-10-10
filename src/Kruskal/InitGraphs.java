package Kruskal;

public class InitGraphs {
	public static Edge[] init1(){
		int numOfEdges = 14;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(1,2,4);	// 1-st edge
		graph[1] = new Edge(2,3,8);	// 2-st edge
		graph[2] = new Edge(3,4,7);	// 3-st edge
		graph[3] = new Edge(4,5,9);	// 4-st edge
		graph[4] = new Edge(5,6,10);// 5-st edge
		graph[5] = new Edge(6,7,2);// 6-st edge
		graph[6] = new Edge(7,8,1);// 7-st edge
		graph[7] = new Edge(8,1,8);// 8-st edge
		graph[8] = new Edge(2,8,11);// 9-st edge
		graph[9] = new Edge(3,9,2);// 10-st edge
		graph[10] = new Edge(3,6,4);// 11-st edge
		graph[11] = new Edge(4,6,14);// 12-st edge
		graph[12] = new Edge(7,9,6);// 13-st edge
		graph[13] = new Edge(8,9,7);// 14-st edge
		return graph;
	}
	
	public static Edge[] init2(){
		int numOfEdges = 8;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(1,2,7);	// 1-st edge
		graph[1] = new Edge(2,3,5);	// 2-st edge
		graph[2] = new Edge(3,4,7);	// 3-st edge
		graph[3] = new Edge(4,5,10);	// 4-st edge
		graph[4] = new Edge(5,6,11);// 5-st edge
		graph[5] = new Edge(2,4,9);// 6-st edge
		graph[6] = new Edge(1,4,4);// 7-st edge
		graph[7] = new Edge(3,5,13);// 8-st edge
		return graph;
	}
	public static Edge[] init3(){
		int numOfEdges = 10;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(1,2,6);	// 1-st edge
		graph[1] = new Edge(2,3,19);	// 2-st edge
		graph[2] = new Edge(3,4,9);	// 3-st edge
		graph[3] = new Edge(4,5,14);	// 4-st edge
		graph[4] = new Edge(5,6,21);// 5-st edge
		graph[5] = new Edge(5,7,2);// 6-st edge
		graph[6] = new Edge(7,8,8);// 7-st edge
		graph[7] = new Edge(8,1,17);// 8-st edge
		graph[8] = new Edge(7,1,11);// 9-st edge
		graph[9] = new Edge(7,2,25);// 10-st edge
		return graph;
	}

	
	public static Edge[] init4(){
		int numOfEdges = 4;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(1,2,3);	// 1-st edge
		graph[1] = new Edge(2,3,12);	// 2-st edge
		graph[2] = new Edge(2,4,5);	// 3-st edge
		graph[3] = new Edge(3,4,1);	// 4-st edge
		return graph;
	}
	public static Edge[] init5(){
		int numOfEdges = 4;
		Edge[] graph  = new Edge[numOfEdges];	
		graph[0] = new Edge(1,2,12);	// 1-st edge
		graph[1] = new Edge(2,3,3);	// 2-st edge
		graph[2] = new Edge(2,4,5);	// 3-st edge
		graph[3] = new Edge(3,4,1);	// 4-st edge
		return graph;
	}
	
}
