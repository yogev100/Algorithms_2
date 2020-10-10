package Lec4;

import java.util.Arrays;

public class negativeCircle {
	static int inf=1000000;

	public static void main(String[] args) {
		int [][]no_directed= {{0,1,4,inf,inf},//no directed graph
				{1,0,inf,6,inf},
				{4,inf,0,1,2},
				{inf,6,1,0,3},
				{inf,inf,2,3,0}};
		String paths_no_directed[][]=init_paths(no_directed);
		System.out.println("directed:"+NegativeCircleNoDirectedGraph(no_directed));


		int [][]directed= {{0,-5,inf},
				{inf,0,1},
				{-3,inf,0}};
		String paths_directed[][]=init_paths(directed);
		//System.out.println("no_directed:"+NegativeCircleDirectedGraph(directed));
		System.out.println(Example_to_negative_Circle(directed, paths_directed));

		System.out.println("    v2");
		System.out.println("    /\\");
		System.out.println("  1/  \\-5");
		System.out.println("  /    \\");
		System.out.println("v1------v3");
		System.out.println("    2");
		int[][] mat = {{0,1,inf},
				{inf,0,-5},
				{2,inf,0}};
		String[][] path = init_paths(mat);

		floyd_warshall_with_paths(mat, path);
		fix_paths(path);
		printMat(mat);
		System.out.println("example:"+Example_to_negative_Circle(mat, path));
		printPaths(path);

	}


	public static int[][] floyd_warshall(int mat[][]){
		for (int k = 0; k < mat.length; k++) {
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat.length; j++) {
					mat[i][j]=Math.min(mat[i][j], mat[i][k]+mat[k][j]);
				}
			}
		}
		return mat;
	}

	public static boolean NegativeCircleNoDirectedGraph(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j]<0) return true;
			}
		}
		return false;
	}

	public static boolean NegativeCircleDirectedGraph(int mat[][]) {
		int arr[][]=floyd_warshall(mat);
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][i]<0) return true;
		}
		return false;
	}

	public static String[][] init_paths(int mat[][]) {
		String path[][]=new String[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i][j]<inf&&i!=j) {
					path[i][j]=i+"->";
				}
				else {
					path[i][j]="";
				}
			}
		}
		return path;
	}

	public static void floyd_warshall_with_paths(int mat[][],String paths[][]) {
		for (int k = 0; k < paths.length; k++) {
			for (int i = 0; i < paths.length; i++) {
				for (int j = 0; j < paths.length; j++) {
					if (mat[i][k]!= inf &&  mat[k][j]!= inf) {
						if(mat[i][j]>mat[i][k]+mat[k][j]) {
							mat[i][j]=mat[i][k]+mat[k][j];
							paths[i][j]=paths[i][k]+paths[k][j];
						}
					}
				}
			}
		}
	}

	public static void fix_paths(String paths[][]) {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				if(!paths[i][j].equals("")) {
					paths[i][j]+=j+"";
				}
			}
		}
	}

	public static void printMat(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}
	public static void printPaths(String paths[][]) {
		for (int i = 0; i < paths.length; i++) {
			System.out.println(Arrays.toString(paths[i]));
		}
	}

	public static String Example_to_negative_Circle(int mat[][],String paths[][]) {
//		floyd_warshall_with_paths(mat, paths);
//		fix_paths(paths);
		for (int i = 0; i < mat.length; i++) {
			if(mat[i][i]<0) {
				return paths[i][i];
			}
		}
		return "no have negative circle";
	}
}
