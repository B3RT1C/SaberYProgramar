package jugadores;

import java.util.ArrayList;

import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;
import util.Consts;

public class Cpu extends Jugador {
	public Cpu(String nombre) {
		super(nombre);
	}

	@Override
	public boolean responder(Pregunta pregunta) {
		if (pregunta instanceof Mates) {
			return true;
			
		} else if (pregunta instanceof Letras) {
			return false;
		
		} else if (pregunta instanceof Ingles) {
			Ingles preguntaIng = (Ingles)pregunta;
			ArrayList<String> opcion = preguntaIng.getOpciones();
			String randomOpcion = opcion.get(Consts.RAND.nextInt(0, opcion.size()));
			return pregunta.verificarRespuesta(randomOpcion);
		}
		
		return false;
	}
}