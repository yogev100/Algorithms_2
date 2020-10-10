package Lec10;

import java.util.Stack;

public class ver {
	int prob;
	char letter;
	ver left;
	ver right;
	
	public ver() {
		letter='-';
	}
	
	public ver(int p,char l) {
		letter=l;
		prob=p;
	}
	
	public ver(int p,char l,ver left,ver right) {
		this.prob=p;
		this.letter=l;
		this.left=left;
		this.right=right;
		
		
	}
}
