package Lec9;

import java.util.ArrayList;
import java.util.Arrays;

public class IsomorphismTrees {

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> tree1 = get_tree3();
		ArrayList<ArrayList<Integer>> tree2 = get_tree4();
		boolean flag[]=new boolean[tree1.size()];
		//System.out.println(Array_to_String(tree_string(tree1,0,0,flag)));
//		System.out.println(Array_to_String(tree_string(tree2,0,0,flag)));
		System.out.println(Is_isomorphisms(tree1, 0, tree2, 0));
	}

	public static ArrayList<ArrayList<Integer>> get_tree1(){
		ArrayList<ArrayList<Integer>> tree=new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			tree.add(new ArrayList<Integer>());
		}

		tree.get(0).add(1);
		tree.get(0).add(2);
		tree.get(0).add(6);
		tree.get(1).add(0);
		tree.get(2).add(0);
		tree.get(2).add(3);
		tree.get(2).add(4);
		tree.get(3).add(2);
		tree.get(4).add(2);
		tree.get(4).add(5);
		tree.get(5).add(4);
		tree.get(6).add(0);

		return tree;
	}

	public static ArrayList<ArrayList<Integer>> get_tree2(){
		ArrayList<ArrayList<Integer>> tree=new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			tree.add(new ArrayList<Integer>());
		}

		tree.get(0).add(1);
		tree.get(0).add(5);
		tree.get(0).add(6);
		tree.get(1).add(0);
		tree.get(1).add(2);
		tree.get(1).add(3);
		tree.get(2).add(1);
		tree.get(3).add(1);
		tree.get(3).add(4);
		tree.get(4).add(3);
		tree.get(5).add(0);
		tree.get(6).add(0);

		return tree;
	}
	
	public static ArrayList<ArrayList<Integer>> get_tree3(){
		ArrayList<ArrayList<Integer>> tree=new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			tree.add(new ArrayList<Integer>());
		}

		tree.get(0).add(1);
		tree.get(0).add(2);
		tree.get(0).add(3);
		tree.get(1).add(0);
		tree.get(2).add(0);
		tree.get(3).add(0);
		tree.get(3).add(4);
		tree.get(3).add(5);
		tree.get(4).add(3);
		tree.get(5).add(3);
		tree.get(5).add(6);
		tree.get(6).add(5);

		return tree;
	}
	
	public static ArrayList<ArrayList<Integer>> get_tree4(){
		ArrayList<ArrayList<Integer>> tree=new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			tree.add(new ArrayList<Integer>());
		}

		tree.get(0).add(1);
		tree.get(0).add(2);
		tree.get(0).add(3);
		tree.get(1).add(0);
		tree.get(1).add(4);
		tree.get(1).add(5);
		tree.get(2).add(0);
		tree.get(3).add(0);
		tree.get(4).add(1);
		tree.get(5).add(1);
		tree.get(2).add(6);
		tree.get(6).add(2);

		return tree;
	}


	
	public static boolean Is_isomorphisms(ArrayList<ArrayList<Integer>> tree1,int root1,ArrayList<ArrayList<Integer>> tree2,int root2) {
		boolean []flag=new boolean[tree1.size()];

		boolean ans=Is_isomorphisms(tree1,root1, tree2,root2,flag);
		return ans;
	}

	public static boolean Is_isomorphisms(ArrayList<ArrayList<Integer>> tree1,int root1,ArrayList<ArrayList<Integer>> tree2,int root2,boolean []flag) {

		String str1[]=tree_string(tree1,root1,root1,flag);
		boolean flag2[]=new boolean[tree2.size()];
		String str2[]=tree_string(tree2,root2,root2,flag2);
		String ans1=Array_to_String(str1);
		String ans2=Array_to_String(str2);
		return ans1.equals(ans2);
	}

	private static String[] tree_string(ArrayList<ArrayList<Integer>> tree,int root,int orig,boolean[] flag) {
		flag[root]=true;
		
		String root_str[]=new String[tree.get(root).size()];
		if(tree.get(root).size()==1) {
			root_str[0]="01";
			return root_str;
		}

		for (int i = 0; i < root_str.length; i++) {
			root_str[i]="";
		}

		ArrayList<Integer> tree_root=tree.get(root);

		for (int i = 0; i < tree_root.size(); i++) {
			if(flag[tree_root.get(i)]==false) {
				int v=tree_root.get(i);
				String temp[]=tree_string(tree,v,orig,flag);
				root_str[i]=Array_to_String(temp);//
			}
		}

		if(root==orig) {
			Arrays.sort(root_str);
		}
		String ans="0"+Array_to_String(root_str)+"1";
		String []ans_arr=new String[1];
		ans_arr[0]=ans;
		if(root==orig) {
			System.out.println(ans_arr[0]);
		}
		return ans_arr;

	}

	private static String Array_to_String(String[] arr) {
		String ans="";
		for(String s:arr) {
			ans+=s;
		}
		return ans;
	}
}
