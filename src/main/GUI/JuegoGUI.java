package main.GUI;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JuegoGUI extends JFrame {
	
	static VisualesGUI visuales;
	
	public JuegoGUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(ConstsGUI.FRAMEWIDTH, ConstsGUI.FRAMEHEIGHT);
		this.setTitle("Saber Y Programar");
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);

		JuegoGUI.visuales = new VisualesGUI(this);
		JuegoGUI.visuales.mostrarPrincipal();
	}
}