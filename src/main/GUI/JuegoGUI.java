package main.GUI;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JuegoGUI extends JFrame {
	private Component actual;
	private VisualesGUI visuales;
	
	public JuegoGUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(640,480);
		this.setTitle("Saber Y Programar");
		
		this.setVisible(true);

		this.visuales = new VisualesGUI(this);
		visuales.mostrarPrincipal();
	}
	
	void cambiarJPanel(JPanel nuevo) {
		if (nuevo != null) {
			try {
				this.remove(actual);
			} catch (NullPointerException e) {
			}
			actual = this.add(nuevo);
			this.validate();
		}
	}
	
}
