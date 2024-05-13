package main.GUI;

import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.Menu;
import jugadores.Jugador;
import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;

class VisualesGUI implements Menu {
	private JPanel actual;
	private JuegoGUI frame;
	private Stack<JPanel> ultimoPanel = new Stack<>();
	
	VisualesGUI(JuegoGUI frame) {
		this.frame = frame;
	}

	//Volviendo se pone true para no añadir JPanel al stack cuando estas retrocediendo en los menús, solo se añaden JPanels avanzando en los menús
	private void cambiarJPanel(JPanel nuevo, Boolean volviendo) {
		if (nuevo != null) {
			try {
				frame.remove(this.actual);
			} catch (NullPointerException e) {
			}
			
			if (!volviendo) {
				this.ultimoPanel.push(this.actual);
			}
			this.actual = (JPanel) frame.add(nuevo);
			frame.revalidate();
			frame.repaint();
		}
	}

	@Override
	public void mostrarPrincipal() {
		this.cambiarJPanel(new MenuPrincipal() ,false);
		//Se limpia el stack porque si se pulsa el boton de salir de la partida quedan paneles no deseados dentro del stack
		this.ultimoPanel.clear();
	}
	
	@Override
	public void mostrarGestorJugadores() {
		this.cambiarJPanel(new MenuGestionJugadores()  ,false);
	}

	@Override
	public void mostrarJugadores() {
		this.cambiarJPanel(new MenuMostrarJugadores()  ,false);
		
	}

	@Override
	public void mostrarHistorico() {
		this.cambiarJPanel(new MenuMostrarHistorico()  ,false);
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
		
		this.cambiarJPanel(this.ultimoPanel.pop(), true);
	}

	@Override
	public void mostrarRanking() {
		this.cambiarJPanel(new MenuMostrarRanking()  ,false);
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		this.cambiarJPanel(new MenuElegirCantidadJugadores() ,false);
	}

	@Override
	public void mostrarElegirJugador() {
		this.cambiarJPanel(new MenuAnyadirJugador() ,false);
	}
	
	@Override
	public void mostrarEliminarJugador() {
		this.cambiarJPanel(new MenuEliminarJugador() ,false);
	}

	@Override
	public void mostrarElegirRondas() {
		this.cambiarJPanel(new MenuElegirRondas() ,false);
	}
	
	@Override
	public void mostrarElegirJugadorPartida() {
		this.cambiarJPanel(new MenuAnyadirJugadorPartida() ,false);
	}

	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		if (pregunta instanceof Mates || pregunta instanceof Letras) {
			MenuPreguntaMateLetras menuPreguntaMateLetras = new MenuPreguntaMateLetras(pregunta);
			this.cambiarJPanel(menuPreguntaMateLetras ,false);
			menuPreguntaMateLetras.isCPUJugando();
		
		} else if (pregunta instanceof Ingles) {
			MenuPreguntaIngles menuPreguntaIngles = new MenuPreguntaIngles(pregunta);
			this.cambiarJPanel(menuPreguntaIngles ,false);
			menuPreguntaIngles.isCPUJugando();
		}
	}
	
	@Override
	public void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta) {
		MenuFinPregunta menuFinPregunta = new MenuFinPregunta(jugador, respuesta, pregunta);
		this.cambiarJPanel(menuFinPregunta ,false);
		JOptionPane.showMessageDialog(menuFinPregunta, menuFinPregunta.getMensaje());
		menuFinPregunta.continuar();
	}

	@Override
	public void mostrarFinPartida() {
		this.cambiarJPanel(new MenuFinPartida() ,false);
	}

}