package jugadores;

import preguntas.Ingles;
import preguntas.Pregunta;

public class Jugador {
	private String nombre; //TODO ¿?
	private int puntosPartida;

	public Jugador(String nombre) {
		this.nombre = nombre.toUpperCase();
		this.puntosPartida = 0;
	}

	public boolean responder(String respuesta, Pregunta pregunta) {
		//En las preguntas de inglés se tomará como respuesta incorrecta automáticamente una respuesta diferente a (a, b, c, d)
		if (pregunta instanceof Ingles && respuesta.matches("[ABCD]||[abcd]")) {
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
	public void preguntaAcertada(boolean preguntaAcertada) {
		if (preguntaAcertada) {
			this.puntosPartida++;
		}
	}
}