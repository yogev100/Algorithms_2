package Lec7;

import java.util.LinkedList;
public class DFS {
	public static final int white = 0;
	public static final int gray = 1;
	public static final int black = 2;
	public static final int NIL = -1;
	int color[];
	int first[];
	int last[];
	int pred[];
	LinkedList<Integer>[] list;
	int size;
	int time;
	int num_comp;
	boolean hasCycle;
	int start_cycle,end_cycle;
	/**
	 * Constructor
	 * @param st the start vertex
	 * @param n the number of vertex
	 */
	public DFS(LinkedList<Integer> []l) {
		hasCycle=false;
		size = l.length;
		list =l;
		// white=0, gray=1, black=2
		color = new int[size]; //
		first = new int[size]; //
		last = new int[size]; // 
		pred = new int[size];
		for (int i=0; i<size; i++) pred[i] = NIL; // 
//		list = InPut_0to5();  
		time = 0;
		PrintGraph_0to5();  
		
		for (int i=0; i<size; i++) {
			if (color[i] == white) {
				System.out.println("White Cons");
				D_F_S(i);
			}
		}
		
		for (int i = 0; i < pred.length; i++) {
			if(pred[i]==NIL) {
				num_comp++;
			}
		}
		
	}
	/**
	 * Function finds all the tracks from st Vertex - Search in depth
	 * @param start the vertex that sent to the DFS Reco function
	 */
	public void D_F_S(int start) { // recursive function
		
		int v;
		time = time+1;
		first[start] = time;
		color[start] = gray;

		// run all over the neighbors of the current vertex
		for (int i=0; i<list[start].size(); i++){
			v = list[start].get(i); 
			if(!hasCycle && v==gray && v!=pred[start]) { // if v visited and start father is not v - so there is a circle
				hasCycle=true;
				start_cycle=start;
				end_cycle=v;
				
			}
			
			if (color[v] == white) {// enter to this recursive function when the neighbor is white
				pred[v] = start;
				D_F_S(v); //
			}
		}
		color[start] = black; // just when the current vertex no have white neighbors - assign it to black
		last[start] = ++time;
		printArrays(color,pred,first,last);
	}
	/**
	 * @return a graph as we learned in class
	 */
	
	public void print_cycle() {
		if(hasCycle) {
			System.out.println("the cycle is between "+start_cycle+" to "+end_cycle);
		}
		else {
			System.out.println("there is no cycle");
		}
	}
	
	public LinkedList<Integer>[] InPut_0to5() {
		for (int i=0; i<size; i++) list[i] = new LinkedList<Integer>();
		list[0].add(1); list[0].add(3); list[1].add(0); list[1].add(4);
		list[2].add(4); list[2].add(5); list[3].add(0); list[3].add(4);
		list[4].add(1); list[4].add(2); list[4].add(3); list[5].add(2);
		return list;
	}
	

	public int get_num_comp() {
		return num_comp;
	}
	
	/** Drawing the graph from the lesson */
	public void PrintGraph_0to5() {
		System.out.println("====== Graph 0 to 5 ======\n");
		System.out.println("(0)--------(1) (2)");
		System.out.println(" | | /| ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println(" | | / | ");
		System.out.println("(3)--------(4) (5)");
		System.out.println("\n==========================\n");
	}
	/**
	 * Helped static function - vertex data printing
	 * @param color the color of the vertex
	 * @param pred the "father" of the vertex
	 * @param first the Distance from the start in the first track
	 * @param last the Distance from the start in the second track
	 */
	public static void printArrays(int color[], int pred[], int first[], int last[]) {
		for (int i=0; i<color.length; i++) {
			String temp = "";
			if (color[i]==0) temp = "White";
			if (color[i]==1) temp = "Gray";
			if (color[i]==2) temp = "Black";
			System.out.println("Vertex "+i+"\tPred="+pred[i]+
					"\t\tFirst="+first[i]+"----Last="+last[i]+"\tThe color: "+temp);
		}
		System.out.println("()()()()()()()()()()()()()()()()()()()()()\n");
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> []list;
		list=new LinkedList[6];
		for (int i=0; i<list.length; i++) list[i] = new LinkedList<Integer>();
		list[0].add(1); list[0].add(3); list[1].add(0); list[1].add(4);
		list[2].add(4); list[2].add(5); list[3].add(0); list[3].add(4);
		list[4].add(1); list[4].add(2); list[4].add(3); list[5].add(2);
		
		LinkedList<Integer> [] list2=new LinkedList[5];
		for (int i=0; i<list2.length; i++) list2[i] = new LinkedList<Integer>();
		list2[0].add(1); list2[0].add(3); list2[1].add(0); list2[1].add(3);
		list2[2].add(4); list2[4].add(2); list2[3].add(0); list2[3].add(1);
		
		
		DFS dfs=new DFS(list2);
		System.out.println(dfs.get_num_comp());
		System.out.println(dfs.hasCycle);
		dfs.print_cycle();
	}
}