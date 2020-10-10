package Lec5;

import java.util.Arrays;

public class Cycle_Best {
	int maxCircle; 
	int sumAll; 
	int plusArr[]; 
	int minusArr[]; 
	int beginCircle;
	int endCircle;
	int sizeOfBest;

	public Cycle_Best(int arr[]) {
		int size = arr.length;
		maxCircle = arr[0];
		sumAll = 0;
		plusArr = new int[size];
		minusArr = new int[size];
		beginCircle = 0;
		endCircle = 0;
		sizeOfBest = 0;
		for (int i = 0; i < size; i++) {
			plusArr[i] = arr[i];
			minusArr[i] = (-1)*arr[i];
			sumAll = sumAll + arr[i]; // save the sum of the array for next step
		}
	}
	/**
	 * find the Best sub Array in circle Array
	 */
	public int getCircleBest() {
		Best positive = new Best(plusArr);
		//		int maxPositive = positive.Calculate_Best();
		if (positive.max<0) { // all the elements are negative, the function finish here
			maxCircle = positive.max;
			beginCircle = positive.startIndex;
			endCircle = positive.endIndex;
			sizeOfBest = 1;
			return maxCircle;
		}
		Best negative = new Best(minusArr);
		//		int maxNegative = negative.getBest();
		if (positive.max >= sumAll + negative.max) {// if the regular best greater than the cycle best
			maxCircle = positive.max;
			beginCircle = positive.startIndex;
			endCircle = positive.endIndex;
			sizeOfBest = endCircle - beginCircle + 1;

		} else { // if the cycle best greater than the regular best - so comp=sum(a)+best(-a)
			maxCircle = sumAll + negative.max;
			beginCircle = negative.endIndex+1;
			endCircle = negative.startIndex-1;
			sizeOfBest = plusArr.length - beginCircle + endCircle + 1;
		}
		return maxCircle;
	}
	public void printResult() {
		System.out.println("the Array is: "+ Arrays.toString(plusArr));
		System.out.println("= = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("the start index is: " + beginCircle);
		System.out.println("the end index is: " + endCircle);
		System.out.print(" the max sub Array is: [\t");
		for (int i = 0; i < sizeOfBest; i++) {
			System.out.print(plusArr[(i+beginCircle) % plusArr.length]+"\t");
		}
		System.out.println("]");
		System.out.println("the max sum is: " + maxCircle);
		System.out.println("the number of element is: "+ sizeOfBest);
		System.out.println("============================================");
	}
	
	public static void main(String[] args) {
		int []a= {0,-1,-6,4,-4,7};
		Cycle_Best b=new Cycle_Best(a);
		b.getCircleBest();
		b.printResult();
	}
}


