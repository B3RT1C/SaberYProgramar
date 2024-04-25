package main;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(14);
		
		System.out.println(a.get(0));
		a.remove(0);

		try {
			System.out.println(a.get(0));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("uwu");
		}
	}
}