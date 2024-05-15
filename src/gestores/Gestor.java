package gestores;

import java.util.Random;

/**
 * Clase usada como herramienta principal para acceder a instancias de objetos estaticos de utilidad para gestionar la partida
 */
public class Gestor {
	/**
	 * Objeto estatico de la clase Random
	 * @see Random
	 */
	public static Random rand = new Random();
	/**
	 * Objeto estatico de la clase Ranking
	 * @see Ranking
	 */
	public static Ranking jugadores = new Ranking();
	/**
	 * Objeto estatico de la clase Partida
	 * @see Partida
	 */
	public static Partida partida = new Partida();
	/**
	 * Objeto estatico de la clase Historial
	 * @see Historial
	 */
	public static Historial historial = new Historial();
	/**
	 * Objeto estatico de la clase Log
	 * @see Log
	 */
	public static Log log = new Log();
}