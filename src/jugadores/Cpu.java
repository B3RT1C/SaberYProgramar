package jugadores;

import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;

public class Cpu extends Jugador {
	public Cpu(String nombre) {
		super(nombre);
	}

	public String generarRespuesta(Pregunta pregunta) {
		if (pregunta instanceof Mates) {
			return pregunta.getSolucion();
			
		} else if (pregunta instanceof Letras) {
			char[] respuesta = pregunta.getSolucion().toCharArray();
			respuesta[0]++;
			return new String(respuesta);
		
		} else if (pregunta instanceof Ingles) {
			int index = ((Ingles) pregunta).getOpciones().indexOf(pregunta.getSolucion());
			char respuesta = (char) ('A'+((index+1)%4));
			return String.valueOf(respuesta);
		
		} else {
			return null;
		}
	}
	
}