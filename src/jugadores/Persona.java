package jugadores;

import preguntas.Pregunta;

public class Persona extends Jugador{
	public Persona(String nombre) {
		super(nombre);
	}

	@Override
	public boolean responder(String respuesta, Pregunta pregunta) {
		boolean isAcierto = pregunta.verificarRespuesta(respuesta);
		super.preguntaAcertada(isAcierto);
		return isAcierto;
	}

}