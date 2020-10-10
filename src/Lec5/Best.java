package Lec5;

import java.util.stream.IntStream;

public class Best {

	int startIndex;
	int endIndex;
	int max;
	int arr[];
	int num_max_subarray;

	
	public Best() {
		num_max_subarray=1;
	}
	
	public Best(int a[]) {
		arr=a;
		num_max_subarray=1;
		Calculate_Best(arr);
	}
	
	/*
	 * function that calculate the max sum in the array,
	 * we assume that there is at least one positive element at the array
	 */
	public int Calculate_Best(int[] a)
	{
		int max = a[0], temp_max = 0,start = 0, end = 0, s = 0,sum=0; 
		int i=0;
		while (a[i]<=0) {// check if all the array are negative / when we arrived to the first positive element
			i++; 
			if(i == a.length) {// we arrived to the end of the array, so just one element is the max subarray
				this.max=max;
				startIndex=start;
				endIndex=end;
				System.out.println("the max sum is "+max+"\n"+"the subarray is ["+startIndex+","+endIndex+"]");
//				System.out.println("number max subarray:"+num_max_subarray);
				return max;
			}
			if (a[i] > max) {
				max = a[i]; 
				start = i;
				end = i;
			}
			else if(a[i]==max) {
				num_max_subarray++;
			}
		}
		max=0;
		s=i;
		for (i = i; i < a.length; i++)  
		{ 
			temp_max += a[i]; 

			if (max < temp_max) //with < sign :the minimal subarray, with <= sign : the maximal subarray 
			{ 
				max = temp_max; 
				start = s; 
				end = i; 
			} 
			else if(max==temp_max) {
				num_max_subarray++;
			}

			if (temp_max <= 0) 
			{ 
				temp_max = 0; 
				s = i + 1; 
			} 
		} 
		this.max =  max; 
		startIndex =  start; 
		endIndex = end;
		System.out.println("the max sum is "+max+"\n"+"the subarray is ["+startIndex+","+endIndex+"]");
//		System.out.println("number max subarray:"+num_max_subarray);

		return max;
	}


	public static void main(String[] args) {
		Best b=new Best();
		int []a= {2,-3,3,-1};
		b.Calculate_Best(a);
	}

}
