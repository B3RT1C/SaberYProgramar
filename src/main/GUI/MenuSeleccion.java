package main.GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class MenuSeleccion extends JPanel {
	private JButton[] botones;
	
	private int medioX;
	private int medioY;
	
	protected MenuSeleccion(int numBotones) {
		this.setLayout(null);
		this.botones = new JButton[numBotones];
		
		medioX = (ConstsGUI.frameWidth/2)-(ConstsGUI.selectButonWidth/2);
		medioY = (ConstsGUI.frameHeight/2)-(((20*(botones.length))+10*(botones.length-1))/2);
		
		this.configurarBotones();
		this.anyadirBotones();
	}
	
	protected JButton[] getBotones() {
		return this.botones;
	}
	
	private void configurarBotones() {
		for (int i = 0; i < this.botones.length; i++) {
			this.botones[i] = new JButton();
			this.botones[i].setBounds(medioX,medioY+(30*i),ConstsGUI.selectButonWidth,ConstsGUI.selectButonHeight);
		}
	}
	
	private void anyadirBotones() {
		for (JButton b : botones) {
			this.add(b);
		}
	}
	
	protected void anyadirNombres(String[] nombres) {
		for (int i = 0; i < this.botones.length; i++) {
			this.botones[i].setText(nombres[i]);
		}
	}
}
