package Lec10;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class HoffmanCoding {
	
	static int priority_time=0;
	static int sorted_time=0;
	
	public static void main(String[] args) {
		ver arr[]=new ver[4];
		arr[0]=new ver(20,'a');
		arr[1]=new ver(20,'b');
		arr[2]=new ver(20,'c');
		arr[3]=new ver(40,'d');
//		arr[4]=new ver(20,'e');

		hoffman_priority_queue(arr);
		hoffman_sorted_with_queues(arr);
	}



	public static void init_matrix(int arr[][],LinkedList<Integer> prob,int num) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j]=-1;
			}
		}

		for (int i = 0; i < num; i++) {
			arr[i][3]=prob.get(i);
		}
	}


	public static void hoffman_priority_queue(ver[] arr) {

		System.out.println("priority_queue:");
		ver root=new ver();

		int N=arr.length;
		String ans[]=new String[N];
		PriorityQueue<ver> q = new PriorityQueue<ver>(N, new MyComparator());
		for (int i = 0; i < arr.length; i++) {//nlogn
			arr[i].left=null;
			arr[i].right=null;
			q.add(arr[i]);
		}
		
//		priority_time+=arr.length*Math.log(arr.length);

		while (q.size()>1) {//o(n)
			ver min1=q.poll();//left child - 0 :o(logn)
			ver min2=q.poll();//right child - 1 :o(logn)
			
			priority_time+=Math.log(arr.length);
			
			ver father=new ver(min1.prob+min2.prob,'-',min1,min2);
			root=father;
			q.add(father);
			
			priority_time++;
		}

		print_code(root,"");
		
		System.out.println("running time:"+priority_time);

	}	

	public static void print_code(ver root,String s) {
		if(root==null) {
			return;
		}
		if(root.left==null && root.right==null && Character.isLetter(root.letter)) {
			System.out.println(root.letter +":"+s);
		}

		print_code(root.left,s+"0");
		print_code(root.right,s+"1");
	}

	public static void hoffman_sorted_with_queues(ver[] arr) {
		System.out.println("sorted_with_queue:");

		ArrayBlockingQueue<ver> Q1 = new ArrayBlockingQueue<ver>(arr.length);
		ArrayBlockingQueue<ver> Q2 = new ArrayBlockingQueue<ver>(arr.length);

		ver root=new ver();

		for (int i = 0; i < arr.length; i++) {
			arr[i].left=null;
			arr[i].right=null;
			Q1.add(arr[i]);
		}

		ver min1=Q1.poll();
		ver min2=Q1.poll();
		ver father=new ver(min1.prob+min2.prob, '-', min1, min2);
		root=father;
		Q2.add(father);
		ver left_child,right_child;
		
		sorted_time++;

		while(!Q1.isEmpty() ) {//o(n)
			ver temp1=Q1.peek();
			ver temp2=Q2.peek();
			boolean remove_q1=false;
			if(temp1.prob<temp2.prob) {
				left_child=Q1.poll();//remove temp1
				remove_q1=true;
			}
			else {//remove temp2
				left_child=Q2.poll();
			}

			if(remove_q1) {
				if(Q1.isEmpty() && !Q2.isEmpty()) {
					while(!Q2.isEmpty()) {
						right_child=Q2.poll();
						ver f=new ver(left_child.prob+right_child.prob,'-',left_child,right_child);
						root=f;
						Q2.add(f);
						sorted_time++;
						if(!Q2.isEmpty()) {
							left_child=Q2.poll();
						}
					}
					
					continue;

				}

				if(!Q1.isEmpty() && Q1.peek().prob<temp2.prob) {
					right_child=Q1.poll();
					ver f=new ver(temp1.prob+right_child.prob, '-', left_child, right_child);
					root=f;
					Q2.add(f);
				}
				else if(!Q1.isEmpty() && Q1.peek().prob>=temp2.prob) {
					right_child=Q2.poll();
					ver f=new ver(temp2.prob+right_child.prob, '-', left_child, right_child);
					root=f;
					Q2.add(f);
				}
			}
			else {

				if(!Q1.isEmpty() && Q2.isEmpty()) {
					ver f=new ver(temp1.prob+temp2.prob,'-',temp2,temp1);
					root=f;
					sorted_time++;
					continue;
				}
				right_child=Q1.poll();
				ver f=new ver(temp2.prob+right_child.prob, '-', temp2, right_child);
				root=f;
				Q2.add(f);
			}
			sorted_time++;
		}

		print_code(root, "");
		
		System.out.println("running time:"+sorted_time);
	}
}
