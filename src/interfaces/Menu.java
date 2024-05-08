package interfaces;

import jugadores.Jugador;
import preguntas.Pregunta;

public interface Menu {
	public abstract void mostrarPrincipal();
//	public abstract String elegirPrincipal();
	
	public abstract void mostrarGestorJugadores();
//	public abstract String elegirGestorJugadores();
	
	public abstract void mostrarJugadores();

	public abstract void mostrarHistorico();
	
	public abstract void volver();
	
	public abstract void mostrarRanking();
	
	public abstract void mostrarElegirCantidadJugadores();
//	public abstract int[] elegirCantidadJugadores();
	
	public abstract void mostrarElegirJugador();
//	public abstract String elegirJugador();
	
	public abstract void mostrarElegirRondas();
//	public abstract int elegirRondas();
	
	public abstract void mostrarPregunta(Pregunta pregunta);
	
	public abstract void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta);
	
	public abstract void mostrarFinPartida();
}