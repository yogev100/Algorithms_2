package Lec4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	static int inf=1000000;

	public static void main(String[] args) {
		int [][]mat= {{0,2,4,inf,inf},
				{2,0,inf,6,inf},
				{4,inf,0,1,2},
				{inf,6,1,0,3},
				{inf,inf,2,3,0}};

		String path="";
		System.out.println(DijkAlgo(0, 3, mat));

		int[][]mat2= {{0,2,1},
				{inf,0,3},
				{inf,inf,0}};

		//		System.out.println(DijkstraAlgorithm(2, 1, mat2, path));
		
		int[][] mat3 = {{0 ,1 ,2 ,inf ,inf ,inf ,inf ,inf },
                {1 ,0 ,4 ,inf ,5 ,inf ,inf ,inf },
                {2 ,4 ,0 ,7 ,inf ,3 ,inf ,inf },
                {inf ,inf ,7 ,0 ,3 ,8 ,inf ,inf },
                {inf ,5 ,inf ,3 ,0 ,inf ,1 ,3 },
                {inf ,inf ,3 ,8 ,inf ,0 ,2 ,inf },
                {inf ,inf ,inf ,inf ,1 ,2 ,0 ,5 },
                {inf ,inf ,inf ,inf ,4 ,inf ,5 ,0 }};

        System.out.println(DijkAlgo(5,7,mat3));
	}


	//	public static int DijkstraAlgorithm(int s,int t,int[][]mat,String path) {	
	//		if((t>=mat.length||t<0)||(s>=mat.length||s<0)) return -1;
	//		int []D=new int[mat.length];//array of weights for each vertex
	//		int []F=new int[mat.length];//array of father for each vertex
	//		boolean visited[]=new boolean[mat.length];
	//		for (int i = 0; i < F.length; i++) {
	//			D[i]=inf;
	//			F[i]=-1;
	//		}
	//		D[s]=0;
	//		PriorityQueue<Integer> q=new PriorityQueue<>();
	//		for (int i = 0; i < F.length; i++) {
	//			q.add(D[i]);
	//		}
	//		
	//		ArrayList<ArrayList<Integer>> N =buildGraph(mat);
	//		while(!q.isEmpty()&&!visited[t]) {
	//			int WeightU=q.poll();
	//			int u=getIndexU(WeightU,D,visited);
	//			
	//			for (int i = 0; i < N.get(u).size(); i++) {
	//				int w=N.get(u).get(i);
	//				if(D[w]>D[u]+mat[u][w]) {
	//					D[w]=D[u]+mat[u][w];
	//					F[w]=u;
	//				}
	//			}
	//			visited[u]=true;
	//			q=updateQueue(D,visited);
	//		}
	//		if(F[t]==-1) {
	//			System.out.println("no path");
	//			return -1;
	//		}
	//		int x=t;
	//		while(x!=s) {
	//			path=F[x]+"->"+path;
	//			x=F[x];
	//		}
	//		path+=t+"";
	//		System.out.println(path);
	//		return D[t];
	//	}
	//	

	//	
	//	public static int getIndexU(int weightU, int[] D,boolean visited[]) {
	//		for (int i = 0; i < D.length; i++) {
	//			if(D[i]==weightU&&!visited[i])return i;
	//		}
	//		return -1;
	//	}
	//	
	//	public static PriorityQueue<Integer> updateQueue(int D[],boolean visited[]){
	//		PriorityQueue<Integer> q=new PriorityQueue<>();
	//		for (int i = 0; i < D.length; i++) {
	//			if(!visited[i]) {
	//				q.add(D[i]);
	//			}
	//		}
	//		return q;
	//	}
	//
	//	public static ArrayList<ArrayList<Integer>> buildGraph(int mat[][]){
	//		ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
	//		for (int i = 0; i < mat.length; i++) {
	//			graph.add(new ArrayList<Integer>());
	//		}
	//		for (int i = 0; i < mat.length; i++) {
	//			for (int j = 0; j < mat.length; j++) {
	//				if(mat[i][j]!=0&&mat[i][j]!=inf) {
	//					graph.get(i).add(j);
	//				}
	//			}
	//		}
	//		return graph;
	//	}
	public static int DijkAlgo (int source,int dest, int weight[][]){
		int n = weight.length;
		int dist[] = new int[n];
		int prev[] = new int[n];
		boolean visited[] = new boolean[n];
		for (int i = 0; i <n ; i++) {
			dist[i]=inf;
			prev[i]=-1;
			visited[i]=false;
		}
		dist[source] =0;
		int v=-1;
		while( v!= dest){
			v=ExtractMin(dist,visited);
			for (int i = 0; i < n ; i++) {
				if(weight[v][i]!=inf && !visited[i] && dist[i]>dist[v]+weight[v][i]){
					dist[i] = dist[v] + weight[v][i];
					prev[i]=v;
				}
			}
			visited[v]=true;
		}

		//print path
		print_path(source,dest,prev);

		return dist[dest];
	}

	private static void print_path(int source, int dest,int []prev) {
		String ans=""+dest;
		int i=dest;
		while(prev[i]!=source) {
			ans=prev[i]+"->"+ans;
			i=prev[i];
		}
		ans=source+"->"+ans;
		System.out.println(ans);

	}


	public static int ExtractMin(int[] dist, boolean[] visited){
		int minIndex = inf;
		int minVal = inf;
		int V = visited.length;
		for (int i = 0; i <V; i++) {
			if(!visited[i]&& dist[i]<minVal){
				minVal= dist[i];
				minIndex=i;
			}

		}

		return minIndex;
	}
}
