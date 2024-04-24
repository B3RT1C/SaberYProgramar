package jugadores;

import preguntas.Pregunta;

public abstract class Jugador {
	private String nombre; //TODO ¿?
	private int puntosPartida;

	protected Jugador(String nombre) {
		this.nombre = nombre;
		this.puntosPartida = 0;
	}

	public abstract boolean responder(Pregunta pregunta);

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