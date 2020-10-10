package Lec3;

import java.util.Arrays;

public class weightedGraphs {
	static int inf=1000000;

	public static void main(String[] args) {
		//----Q1----//
		int [][]mat= {{0,1,inf,5},
				{1,0,9,inf},
				{inf,9,0,2},
				{5,inf,2,0}};

		//		printMatrix(mat);
		//		ShortestPathsMatrix(mat);
		//		printMatrix(mat);

		//----Q2----//
//		String paths[][]=init_paths(mat);
//		ShortestPathsMatrix(mat, paths);
//		fix_paths(paths);
//		printPaths(mat,paths);
		
		//Q3 floyd warshall on weights on the vertices 
		System.out.println("me");
		int[][] mat3 = {{0,1,1,inf},
						{1,0,inf,1},
						{1,inf,0,1},
						{inf,1,1,0}};
		int[] arr = {1,8,5,10};
		int[][] H = ConvertWeightedVertexes(mat3, arr);
		ShortestPathsMatrix(H);
		fixMatrix(H, arr);
		printMatrix(H);	
		
		//Q3 floyd warshall on weights on the vertices 
		int[][] mat4 = {{0,1,1,inf},
				{1,0,inf,1},
				{1,inf,0,1},
				{inf,1,1,0}};
		int[] arr4 = {1,8,5,10};
		int[][] H4 = InitH(mat4, arr4);
		ShortestPathsMatrix(H4);
		fixH(H4, arr4);
		System.out.println("or");
		printMatrix(H4);

	}

	public static void printPaths(int mat[][],String paths[][]) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				if(mat[i][j]!=inf) {
					System.out.println(i+"->"+j+": "+paths[i][j]);
				}
				else {
					System.out.println(i+"->"+j+": no path");
				}
			}
		}
	}

	public static void printMatrix(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}

	//--------------Q1----------------//
	public static void ShortestPathsMatrix(int arr[][]){//floyd warshall
		for (int k = 0; k < arr.length; k++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(arr[i][k]!=inf&&arr[k][j]!=inf) {
						arr[i][j]=Math.min(arr[i][j], arr[i][k]+arr[k][j]);
					}
				}
			}
		}
	}
	//------------Q2--------------//
	public static void ShortestPathsMatrix(int arr[][],String paths[][]){//floyd warshall for find all paths
		for (int k = 0; k < paths.length; k++) {
			for (int i = 0; i < paths.length; i++) {
				for (int j = 0; j < paths.length; j++) {
					if(arr[i][k]!=inf&&arr[k][j]!=inf) {
						if(arr[i][j]>arr[i][k]+arr[k][j]) {
							arr[i][j]=arr[i][k]+arr[k][j];
							paths[i][j]=paths[i][k]+paths[k][j];
						}
					}
				}
			}
		}
	}

	public static String[][] init_paths(int arr[][]){
		String paths[][]=new String[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(arr[i][j]!=inf) {
					paths[i][j]=i+"->";
				}
				else {
					paths[i][j]="";
				}
			}
		}
		return paths;
	}

	public static void fix_paths(String paths[][]) {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				paths[i][j]+=""+j;
			}
		}
	}

	//------------------Q3-------------------//

	public static int[][] ConvertWeightedVertexes(int arr[][],int nodes[]) {//floyd warshall
		int ans[][]=new int[arr.length][arr.length];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				if(arr[i][j]==1) {
					ans[i][j]=nodes[i]+nodes[j];
				}
				else {
					ans[i][j]=inf;
				}
			}
		}
		for (int i = 0; i < ans.length; i++) {
			ans[i][i]=nodes[i];
		}
		return ans;
	}

	public static void fixMatrix(int arr[][],int nodes[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(i!=j) {
					arr[i][j]=(arr[i][j]+nodes[i]+nodes[j])/2;
				}
			}
		}
	}
	
	private static int[][] InitH(int[][] mat, int[] arr) {
		int size = mat.length;
		int[][] H = new int[size][size];

		for (int i = 0; i < H.length; i++) {
			for (int j = 0; j < H[i].length; j++) {
				if (mat[i][j] == 1) {
					H[i][j] = arr[i] + arr[j];
				}
				else
				{
					H[i][j] = inf;
				}
			}
		}
		for (int i = 0; i < H.length; i++) {
			H[i][i] = arr[i];
		}
		return H;
	}
	private static void fixH(int[][] H, int[] arr) {
		for (int i = 0; i < H.length; i++) {
			for (int j = 0; j < H[i].length; j++) {
				if (i != j)
					H[i][j] = (H[i][j] + arr[i] + arr[j] ) / 2;
			}
		}
	}
}
