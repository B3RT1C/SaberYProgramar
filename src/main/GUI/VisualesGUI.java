package main.GUI;

import javax.swing.JPanel;

import interfaces.Menu;
import jugadores.Jugador;
import preguntas.Pregunta;
import util.Consts;

class VisualesGUI implements Menu {
	private JPanel actual;
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
			this.actual = (JPanel) frame.add(nuevo);
			frame.revalidate();
//			frame.repaint();
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
		this.cambiarJPanel(new MenuMostrarJugadores());
		
	}

	@Override
	public void mostrarHistorico() {
		this.cambiarJPanel(new MenuMostrarHistorico());
	}

	@Override
	public void volver() {
		if (this.actual instanceof MenuPrincipal) {
			
			System.exit(0);
		
		} else if (this.actual instanceof MenuMostrarRanking 
					|| this.actual instanceof MenuMostrarHistorico 
						|| this.actual instanceof MenuGestionJugadores) {
			this.mostrarPrincipal();
		
		} else if (this.actual instanceof MenuMostrarJugadores
					|| this.actual instanceof MenuModificarJugador) {
			this.mostrarGestorJugadores();
		}
	}

	@Override
	public void mostrarRanking() {
		this.cambiarJPanel(new MenuMostrarRanking());
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		// TODO Auto-generated method stub
		
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