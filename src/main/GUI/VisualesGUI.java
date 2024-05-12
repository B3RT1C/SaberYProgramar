package main.GUI;

import java.util.Stack;

import javax.swing.JPanel;

import interfaces.Menu;
import jugadores.Jugador;
import preguntas.Pregunta;

class VisualesGUI implements Menu {
	private JPanel actual;
	private JuegoGUI frame;
	private Stack<JPanel> ultimoPanel = new Stack<>();
	
	VisualesGUI(JuegoGUI frame) {
		this.frame = frame;
	}

	private void cambiarJPanel(JPanel nuevo) {
		if (nuevo != null) {
			try {
				frame.remove(this.actual);
			} catch (NullPointerException e) {
			}
			this.ultimoPanel.push(this.actual);
			this.actual = (JPanel) frame.add(nuevo);
			frame.revalidate();
			frame.repaint();
		}
	}

	@Override
	public void mostrarPrincipal() {
		this.cambiarJPanel(new MenuPrincipal());
		//Se limpia el stack porque si se pulsa el boton de salir de la partida quedan paneles no deseados dentro del stack
		this.ultimoPanel.clear();
	}
	
	@Override
	public void mostrarGestorJugadores() {
		this.cambiarJPanel(new MenuGestionJugadores());
	}

	@Override
	public void mostrarJugadores() {
		this.cambiarJPanel(new MenuMostrarJugadores());
		
	}

	@Override
	public void mostrarHistorico() {
		this.cambiarJPanel(new MenuMostrarHistorico());
	}

	@Override
	public void volver() {
//		if (this.actual instanceof MenuPrincipal) {
//			
//			System.exit(0);
//		
//		} else if (this.actual instanceof MenuMostrarRanking 
//					|| this.actual instanceof MenuMostrarHistorico 
//						|| this.actual instanceof MenuGestionJugadores) {
//			this.mostrarPrincipal();
//		
//		} else if (this.actual instanceof MenuMostrarJugadores
//					|| this.actual instanceof MenuImputJugador) {
//			this.mostrarGestorJugadores();
//		}
		
		this.cambiarJPanel(this.ultimoPanel.pop());
	}

	@Override
	public void mostrarRanking() {
		this.cambiarJPanel(new MenuMostrarRanking());
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		this.cambiarJPanel(new MenuElegirCantidadJugadores());
	}

	@Override
	public void mostrarElegirJugador() {
		this.cambiarJPanel(new MenuAnyadirJugador());
	}
	
	@Override
	public void mostrarEliminarJugador() {
		this.cambiarJPanel(new MenuEliminarJugador());
	}

	@Override
	public void mostrarElegirRondas() {
		this.cambiarJPanel(new MenuElegirRondas());
	}
	
	@Override
	public void mostrarElegirJugadorPartida() {
		this.cambiarJPanel(new MenuAnyadirJugadorPartida());
	}

	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		this.cambiarJPanel(new MenuPregunta(pregunta));
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