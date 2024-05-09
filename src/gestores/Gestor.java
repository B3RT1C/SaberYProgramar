package gestores;

import java.util.Random;

public class Gestor {
	public static Random rand = new Random();
	public static Ranking jugadores = new Ranking();
	public static Partida partida = new Partida();
	public static Historial historial = new Historial();
	public static Log log = new Log();
}