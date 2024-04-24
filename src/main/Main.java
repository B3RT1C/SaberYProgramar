package main;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		
		
		System.out.println(a);
		Collections.rotate(a, -1);
		System.out.println(a);
		a.add(a.get(0));
		a.remove(0);
		System.out.println(a);
	}
}