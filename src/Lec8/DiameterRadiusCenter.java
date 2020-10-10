package Lec8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class DiameterRadiusCenter {

	public static void main(String[] args) {
//		ArrayList<ArrayList<Integer>> tree=get_tree();
//		BurningAlgorithm(tree);
		ArrayList<ArrayList<Integer>> tree2=new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			tree2.add(new ArrayList<Integer>());
		}
		tree2.get(0).add(1);
		tree2.get(1).add(0);
		tree2.get(1).add(2);
		tree2.get(2).add(1);
		tree2.get(2).add(3);
		tree2.get(3).add(2);
		BurningAlgorithm(tree2);
	}

	public static ArrayList<ArrayList<Integer>> get_tree(){
		ArrayList<ArrayList<Integer>> tree=new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			tree.add(new ArrayList<Integer>());
		}

		tree.get(0).add(1);
		tree.get(1).add(0);
		tree.get(1).add(2);
		tree.get(1).add(3);
		tree.get(2).add(1);
		tree.get(3).add(1);
		tree.get(3).add(4);
		tree.get(3).add(5);
		tree.get(3).add(6);
		tree.get(4).add(3);
		tree.get(5).add(3);
		tree.get(6).add(3);
		tree.get(6).add(7);
		tree.get(7).add(6);
		return tree;
	}

	public static void BurningAlgorithm(ArrayList<ArrayList<Integer>> tree) {
		int degree[]=new int[tree.size()];
		int count_size=tree.size();
		int count_burn=0;
		for (int i = 0; i < degree.length; i++) {
			degree[i]=tree.get(i).size();
		}

		ArrayBlockingQueue<Integer> Q = new ArrayBlockingQueue<Integer>(tree.size());
		while(count_size>2) {
			for (int i = 0; i < degree.length; i++) {//burning the leaves
				if(degree[i]==1) {
					Q.add(i);
					degree[i]--;


				}
			}
			count_burn++;

			Integer temp=-1;
			while(!Q.isEmpty()) {
				temp=Q.poll();
				count_size--;

				for (int i = 0; i < tree.size(); i++) {

					if(degree[i]>1 && tree.get(i).contains(temp)) {
						degree[i]--;		
					}
				}
			}
		}
		System.out.println(count_size);
		System.out.println(Arrays.toString(degree));
		int radius=0;
		int center1=-1;
		int center2=-1;
		int diameter=0;
		for (int i = 0; i < degree.length; i++) {
			if(degree[i]>0) {
				if(center1==-1) {
					center1=i;
				}
				else {
					center2=i;
				}
			}


		}
		if(count_size==1) {
			radius=count_burn;
			diameter=2*radius;
		}
		else {
			radius=count_burn+1;
			diameter=2*radius-1;
		}
		if(center2==-1) {
			System.out.println("the radius of the tree is:"+radius);
			System.out.println("the diameter of the tree is:"+diameter);
			System.out.println("the center of the tree is:"+center1);
		}
		else {
			System.out.println("the radius of the tree is:"+radius);
			System.out.println("the diameter of the tree is:"+diameter);
			System.out.println("the centers of the tree is:"+center1+" , "+center2);
		}
	}
}
