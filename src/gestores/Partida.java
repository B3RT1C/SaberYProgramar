package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Jugador;
import preguntas.Pregunta;

public class Partida {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	Partida() {}
	
	public void setNumRondas(int numRondas) {
		this.generarPreguntas(numRondas*this.jugadores.size());
	}
	
	private void generarPreguntas(int numPreguntas) {
		for (int i = 0; i < numPreguntas; i++) {
			this.preguntas.add(Pregunta.generarAleatoria());
		}
	}
	
	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	public boolean addJugador(Jugador jugador) {
		if (jugadores.contains(jugador)) {
			this.jugadores.add(jugador);
			Collections.shuffle(this.jugadores);
			return true;
			
		} else {
			return false;
		}
	}
	
	public <T> T nextEnCola(ArrayList<T> cola, boolean rotate) {
		try {
			T primeroEnCola = cola.get(0);
			cola.remove(0);
			
			if (rotate) {
				cola.add(primeroEnCola);
			}
			return primeroEnCola;

		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Pregunta nextPregunta() {
		return this.nextEnCola(this.preguntas, false);
	}
	
	public Jugador nextJugador() {
		return this.nextEnCola(this.jugadores, true);
	}
	
	
}