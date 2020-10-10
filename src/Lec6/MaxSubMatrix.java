package Lec6;

import java.util.Arrays;

public class MaxSubMatrix {
	
	public static void main(String[] args) {
		int a[][]= {{2,1,-3,-4,5},
				    {0,6,3,4,1},
				    {2,-2,-1,4,-5},
				    {-3,3,1,0,3}};

		CompleteSearch(a);
		SubRows(a);
		DynamicProgramming(a);
		SuperBest(a);
	}
	
	
	public static int[] Best(int arr[]) {
		if(arr.length<1) return null;
	
		int max=arr[0];int maxTemp=arr[0];
		int start=0;int startTemp=0;
		int end=0;int endTemp=0;
		
		int sum[]=new int[arr.length];
		sum[0]=arr[0];
		
		for (int i = 1; i < sum.length; i++) {
			if(sum[i-1]+arr[i]>=0&&sum[i-1]>=0) {
				sum[i]=sum[i-1]+arr[i];
				if(sum[i]>max) {
					max=sum[i];
				}	
			}
			else if(sum[i-1]+arr[i]<0){
				sum[i]=-1;
			}
			else {
				sum[i]=arr[i];
				if(sum[i]>max) {
					max=sum[i];
				}
			}

		}
		startTemp=0;endTemp=0;
		int length=sum.length;
		for (int i = 0; i < sum.length; i++) {
			if(sum[i]==max) {
				endTemp=i;
				if(endTemp-startTemp<length) {
					start=startTemp;
					end=endTemp;
				}
			}
			if(sum[i]<=0) {
				startTemp=i+1;
			}
		}
		int ans[]=new int[3];
		ans[0]=max;
		ans[1]=start;
		ans[2]=end;
		startTemp=0;endTemp=0;
		length=0;
		for (int i = 0; i < sum.length; i++) {
			if(sum[i]==max) {
				endTemp=i;
				if(endTemp-startTemp>=length) {
					start=startTemp;
					end=endTemp;
				}
				
			}
			if(sum[i]<0) {
				startTemp=i+1;
			}
		}
		return ans;
	}

	public static void CompleteSearch(int mat[][]) {//O(n^6)
		int sum=0;
		int max=Integer.MIN_VALUE;
		int start_row=-1;
		int start_col=-1;
		int end_row=-1;
		int end_col=-1;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				for (int k = i; k < mat.length; k++) {
					for (int l = j; l < mat[0].length; l++) {
						for (int x = i; x <= k; x++) {
							for (int y = j; y <= l; y++) {
								sum+=mat[x][y];
							}
						}
						if(sum>max) {
							max=sum;
							start_row=i;
							start_col=j;
							end_row=k;
							end_col=l;
						}
						sum=0;
					}
				}
			}
		}
		System.out.println("the max sub array is: ["+start_row+","+start_col+"] , ["+end_row+","+end_col+"]");
		System.out.println("the sum is "+max);
	}
	
	public static void SubRows(int mat[][]) {//O(n^4)

		
		int max=Integer.MIN_VALUE;
		int start_row=-1;
		int start_col=-1;
		int end_row=-1;
		int end_col=-1;
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = i; j < mat.length; j++) {
				int arr[]=new int[mat[0].length];
				for (int k = i; k <=j; k++) {
					for (int l = 0; l < mat[0].length; l++) {
						arr[l]+=mat[k][l];
					}
				}
				int temp[]=Best(arr);
				if(temp[0]>max) {
					max=temp[0];
					start_row=i;
					end_row=j;
					start_col=temp[1];
					end_col=temp[2];
				}
			}
		}
		System.out.println("the max sub array is: ["+start_row+","+start_col+"] , ["+end_row+","+end_col+"]");
		System.out.println("the sum is "+max);
	}
	
	public static void DynamicProgramming(int mat[][]) {//O(n^4)
		int max=Integer.MIN_VALUE;
		int sum=0;
		int start_row=-1;
		int start_col=-1;
		int end_row=-1;
		int end_col=-1;
		
		int H[][]=BuildH(mat);
		for (int i = 0; i < H.length; i++) {
			if(H[i][0]>max) {
				max=H[i][0];
				start_row=i;
				start_col=0;
				end_row=i;
				end_col=0;
			}
		}
		
		for (int i = 0; i < H[0].length; i++) {
			if(H[0][i]>max) {
				max=H[0][i];
				start_row=0;
				start_col=i;
				end_row=0;
				end_col=i;
			}
		}
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				for (int k = i; k < mat.length; k++) {
					for (int l = j; l < mat[0].length; l++) {
						sum=H[k][l]-H[k][j-1]-H[i-1][l]+H[i-1][j-1];
						if(sum>max) {
							max=sum;
							start_row=i;
							start_col=j;
							end_row=k;
							end_col=l;
						}
					}
				}
			}
		}
		
		System.out.println("the max sub array is: ["+start_row+","+start_col+"] , ["+end_row+","+end_col+"]");
		System.out.println("the sum is "+max);
	}


	private static int[][] BuildH(int[][] mat) {
		int arr[][]=new int[mat.length][mat[0].length];
		arr[0][0]=mat[0][0];
		for (int i = 1; i < arr[0].length; i++) {
			arr[0][i]+=arr[0][i-1]+mat[0][i];
		}
		for (int i = 1; i < arr.length; i++) {
			arr[i][0]+=arr[i-1][0]+mat[i][0];
		}
		
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				arr[i][j]=mat[i][j]+arr[i-1][j]+arr[i][j-1]-arr[i-1][j-1];
			}
		}
		print(arr);
		return arr;
		
	}
	
	public static void SuperBest(int[][] mat) {//O(n^3)
		int max=Integer.MIN_VALUE;
		int start_row=-1;
		int start_col=-1;
		int end_row=-1;
		int end_col=-1;

		for (int i = 0; i < mat[0].length; i++) {
			int arr[]=new int[mat.length];
			for (int j = i; j < mat[0].length; j++) {
				for (int k = 0; k < arr.length; k++) {
					arr[k]+=mat[k][j];
				}
//				int temp[]=Best(arr);
				Best best=new Best(arr);
				if(best.max>max) {
					max=best.max;
					start_row=best.startIndex;
					end_row=best.endIndex;
					start_col=i;
					end_col=j;
				}
			}
		}
		System.out.println("super best");
		System.out.println("the max sub array is: ["+start_row+","+start_col+"] , ["+end_row+","+end_col+"]");
		System.out.println("the sum is "+max);
		
	}
	
	private static void print(int arr[][]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
