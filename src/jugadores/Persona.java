package jugadores;

import preguntas.Pregunta;

public class Persona extends Jugador{
	protected Persona(String nombre) {
		super(nombre);
	}

	@Override
	public boolean responder(Pregunta pregunta) {
		// TODO Auto-generated method stub
		return false;
	}

}
