package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import util.Consts;

public class Main {
	public static void main(String[] args) {
		
		try {
			Files.writeString(Consts.RANKING_PATH, "");
			
			for (int i = 0; i < 5; i++) {
				Files.writeString(Consts.RANKING_PATH,String.valueOf(i)+"\n",StandardOpenOption.APPEND);		
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> a = new ArrayList<>();
		try {
			a = (ArrayList<String>)Files.readAllLines(Consts.RANKING_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		for (String i : a) {
			System.out.println(i);
		}
		System.out.println("uqwe");
	
	}
}