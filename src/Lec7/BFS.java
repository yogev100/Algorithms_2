package Lec7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	static final int inf=1000000;
	public static final int NIL = -1;
	int size;
	int[][] mat;
	int[] color;
	int[] distance;
	int[] father;
	int V; //numberOfVertecies
	int num_comp;
	ArrayList<ArrayList<Integer>> components;

	public BFS(int[][] matrix)
	{
		mat = matrix;
		size=matrix.length;
		V = mat.length;
		color = new int[V];
		distance = new int[V];
		father = new int[V];
		if(size>0) {
			num_comp=1;
		}
		components=new ArrayList<>();
	}

	private int[] Run_BFS(int s) {
		Queue<Integer> q = new LinkedList<>();

		Initialize();

		q.add(s);
		color[s] = 1;
		distance[s] = 0;
		father[s] = -1;

		int v;
		while (q.isEmpty() == false)
		{
			v = q.poll();
			for (int u = 0; u < V; u++)
			{
				if (color[u] == 0 && mat[v][u] == 1)
				{
					color[u] = 1;
					distance[u] = distance[v]+1;
					father[u] = v;
					q.add(u);
				}
			}
			color[v] = 2;
		}
		return color;
	}
	
	public int[] findComponents(){
        int n = mat.length;
        int counter = 1;
        int colors[];
        int arr[] = new int[n];
        for (int i = 0; i <n ; i++) {
            if(arr[i]==0){
                colors = Run_BFS(i);
                for (int j = 0; j <colors.length; j++) {
                    if(colors[j]==2){//black
                        arr[j]=counter;
                    }
                }
                counter++;
            }
        }
        num_comp=counter-1;
//        arr[n]= counter-1;
        return arr;
    }
	
	public int get_num_comp() {
		findComponents();
		return num_comp;
	}
	
	public void print_components() {
		int []c=findComponents();
		int num=num_comp;
		for (int i = 0; i < num; i++) {
			components.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < c.length; i++) {
			
			components.get(c[i]-1).add(i);
		}
		for (int i = 0; i < num; i++) {
			System.out.println("component number "+(i+1)+":"+components.get(i));
		}
	}


	private void Initialize() {
		for (int v = 0; v < V; v++) {
			color[v] = 0;
			distance[v] = NIL;
			father[v] = NIL;
		}	
	}

	public boolean IsConnected()
	{
		for (int v = 0; v < V; v++)
		{
			if (color[v] == 0)
				return false;
		}
		return true;
	}

	public boolean IsTherePathBetween_v_and_u(int v, int u)
	{
		Run_BFS(v);
		if (color[u] == 2)
			return true;
		return false;
	}

	//after checking IsTherePathBetween_v_and_u
	public ArrayList<Integer> GetPathBetween_v_and_u(int v, int u)
	{
		ArrayList<Integer> path = new ArrayList<Integer>();

		int vertex = u;
		while(father[vertex] != -1)
		{
			path.add(vertex);
			vertex = father[vertex];
		}
		path.add(vertex);

		//reverse the list
		int temp;
		for (int i = 0; i < path.size()/2; i++) {
			temp = path.get(i);
			path.set(i, path.get(path.size()-1-i));
			path.set(path.size()-1-i, temp);
		}

		return path;
	}
	
    /**
     * O(V+E)*2 = O(V+E).
     * algorithem to find the diameter of a graph(longest path between to vertices)
     * diameter= max length of edges between two vertices.
     * @param mat matrix that represent a graph.
     * @return the value of the diameter.
     */
    public int findDiameter(){
        int diameter=0;
        Run_BFS(0);
        Run_BFS(findMaxIndex(distance));
        diameter=distance[findMaxIndex(distance)];
        return diameter;
    }
    
    private int findMaxIndex(int[] dist) {
    	int max=-1;
    	int index=-1;
    	System.out.println(Arrays.toString(dist));
    	for (int i = 0; i < dist.length; i++) {
			if(dist[i]>max) {
				max=dist[i];
				index=i;
			}
		}
    	return index;
    }

	public static void main(String[] args) {
		int[][] graph = {{0,0,0,1,0,0},
				{0,0,0,1,1,1},
				{0,0,0,1,1,1},
				{1,1,1,0,0,0},
				{0,1,1,0,0,0},
				{0,1,1,0,0,0}};

		BFS bfs = new BFS(graph);
		System.out.println(bfs.IsTherePathBetween_v_and_u(5,0));
		System.out.println(bfs.GetPathBetween_v_and_u(5, 0));
		int comp1[]=bfs.findComponents();
		System.out.println(Arrays.toString(comp1));
		System.out.println(bfs.get_num_comp());
		bfs.print_components();
		System.out.println("diameter:"+bfs.findDiameter());
		
		int[][] g= {{0,1,0,1,0},
				    {1,0,0,1,0},
				    {0,0,0,0,1},
				    {1,1,0,0,0},
				    {0,0,1,0,0}};
		
		BFS b=new BFS(g);
		int comp[]=b.findComponents();
		System.out.println(Arrays.toString(comp));
		System.out.println(b.get_num_comp());
		b.print_components();
		}
	}


