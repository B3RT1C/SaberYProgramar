package main.GUI;

import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaces.Menu;
import jugadores.Jugador;
import main.GUI.Menus.FinPregunta;
import main.GUI.Menus.PreguntaIngles;
import main.GUI.Menus.PreguntaMateLetras;
import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;

class VisualesGUI implements Menu {
	private JPanel actual;
	private JuegoGUI frame;
	private Stack<JPanel> ultimoPanel = new Stack<>();
	private Menus menu = new Menus();
	
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
		this.cambiarJPanel(menu.new Principal() ,false);
		//Se limpia el stack porque si se pulsa el boton de salir de la partida quedan paneles no deseados dentro del stack
		this.ultimoPanel.clear();
	}
	
	@Override
	public void mostrarGestorJugadores() {
		this.cambiarJPanel(menu.new GestionJugadores()  ,false);
	}

	@Override
	public void mostrarJugadores() {
		this.cambiarJPanel(menu.new MostrarJugadores()  ,false);
		
	}

	@Override
	public void mostrarHistorico() {
		this.cambiarJPanel(menu.new MostrarHistorico()  ,false);
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
		this.cambiarJPanel(menu.new MostrarRanking()  ,false);
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		this.cambiarJPanel(menu.new ElegirCantidadJugadores() ,false);
	}

	@Override
	public void mostrarElegirJugador() {
		this.cambiarJPanel(menu.new AnyadirJugador() ,false);
	}
	
	@Override
	public void mostrarEliminarJugador() {
		this.cambiarJPanel(menu.new EliminarJugador() ,false);
	}

	@Override
	public void mostrarElegirRondas() {
		this.cambiarJPanel(menu.new ElegirRondas() ,false);
	}
	
	@Override
	public void mostrarElegirJugadorPartida() {
		this.cambiarJPanel(menu.new AnyadirJugadorPartida() ,false);
	}

	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		if (pregunta instanceof Mates || pregunta instanceof Letras) {
			PreguntaMateLetras menuPreguntaMateLetras = menu.new PreguntaMateLetras(pregunta);
			this.cambiarJPanel(menuPreguntaMateLetras ,false);
			menuPreguntaMateLetras.isCPUJugando();
		
		} else if (pregunta instanceof Ingles) {
			PreguntaIngles menuPreguntaIngles = menu.new PreguntaIngles(pregunta);
			this.cambiarJPanel(menuPreguntaIngles ,false);
			menuPreguntaIngles.isCPUJugando();
		}
	}
	
	@Override
	public void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta) {
		FinPregunta menuFinPregunta = menu.new FinPregunta(jugador, respuesta, pregunta);
		this.cambiarJPanel(menuFinPregunta ,false);
		JOptionPane.showMessageDialog(menuFinPregunta, menuFinPregunta.getMensaje());
		menuFinPregunta.continuar();
	}
	
	@Override
	public void mostrarFinRonda() {
		this.cambiarJPanel(menu.new FinRonda(), false);
	}

	@Override
	public void mostrarFinPartida() {
		this.cambiarJPanel(menu.new FinPartida() ,false);
	}

}