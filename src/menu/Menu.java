package menu;

import jugadores.Jugador;

public abstract class Menu {
	public abstract void mostrarPrincipal();
	public abstract String elegirPrincipal();
	
	public abstract void mostrarGestorJugadores();
	public abstract String elegirGestorJugadores();

	public abstract void mostrarHistorico();
	
	public abstract void mostrarRanking();
	
	public abstract void mostrarElegirCantidadJugadores();
	
	public abstract void mostrarElegirJugador();
	public abstract Jugador elegirJugador(String nombre);
	
	public abstract void mostrarElegirRondas();
	public abstract int elegirRondas();
	
	public abstract void mostrarPregunta();
	public abstract void preguntaTerminada(); //TODO posibles mensaje dependiendo si aciertas o no
	
	public abstract void mostarFinPartida();
}