package jugadores;

import preguntas.Ingles;
import preguntas.Pregunta;

public class Persona extends Jugador{
	public Persona(String nombre) {
		super(nombre);
	}

	@Override
	public boolean responder(String respuesta, Pregunta pregunta) {
		//En las preguntas de inglés se tomará como respuesta incorrecta automáticamente una respuesta diferente a (a, b, c, d)
		if (pregunta instanceof Ingles && respuesta.matches("[ABCD]")) {
			respuesta = respuesta.toUpperCase();
			char opcion = respuesta.toCharArray()[0];
			respuesta = ((Ingles)pregunta).getOpciones().get(opcion - 'A'); //;)
		}
		
		boolean isAcierto = pregunta.verificarRespuesta(respuesta);
		super.preguntaAcertada(isAcierto);
		return isAcierto;
	}

}