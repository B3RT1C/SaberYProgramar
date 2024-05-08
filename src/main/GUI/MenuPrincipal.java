package main.GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel {
	
	JuegoGUI frame;
	JButton[] botones;
	String[] nombreBotones = {"Jugar","Ranking","Histórico","Jugadores","Salir"};
	
	public MenuPrincipal(JuegoGUI frame) {
		this.frame = frame;
		
		this.setLayout(null);
		this.setBackground(Color.CYAN);

		this.botones = new JButton[]{new JButton(), 
									 new JButton(), 
									 new JButton(), 
									 new BotonCambiarMenu("CambiarMenu",frame, null), 
									 new BotonSalir()};
		
		int width = 200;
		int height = 20;
		int medioX = (frame.getWidth()/2)-(width/2);
		int medioY = (frame.getHeight()/2)-120;
	
		for (int i = 0; i < botones.length; i++) {
			botones[i].setBounds(medioX,medioY+(30*i),width,height);
		}
		
		for (JButton b : botones) {
			this.add(b);
		}
		
	}
	
}