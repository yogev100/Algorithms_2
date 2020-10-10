package Lec7;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {


	public static void main(String[] args) {
		ArrayList <Integer> s = new ArrayList<>();
		s.add(0);
		s.add(95);
		s.add(107);
		s.add(-8);
		s.add(83);
		s.add(74);
		s.add(100);
		s.add(67);
		s.add(54);
		s.add(98);

		
		printPassed(s);

	}
	public static void printPassed(Collection<Integer> grades) {
		grades.stream().filter(a->(a>=60&&a<=100)).map(a->100-a).forEach(System.out::println);
	}

}
