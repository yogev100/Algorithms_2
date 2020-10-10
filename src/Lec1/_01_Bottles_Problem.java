package Lec1;

import java.util.Arrays;

public class _01_Bottles_Problem {
	public static boolean[][] BottlesProblem(int m,int n){
		int size=(m+1)*(n+1);//m:rows, n:columns
		boolean arr[][]=new boolean[size][size];
		for (int i = 0; i <=m; i++) {
			for (int j = 0; j <=n; j++) {
				int index=getIndex(i,j,n);
				arr[index][getIndex(0,j,n)]=true;//(i,j)->(0,j):empty left bottle
				arr[index][getIndex(m,j,n)]=true;//(i,j)->(m,j):fill left bottle
				arr[index][getIndex(i,0,n)]=true;//(i,j)->(i,0):empty right bottle
				arr[index][getIndex(i,n,n)]=true;//(i,j)->(i,n):fill right bottle
				int indexTo=getIndex(i+j-Math.min(i+j, n),Math.min(i+j, n),n);
				//(i,j)->max(i+j-min(i+j,m),min(i+j,m)):pass water from m:left bottle to n:right bottle
				arr[index][indexTo]=true;
				indexTo=getIndex(Math.min(i+j, m),i+j-Math.min(i+j, m),n);
				//(i,j)->max(i+j-min(i+j,n),min(i+j,n)):pass water from n:right bottle to m:left bottle
				arr[index][indexTo]=true;	
			}
		}


		return arr;

	}

	public static String[][] init_paths(boolean m[][],int n) {
		String paths[][]=new String[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if(m[i][j]) {
					paths[i][j]="("+getI(i,n)+","+getJ(i,n)+")";
				}
			}
		}
		return paths;
	}

	public static void fix_paths(boolean m[][],String paths[][],int n) {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				if(m[i][j]) {
					paths[i][j]+="->("+getI(j,n)+","+getJ(j,n)+")";
				}
			}
		}
	}

	public static void PrintAllPaths(String paths[][],int n) {
		for (int i = 0; i < paths.length; i++) {
			for (int j = 0; j < paths.length; j++) {
				System.out.print("("+getI(i,n)+","+getJ(i,n)+")"+" to "+"("+getI(j,n)+","+getJ(j,n)+"):" );
				System.out.println(paths[i][j]);
			}
		}
	}

	public static boolean[][] Floyd_Warshall(boolean[][] m){
		for (int k = 0; k < m.length; k++) {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.length; j++) {
					m[i][j]=((m[i][k]&&m[k][j])||m[i][j]);
				}
			}
		}
		return m;
	}

	public static boolean[][] Floyd_Warshall(boolean[][] m,String paths[][]){
		for (int k = 0; k < m.length; k++) {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.length; j++) {
					if(m[i][j]==false&&(m[i][k]&&m[k][j])) {
						paths[i][j]=paths[i][k]+"->"+paths[k][j];
					}
					m[i][j]=((m[i][k]&&m[k][j])||m[i][j]);
					if(m[i][j]==false) {
						paths[i][j]="No path";
					}
				}
			}
		}
		return m;
	}

	public static void IsExist(int i1,int j1,int i2,int j2,boolean m[][],String paths[][],int n) {
		int i=getIndex(i1, j1, n);
		int j=getIndex(i2, j2, n);
		if(m[i][j]) {
			System.out.println("YES: "+paths[i][j]);
		}
		else {
			System.out.println("NO");
		}
	}

	public static int getI(int k,int col) {
		return k/(col+1);
	}
	public static int getJ(int k,int col) {
		return k%(col+1);
	}
	public static int getIndex(int i,int j,int n) {
		return (n+1)*i+j;
	}

	public static int[][] bool_to_int(boolean [][]mat){
		int ans[][]=new int[mat.length][mat[0].length];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans.length; j++) {
				if(mat[i][j]==true) {
					ans[i][j]=1;
				}
				else {
					ans[i][j]=0;
				}
			}
		}
		return ans;
	}

	public static int[][] shortestPathFW(boolean mat[][]) {
		int[][] m=bool_to_int(mat);
		int n = mat.length;
		int max = Integer.MAX_VALUE;
		int ans[][] = new int[n][n];
		for (int i = 0; i < n; i++) {         //initialize the math with infinity.
			for (int j = 0; j < n; j++) {
				ans[i][j] = m[i][j];
			}
		}
		for (int i = 0; i < n; i++) {    // update the weight path from the given matrix.
			for (int j = 0; j < n; j++) {
				if (ans[i][j] == 0 && i != j) ans[i][j] = max;
				if (i == j) ans[i][j] = 0;
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (ans[i][k] != max && ans[k][j] != max)
						ans[i][j] = Math.min(ans[i][k] + ans[k][j], ans[i][j]);  //update the minimum path.
				}
			}
		}
		return ans;
	}

	public static void print(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}



	public static void main(String[] args) {
		boolean m[][]=BottlesProblem(3, 5);
		String paths[][]=init_paths(m, 5);
		shortestPathFW(m);
		print(shortestPathFW(m));
			Floyd_Warshall(m, paths);
			fix_paths(m, paths, 5);
			//PrintAllPaths(paths, 5);
			IsExist(0,0,0,4,m,paths,5);

	}

}
