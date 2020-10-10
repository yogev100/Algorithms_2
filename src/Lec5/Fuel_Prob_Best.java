package Lec5;

import java.util.Arrays;

public class Fuel_Prob_Best {
	int c[];
	int start_ver;
	int a_sum,b_sum;
	
	public Fuel_Prob_Best(int a[],int b[]) {
		c=new int[a.length];
		for (int i = 0; i < b.length; i++) {
			c[i]=a[i]-b[i];
			a_sum+=a[i];
			b_sum+=b[i];
		}
	}
	
	public int get_vertex_to_start() {
		System.out.println(Arrays.toString(c));
		if(b_sum>a_sum) {
			System.out.println("cant drive all the cycle");
			return -1;
		}
		
		Cycle_Best cb=new Cycle_Best(c);
		cb.getCircleBest();
		System.out.println("cycle_best at ["+cb.beginCircle+","+cb.endCircle+"]:"+cb.maxCircle);
		System.out.println("the vertex you should start for close the cycle is "+cb.beginCircle);
		return cb.beginCircle;
	}
	
	public static void main(String[] args) {
		int a[]= {6,6,2,8};
		int b[]= {5,4,3,4};
		Fuel_Prob_Best fpb=new Fuel_Prob_Best(a, b);
		fpb.get_vertex_to_start();
		
	}
	
}
