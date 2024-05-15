package jugadores;

import preguntas.Ingles;
import preguntas.Pregunta;
/**
 * Permite crear un jugador el cual podra responder a preguntas
 */
public class Jugador {
	/**
	 * Nombre del jugador, los nombres de los jugadores siempre seran en mayusculas
	 */
	private String nombre;
	/**
	 * Numero de preguntas acertadas del jugador en la partida actual
	 */
	private int puntosPartida;
	/**
	 * Constructor de la clase, cambia el nombre del jugador a mayusculas e inicializa los puntos a 0
	 * @param nombre Nombre del jugador a instanciar
	 */
	public Jugador(String nombre) {
		this.nombre = nombre.toUpperCase();
		this.puntosPartida = 0;
	}
	/**
	 * Permite al jugador responder a una pregunta
	 * @param respuesta Respuesta a la pregunta
	 * @param pregunta Pregunta a responder
	 * @return Devuelve true si se ha acertado la pregunta y false si se ha fallado
	 */
	public boolean responder(String respuesta, Pregunta pregunta) {
		//En las preguntas de inglés se tomará como respuesta incorrecta automáticamente una respuesta diferente a (a, b, c, d)
		if (pregunta instanceof Ingles && respuesta.matches("[ABCDabcd]")) {
			respuesta = respuesta.toUpperCase();
			char opcion = respuesta.toCharArray()[0];
			respuesta = ((Ingles)pregunta).getOpciones().get(opcion - 'A');
		}

		boolean isAcierto = pregunta.verificarRespuesta(respuesta);
		this.preguntaAcertada(isAcierto);
		return isAcierto;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public int getPuntosPartida() {
		return this.puntosPartida;
	}
	/**
	 * Aumenta en 1 los puntos del jugador si se ha hacertado una pregunta
	 * @param preguntaAcertada true si se ha acertado la pregunta, false si no se ha acertado la pregunta
	 */
	private void preguntaAcertada(boolean preguntaAcertada) {
		if (preguntaAcertada) {
			this.puntosPartida++;
		}
	}
}