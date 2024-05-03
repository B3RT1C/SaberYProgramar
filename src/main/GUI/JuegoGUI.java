package main.GUI;

import javax.swing.JFrame;

import interfaces.Menu;
import preguntas.Pregunta;

public class JuegoGUI extends JFrame implements Menu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JuegoGUI() {
		
		
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void mostrarPrincipal() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String elegirPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarGestorJugadores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String elegirGestorJugadores() {
		// TODO Auto-generated method stub
		return null;
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
	public int[] elegirCantidadJugadores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarElegirJugador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String elegirJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarElegirRondas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int elegirRondas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preguntaTerminada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFinPartida() {
		// TODO Auto-generated method stub
		
	}

}
