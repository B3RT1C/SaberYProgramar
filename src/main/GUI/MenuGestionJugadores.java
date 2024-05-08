package main.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MenuGestionJugadores extends MenuSeleccion {

	protected MenuGestionJugadores() {
		super(4);
		
		this.setBackground(Color.GREEN);
		
		super.anyadirNombres(new String[]{"Ver jugadores","Añadir jugador","Eliminar jugador","Volver"});
	
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
				JuegoGUI.visuales.mostrarPrincipal();
			}
		});
	}
}