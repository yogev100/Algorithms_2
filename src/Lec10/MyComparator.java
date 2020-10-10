package Lec10;

import java.util.Comparator;

	// comparator class helps to compare the node 
	// on the basis of one of its attribute. 
	// Here we will be compared 
	// on the basis of data values of the nodes. 
	class MyComparator implements Comparator<ver> { 
		public int compare(ver x, ver y) 
		{ 

			return x.prob - y.prob; 
		} 
	} 

