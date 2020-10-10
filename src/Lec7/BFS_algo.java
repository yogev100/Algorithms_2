package Lec7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BFS_algo {
	
	static int inf=1000000;
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> G= build_graph2();
		ArrayList<ArrayList<Integer>> data=BFS(G,1);
		print_BFS(data);
		System.out.println(isConnected(data));
		num_component(G);
		print_path(data, 2, 6);
	}
	
	public static ArrayList<ArrayList<Integer>> build_graph1(){// 1 component

		ArrayList<ArrayList<Integer>> G= new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			G.add(new ArrayList<Integer>());
		}
		
		G.get(0).add(1);
		G.get(0).add(4);
		G.get(1).add(0);
		G.get(1).add(5);
		G.get(2).add(3);
		G.get(2).add(6);
		G.get(2).add(5);
		G.get(3).add(2);
		G.get(3).add(7);
		G.get(4).add(0);
		G.get(5).add(1);
		G.get(5).add(2);
		G.get(5).add(6);
		G.get(6).add(2);
		G.get(6).add(5);
		G.get(6).add(7);
		G.get(7).add(3);
		G.get(7).add(6);
		return G;
	}
	
	public static ArrayList<ArrayList<Integer>> build_graph2(){// 2 component
		ArrayList<ArrayList<Integer>> G= new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			G.add(new ArrayList<Integer>());
		}
		
		G.get(0).add(1);
		G.get(0).add(4);
		G.get(1).add(0);
		G.get(2).add(3);
		G.get(2).add(5);
		G.get(3).add(2);
		G.get(3).add(6);
		G.get(4).add(0);
		G.get(5).add(2);
		G.get(5).add(6);
		G.get(6).add(3);
		G.get(6).add(5);

		return G;
	}
	
	public static ArrayList<ArrayList<Integer>> build_graph3(){// 3 component
		ArrayList<ArrayList<Integer>> G= new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			G.add(new ArrayList<Integer>());
		}
		
		G.get(0).add(1);
		G.get(1).add(0);
		G.get(2).add(3);
		G.get(2).add(5);
		G.get(3).add(2);
		G.get(3).add(6);
		G.get(5).add(2);
		G.get(5).add(6);
		G.get(6).add(3);
		G.get(6).add(5);

		return G;
	}
	private static void print_BFS(ArrayList<ArrayList<Integer>> data) {
		System.out.println("colors:");
		System.out.println(data.get(0));
		System.out.println("distance:");
		System.out.println(data.get(1));
		System.out.println("father:");
		System.out.println(data.get(2));
	}
	
	public static ArrayList<ArrayList<Integer>> BFS(ArrayList<ArrayList<Integer>> G,int s){
		ArrayList<ArrayList<Integer>> data=new ArrayList<>();// data[0]-color,[1]-distance,[2]-father
		data.add(new ArrayList<Integer>());//data
		data.add(new ArrayList<Integer>());//distance
		data.add(new ArrayList<Integer>());//father
		
		for (int i = 0; i < G.size(); i++) {
			data.get(0).add(0);//0 - white , 1-gray , 2-black
			data.get(1).add(inf);// distance unknown yet
			data.get(2).add(null);//father unknown yet
			
		}
		
		data.get(0).set(s, 1);
		data.get(1).set(s, 0);
		data.get(2).set(s, null);
		
		ArrayBlockingQueue<Integer> Q = new ArrayBlockingQueue<Integer>(G.size()); 
		Q.add(s);
		
		while(!Q.isEmpty()) {
			Integer u=Q.poll();
			for (int i = 0; i < G.get(u).size(); i++) {//passing all u's neighbors
				Integer v=G.get(u).get(i);
				if(data.get(0).get(v)==0) {//if the color is white
					data.get(0).set(v, 1);//color=gray
					data.get(1).set(v, data.get(1).get(u)+1);//d=d(u)+1
					data.get(2).set(v, u);//father of v is u
					Q.add(v);
				}
			}
			data.get(0).set(u, 2);
		}
		
		return data;
			
	}
	
	public static boolean isConnected(ArrayList<ArrayList<Integer>> data) {
		for (int i = 0; i < data.get(0).size(); i++) {
			if(data.get(0).get(i)!=2) {
				return false;
			}
		}
		return true;
	}
	
	public static void num_component(ArrayList<ArrayList<Integer>> G) {
		int component[]=new int[G.size()];
		ArrayList<ArrayList<Integer>> data=BFS(G,0);
		
		int count=0;

		int s=white_vertex(data.get(0));
		while(s!=-1) {
			count++;
			ArrayList<ArrayList<Integer>> temp=BFS(G,s);
			put_component(data.get(0),temp.get(1), component, count);
			s=white_vertex(data.get(0));
		}
		
		
		System.out.println("number of component:"+(count+1));
		System.out.println("the component array is:");
		System.out.println(Arrays.toString(component));
		
	}
	

	
	private static int white_vertex(ArrayList<Integer> colors) {
		for (int i = 0; i < colors.size(); i++) {
			if(colors.get(i)==0) {
				return i;
			}
		}
		return -1;
	}
	
	private static void put_component(ArrayList<Integer> colors,ArrayList<Integer> temp,int comp[],int count) {

		for (int i = 0; i < temp.size(); i++) {
			Integer dis=temp.get(i);
			if(dis!=inf) {
				comp[i]=count;
				colors.set(i, 2);
			}
		}
	}
	
	public static void print_path(ArrayList<ArrayList<Integer>> data,int s,int v) {

		if(s==v) {
			System.out.print(""+s);
			return;
		}
		if(data.get(2).get(v)==null) {
			System.out.println("there is no path");
			return;
		}
		else {
			
			print_path(data,s,data.get(2).get(v));
			System.out.print("->"+v);
		}
		
	}
	
	private static HashMap<Integer,ArrayList<Integer>> sub_graph(ArrayList<ArrayList<Integer>> G,ArrayList<Integer> colors){
		HashMap<Integer,ArrayList<Integer>> new_graph = new HashMap<>();
		for (int i = 0; i < colors.size(); i++) {
			if(colors.get(i)==0) {
				new_graph.put(i, new ArrayList<>());
				for (int j = 0; j < G.get(i).size(); j++) {
					new_graph.get(i).add(G.get(i).get(j));
				}
			}
		}
		return new_graph;
	}
}
