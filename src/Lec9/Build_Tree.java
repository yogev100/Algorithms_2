package Lec9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Build_Tree {
	
	public static void main(String[] args) {
		int deg[]= {1,1,1,1,2,2,3,3};
		System.out.println(Arrays.toString(build_tree(deg)));
	}
	
	public static int[] build_tree(int[] deg) {
		
		int []tree=new int[deg.length];
		int sum=0;
		for (int i = 0; i < deg.length; i++) {
			sum+=deg[i];
		}
		
		if(sum/2+1!=deg.length) {
			System.out.println("this is not tree degree array");
			return new int[1];
		}
		
		Arrays.sort(deg);
		
		int j=not_a_leaf(deg);
		int N=tree.length;
		for (int i = 0; i < N-2; i++) {
			tree[i]=j;//j is father of i
			deg[j]--;// reduce j degree by one
			if(deg[j]==1) {//if j now is a leaf
				j++;
			}
		}
		tree[N-2]=N-1;
		//the answer is the tree as parent array - so the size will be N-1 because the root no have parent
		int k=0;
		int ans[]=new int[N-1];
		for (int l = 0; l < ans.length; l++) {
			ans[l]=tree[l];
		} 
	
		return ans;
	}
	
	public static int not_a_leaf(int deg[]) {
		for (int i = 0; i < deg.length; i++) {
			if(deg[i]>1) {
				return i;
			}
		}
		return -1;
	}
}
