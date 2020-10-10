package Lec5;

import java.util.Arrays;

public class FuelProblem {
	public static int[] maxSum(int arr[]) {
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
		System.out.println(Arrays.toString(ans));
		return ans;
	}
	
	
	public static int[] maxSumCycle(int arr[]) {
		int normArr[]=maxSum(arr);//BEST(A)
		int norm=normArr[0];
		int totalSum=0;
		for (int i = 0; i < arr.length; i++) {//totalSum
			totalSum+=arr[i];
		}
		for (int i = 0; i < arr.length; i++) {//-A
			arr[i]=-1*arr[i];
		}
		int bestMinusArr[]=maxSum(arr);
		int bestMinus=bestMinusArr[0];//BEST(-A)
		int ans[]=new int[3];
		if(norm>bestMinus) {
			ans[0]=norm;
			ans[1]=normArr[1];
			ans[2]=normArr[2];
			System.out.println(Arrays.toString(ans));
			return ans;
		}
		else {
			ans[0]=bestMinus;
			ans[1]=bestMinusArr[1];
			ans[2]=bestMinusArr[2];
			System.out.println(Arrays.toString(ans));
			return ans;
		}
		
		
	}
	
	public static int[] complementSubArray(int i,int j,int length) {
		int ans[]=new int[2];
		if(i==0) {
			ans[0]=(j+1)%length;
			ans[1]=length;
			return ans;
		}
		ans[0]=(j+1)%length;
		ans[1]=(i-1)%length;
		return ans;
	}
	
	public static int fuelProb(int a[],int b[]) {
		int aSum=0;
		int bSum=0;
		for (int i = 0; i < b.length; i++) {
			aSum+=a[i];
			bSum+=b[i];
		}
		if(bSum>aSum) {
			System.out.println("cant drive all cycle");
			return -1;
		}
		int c[]=new int[a.length];
		for (int i = 0; i < c.length; i++) {
			c[i]=a[i]-b[i];
		}
		System.out.println(Arrays.toString(c));
		
		int arr[]=maxSumCycle(c);
//		System.out.println(Arrays.toString(arr));
//		int ans[]=complementSubArray(arr[1], arr[2], c.length);
//		System.out.println(Arrays.toString(ans));
		return arr[1];
		
	}
	
	public static void main(String[] args) {
		int a[]= {1,5,2,7,3,8};
		int b[]= {1,6,8,3,7,1};
		System.out.println(fuelProb(a, b));
	}
}
