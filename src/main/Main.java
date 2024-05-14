package main;

import main.GUI.JuegoGUI;
import main.consola.JuegoConsola;

/**
 * Llama al método que inicia el bucle del principal del juego
 */
public class Main {
	public static void main(String[] args) throws Exception {
		boolean consola = false; // <- False en caso de querer jugar en GUI
		
		if (consola) {
			new JuegoConsola();
		
		} else {
			new JuegoGUI();
		}
		
		
	}
}