package interfaces;

import jugadores.Jugador;
import preguntas.Pregunta;
/**
 * Interfaz encargada de gestionar como se ha de mostrar la informacion en pantalla
 */
public interface Menu {
	/**
	 * Muestra el menu principal
	 */
	public abstract void mostrarPrincipal();
	/**
	 * Muestra el menu de gestion de jugadores
	 */
	public abstract void mostrarGestorJugadores();
	/**
	 * Muestra los jugadores existentes en el sistema
	 */
	public abstract void mostrarJugadores();
	/**
	 * Muestra el historial de partidas con el nombre de cada jugador y el numero de preguntas acertadas en esa partida
	 */
	public abstract void mostrarHistorico();
	/**
	 * Usado cuando cuando se quiera volver de un menu al anterior
	 */
	public abstract void volver();
	/**
	 * Muestra el ranking ordenado de mayor a menor por numero de preguntas total acertadas por cada jugador
	 */
	public abstract void mostrarRanking();
	/**
	 * Muestra un mensaje para pedir el numero de jugadores totales a añadir a la partida
	 */
	public abstract void mostrarElegirCantidadJugadores();
	/**
	 * Muestra un menu para crear un jugador en el sistema
	 */
	public abstract void mostrarElegirJugador();
	/**
	 * Muestra un menu para eliminar un jugador del sistema
	 */
	public abstract void mostrarEliminarJugador();
	/**
	 * Muestra el menu con las opciones del numero de rondas predeterminado
	 */
	public abstract void mostrarElegirRondas();
	/**
	 * Muestra un menu para añadir un jugador a la partida
	 */
	public abstract void mostrarElegirJugadorPartida();
	/**
	 * Muestra el enunciado de una pregunta y sus opciones si las tuviera
	 * @param pregunta Pregunta a mostrar
	 */
	public abstract void mostrarPregunta(Pregunta pregunta);
	/**
	 * Muestra informacion tras responder una pregunta (si se ha hacertado o no, respuesta correcta, etc..)
	 * @param jugador Jugador que ha respondido a la pregunta
	 * @param respuesta Respuesta a la pregunta
	 * @param pregunta Pregunta que ha sido respondida
	 */
	public abstract void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta);
	/**
	 * Muestra informacion del puntuaje de los jugadores tras terminar una ronda
	 */
	public abstract void mostrarFinRonda();
	/**
	 * Muestra informacion relacionada con el final de la partida (puntuaciones finales de cada jugador)
	 */
	public abstract void mostrarFinPartida();
}