package main.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gestores.Gestor;
import util.Consts;

@SuppressWarnings("serial")
public class MenuAnyadirJugadorPartida extends MenuModificarJugador  {
	
	private int numHumanos;
	private TerminarPartida botonSalir = new TerminarPartida();
	
	MenuAnyadirJugadorPartida() {
		this.setBackground(Color.PINK);
		super.setNombreGetText("Seleccionar jugador");

		this.numHumanos = MenuElegirCantidadJugadores.getNumHumanos();
		
		this.esMasFacilHacerEsteMetodoQueArreglarLaHerencia();
		
		this.add(botonSalir);
		
		
	}
	
	//Este metodo elimina un boton que hereda del padre y que causa problemas en este caso, podría arreglarlo pero no me apetece y tampoco me da tiempo :)
	private void esMasFacilHacerEsteMetodoQueArreglarLaHerencia() {
		for (Component i : this.getComponents()) {
			if (i instanceof JButton  && ((JButton)i).getText().equals("Volver")) {
				this.remove(i);
			}
		}
	}

	@Override
	protected ActionListener setListenerGetText() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = getInText().getText();
				
				if (verificarExistenciaJugador(nombre)) {
					addJugadorPartida(nombre);
				}
			}
		};
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Jugadores a añadir restantes: " + String.valueOf(this.numHumanos), 110, 280);
	}
	
	private boolean verificarExistenciaJugador(String nombre) {
		if (Gestor.jugadores.existsJugador(nombre)) {
			return true;
			
		} else {
			int opcion = JOptionPane.showOptionDialog(MenuAnyadirJugadorPartida.this, Consts.MENU_ADD_JUGADOR_INEXISTENTE(nombre), null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Y", "N"}, "Y");
			
			if (opcion == 0) {
				return Gestor.jugadores.crearJugador(nombre);
				
			} else {
				return false;
			}
		}
	}
	
	private void addJugadorPartida(String nombre) {
		if (Gestor.partida.addPersona(nombre)) {
			JOptionPane.showMessageDialog(MenuAnyadirJugadorPartida.this, Consts.MENU_SUCCEED);
			
			this.numHumanos--;
			MenuAnyadirJugadorPartida.this.repaint();

			if (this.numHumanos == 0) {
				JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
			}
			
		} else {
			JOptionPane.showMessageDialog(MenuAnyadirJugadorPartida.this, Consts.ERROR_JUGADOR_REPETIDO);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.ERROR_JUGADOR_REPETIDO);
		}
	}
}