package Lec5;

import java.util.Arrays;

public class max_subarray_sum {
	
	public static int maxSum(int arr[]) {
		if(arr.length<1) return -1;
	
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
		System.out.println(Arrays.toString(sum));
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
		
		System.out.println("the shortest sub array is: ("+start+","+end+")");
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
		System.out.println("the longest sub array is: ("+start+","+end+")");
		return max;
	}
	
	
	public static int maxSumCycle(int arr[]) {
		int bestA=maxSum(arr);//BEST(A)
		int totalSum=0;
		for (int i = 0; i < arr.length; i++) {//totalSum
			totalSum+=arr[i];
		}
		for (int i = 0; i < arr.length; i++) {//-A
			arr[i]=-1*arr[i];
		}
		int bestMinusA=maxSum(arr);//BEST(-A)
		return Math.max(bestA, totalSum+bestMinusA);
		
	}
	
	public static void main(String[] args) {
		int arr[]= {7,-9,2,1};
		System.out.println(maxSumCycle(arr));
	}
}
