package Lec9;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.*;


public class Euler {
	
	public static void main(String[] args) {
		int [][]mat=get_graph();
		int [][]mat2= {{0,0,1,1,1},
					   {0,0,1,1,1},
					   {1,1,0,0,0},
					   {1,1,0,0,0},
					   {1,1,0,0,0}};
		
		int[][] mat3= {{0,1,0,1,1},
					   {1,0,1,1,1},
					   {0,1,0,1,0},
					   {1,1,1,0,1},
					   {1,1,0,1,0}};

		
		int[][] mat4= {{0,1,0,0,0,0},
					   {1,0,1,0,0,1},
					   {0,1,0,1,0,0},
					   {0,0,1,0,1,0},
					   {0,0,0,1,0,1},
					   {0,1,0,0,1,0}};
		
		int[][] mat5= {{0,1,0,0,0,1},
				   {1,0,1,0,0,0},
				   {0,1,0,1,0,1},
				   {0,0,1,0,1,0},
				   {0,0,0,1,0,1},
				   {1,0,1,0,1,0}};
		
		
		
//		System.out.println(euler_cycle(mat2));
		euler_path(mat5);
		System.out.println(is_path_euler_graph(mat5));
	}
	
	public static int[][] get_graph(){
		int mat[][]= {{0,1,1,0,0,0,0,1,1},
					  {1,0,1,0,0,0,0,0,0},
					  {1,1,0,1,0,0,0,1,0},
					  {0,0,1,0,1,1,0,1,0},
					  {0,0,0,1,0,1,0,0,0},
					  {0,0,0,1,1,0,1,1,0},
					  {0,0,0,0,0,1,0,1,0},
					  {1,0,1,1,0,1,1,0,1},
					  {1,0,0,0,0,0,0,1,0}};

		
		return mat;
	}
		
	
	public static String euler_cycle(int [][]mat) {
		if(!is_cycle_euler_graph(mat)) {
			System.out.println();
			return "there is not an euler cycle in this graph";
		}
		
		Stack<Integer> path=new Stack();
		Stack<Integer> cycle=new Stack();
		path.push(0);
		while(!path.isEmpty()) {
			int u=path.peek();
			int deg=IntStream.of(mat[u]).sum();
			if(deg==0) {
				path.pop();
				cycle.push(u);
			}
			else {//get one of u's neighbors
				int v=get_neighbor(mat[u]);
				path.push(v);
				remove_edge(mat,u,v);
			}
		}
		
		 Object[] ans=cycle.toArray();
		 String str="";
		 for (int i = 0; i < ans.length; i++) {
			str+=ans[i]+",";
		}
		 System.out.println("the euler cycle is:"+str);
		 return str;
	}
	
	public static String euler_path(int mat[][]) {
		if(!is_path_euler_graph(mat)) {
			return "there is not a euler path in this graph";
		}
		
		int deg[]=get_deg(mat);
		
		int odd_vertex[]=get_odd_vertex(deg);
		
		int temp1=odd_vertex[0];
		int temp2=odd_vertex[1];
		
		if(mat[temp1][temp2]==0) {//there is no edge between the odd vertexes, add one edge
			mat[temp1][temp2]=1;
			mat[temp2][temp1]=1;
			String ans=euler_cycle(mat);
			int i1=ans.indexOf(temp1+","+temp2);
			int i2=ans.indexOf(temp2+","+temp1);
			if(i1!=-1) {
				ans=ans.substring(0,i1+2)+ans.substring(i1+4);
			}
			else {
				ans=ans.substring(0,i2+2)+ans.substring(i2+4);
			}
			System.out.println("the euler path is:"+ans);
			return ans;
		}
		
		else {//there is an edge between the odd vertexes - add one vetrex
			int [][]arr=new int[mat.length+1][mat.length+1];
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					arr[i][j]=mat[i][j];
				}
			}
			
			int new_ver=mat.length;
			arr[temp1][new_ver]=1;
			arr[temp2][new_ver]=1;
			arr[new_ver][temp1]=1;
			arr[new_ver][temp2]=1;
			
			String ans=euler_cycle(arr);
			int i1=ans.indexOf(new_ver+"");
//			int i2=ans.indexOf(new_ver+"");
			if(i1!=-1 && i1>1) {
				ans=ans.substring(0,i1-2)+ans.substring(i1+2);
			}
//			else {
//				ans=ans.substring(0,i2)+ans.substring(i2+2);
//			}
			System.out.println("the euler path is:"+ans);
			return ans;
		}
		
		
		
	}
	
	private static int[] get_odd_vertex(int []deg) {
		int ans[]=new int[2];
		ans[0]=-1;
		ans[1]=-1;
		for (int i = 0; i < deg.length; i++) {
			if(deg[i]%2!=0) {
				if(ans[0]!=-1) {
					ans[1]=i;
				}
				else {
					ans[0]=i;
				}
			}
		}
		return ans;
	}
	
	private static int get_neighbor(int []arr) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==1) {
				return i;
			}
		}
		return -1;
	}
	
	private static void remove_edge(int [][]mat,int u,int v) {
		mat[u][v]=0;
		mat[v][u]=0;
	}
	
	private static boolean is_cycle_euler_graph(int [][]mat) {
		int deg[]=get_deg(mat);
		
		for (int i = 0; i < deg.length; i++) {
			if(deg[i]%2!=0) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean is_path_euler_graph(int mat[][]) {
		int deg[]=get_deg(mat);
		int count=0;
		for (int i = 0; i < deg.length; i++) {
			if(deg[i]%2!=0) {
				count++;
			}
		}
		
		return count==2;
	}
	
	private static int[] get_deg(int mat[][]) {
		int deg[]=new int[mat.length];
		for (int i = 0; i < deg.length; i++) {
			deg[i]=IntStream.of(mat[i]).sum();
		}
		
		return deg;
	}
}
