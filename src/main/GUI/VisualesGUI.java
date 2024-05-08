package main.GUI;

import interfaces.Menu;
import preguntas.Pregunta;

public class VisualesGUI implements Menu {

	JuegoGUI frame;
	
	VisualesGUI(JuegoGUI frame) {
		this.frame = frame;
	}
	
	@Override
	public void mostrarPrincipal() {
		frame.cambiarJPanel(new MenuPrincipal(frame));
	}

	@Override
	public void mostrarGestorJugadores() {
		// TODO Auto-generated method stub
		
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
	public void mostrarFinPartida() {
		// TODO Auto-generated method stub
		
	}

}
