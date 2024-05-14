package main;

import main.GUI.JuegoGUI;
import main.consola.JuegoConsola;

/**
 * Contiene el metodo main
 */
public class Main {
	/**
	 * Llama el metodo que se encargara de iniciar todo lo necesrio para que empiece el juego
	 * @see main.consola
	 * @see main.GUI
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		boolean consola = true; // <- cambiar a false en caso de querer jugar en GUI
		
		if (consola) {
			new JuegoConsola();
		
		} else {
			new JuegoGUI();
		}
		
		
	}
}