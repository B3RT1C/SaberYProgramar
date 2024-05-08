package main.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class MenuPrincipal extends MenuSeleccion {
	
	MenuPrincipal() {
		super(5);
				
		this.setBackground(Color.CYAN);

		super.anyadirNombres(new String[]{"Jugar","Ranking","Histórico","Jugadores","Salir"});
		
		super.getBotones()[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		super.getBotones()[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		super.getBotones()[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		super.getBotones()[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarGestorJugadores();
			}
		});
		
		super.getBotones()[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}