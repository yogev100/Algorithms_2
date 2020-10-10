package Lec2;

import java.util.Arrays;

public class graphs {
	
//	public static void main(String[] args) {
//		System.out.println("        V4");
//		System.out.println("        /\\");
//		System.out.println("       /  \\");
//		System.out.println("      /____\\");
//		System.out.println("     V0     V5");
//		System.out.println("    V6------V3");
//		System.out.println("      |     |");
//		System.out.println("      |_____|");
//		System.out.println("     V1     V2");
//		System.out.println();
//		boolean[][] T = Init01();				
//		floyd_warshall(T);
//		
//		System.out.println("Floyd Warshall Matrix:");
//		printBoolMatrix(T);
//
//		System.out.println("Is the graph connected? : " + isConnected(T));
//
//		System.out.println("\nThere is : " + NumberOfComponents(T) + " components.");
//
//		String[] components = GetVertexInEachComponents(T);
//		for (int i = 0; i < components.length; i++) {
//			System.out.println("Component "+i+" is : " + components[i]);
//
//	}

	public static Node[][] PathsMatrix(boolean[][] m){
		Node [][]arr=new Node[m.length][m[0].length];
		for (int i = 0; i < arr.length; i++) {//initial node matrix
			for (int j = 0; j < arr.length; j++) {
				if(m[i][j]) {
					arr[i][j]=new Node(1);
					if(i!=j) {
						arr[i][j].path=i+"->";
					}
					else {
						arr[i][j].path="me";
					}
				}
				else {
					arr[i][j]=new Node(1000000);//no path yet
				}
			}
		}
		for (int k = 0; k < arr.length; k++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(!m[i][j]) {
						if(m[i][k]&&m[k][j]) {
							arr[i][j].val=1;
							arr[i][j].path=arr[i][k].path+arr[k][j].path.substring(1);
							//System.out.println(arr[i][j].path);
						}
					}
					else {
						String c=arr[i][j].path.charAt(arr[i][j].path.length()-1)+"";
						String parse=""+j;
						if(!c.equals(parse)&&!c.equals("e")) {
							arr[i][j].path+=j;
						}
					}
					m[i][j]=((m[i][k]&&m[k][j])||m[i][j]);

				}
			}
		}
		return arr;
	}
	
	public static void Floyd_Warshall(boolean [][]m) {
		for (int k = 0; k < m.length; k++) {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m.length; j++) {
					m[i][j]=m[i][j]||(m[i][k]&&m[k][j]);
				}
			}
		}
	}
	
	public static boolean isConnected(boolean [][]m) {
		for (int i = 0; i < m.length; i++) {
			if(m[0][i]==false) {
				return false;
			}
		}
		return true;
	}
	
	public static int NumberOfComponents(boolean [][]m) {
		int arr[]=new int[m.length];
		int count=0;
		for (int i = 0; i < m.length; i++) {
			if(arr[i]==0) {
				count++;
				arr[i]=count;
				for (int j = i+1; j < m.length; j++) {
					if(m[i][j]==true) {
						arr[j]=count;
					}
				}
			}
		}
		return count;
	}

	public static String[] GetVertexInEachComponents(boolean m[][]) {
		int arr[]=new int[m.length];
		int count=0;
		for (int i = 0; i < m.length; i++) {
			if(arr[i]==0) {
				count++;
				arr[i]=count;
				for (int j = i+1; j < m.length; j++) {
					if(m[i][j]==true) {
						arr[j]=count;
					}
				}
			}
		}
		String[] ans=new String[count];
		for (int i = 0; i < ans.length; i++) {
			ans[i]="";
		}
		for (int i = 0; i < arr.length; i++) {
			ans[arr[i]-1]+=i+"\t";/*arr[i] is bigger than 0 so the vertexes that belong
									the same component will be at the same vector*/
		}

		return ans;
	}
	
	private static boolean minusOneExist(int t[]) {
		for (int i = 0; i < t.length; i++) {
			if(t[i]==-1)return true;
		}
		return false;
	}

	public static void printBoolMatrix(boolean [][]m) {
		for (int i = 0; i < m.length; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
	}
	public static void printNodeMatrix(Node [][]m) {
		for (int i = 0; i < m.length; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
	}

	public static void main(String[] args) {
		boolean b[][]=new boolean[7][7];
		b[0][3]=true;
		b[0][5]=true;
		b[1][4]=true;
		b[1][6]=true;
		b[2][4]=true;
		b[2][6]=true;
		b[3][0]=true;
		b[3][5]=true;
		b[4][1]=true;
		b[4][2]=true;
		b[5][0]=true;
		b[5][3]=true;
		b[6][1]=true;
		b[6][2]=true;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if(i==j) {
					b[i][j]=true;
				}
			}
		}
		
		Floyd_Warshall(b);
		System.out.println("the graph is connected? "+isConnected(b));
		System.out.println("number of component: "+NumberOfComponents(b));
		String components[]=GetVertexInEachComponents(b);
		for (int i = 0; i < components.length; i++) {
			System.out.println("component number "+i+": "+components[i]);
		}

	}
}
