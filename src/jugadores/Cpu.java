package jugadores;

import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;

/**
 * Subclase de la clase Jugador
 * @see Jugador
 */
public class Cpu extends Jugador {
	/**
	 * Constructor de la clase
	 * @param Nombre nombre de la CPU a crear
	 * @see Jugador
	 */
	public Cpu(String nombre) {
		super(nombre);
	}
	/**
	 * Permite a las CPUs generar una respuesta de manera autmatica, 
	 * la respuesta sera diferente dependiendo de que subclase se a la pregunta
	 * @param pregunta Pregunta de la cual se quiere generar una respuesta
	 * @return Devuelve la respuesta a la pregunta o null en caso de que la pregunta no perteneza a una subclase implementada en este metodo
	 */
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