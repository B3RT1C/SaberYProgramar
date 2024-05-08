package main.GUI;

import java.awt.Component;

import javax.swing.JPanel;

import interfaces.Menu;
import jugadores.Jugador;
import preguntas.Pregunta;

public class VisualesGUI implements Menu {
	private Component actual;
	private JuegoGUI frame;
	
	VisualesGUI(JuegoGUI frame) {
		this.frame = frame;
	}

	private void cambiarJPanel(JPanel nuevo) {
		if (nuevo != null) {
			try {
				frame.remove(this.actual);
			} catch (NullPointerException e) {
			}
			this.actual = frame.add(nuevo);
			frame.validate();
		}
	}

	@Override
	public void mostrarPrincipal() {
		this.cambiarJPanel(new MenuPrincipal());
	}
	
	@Override
	public void mostrarGestorJugadores() {
		this.cambiarJPanel(new MenuGestionJugadores());
	}

	@Override
	public void mostrarJugadores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarHistorico() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarRanking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarElegirJugador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarElegirRondas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFinPartida() {
		// TODO Auto-generated method stub
		
	}

	

}
