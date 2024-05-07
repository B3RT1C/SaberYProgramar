package main;

import main.GUI.JuegoGUI;
import main.consola.JuegoConsola;

public class Main {
	public static void main(String[] args) throws Exception {
		boolean consola = true;
		
		if (consola) {
			new JuegoConsola();
		
		} else {
			new JuegoGUI();
		}
		
	}
}