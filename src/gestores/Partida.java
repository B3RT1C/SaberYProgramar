package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Jugador;
import preguntas.Pregunta;

public class Partida {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	private int numPreguntas;
	
	public Partida(int numRondas, int numJugadores) {
		this.numPreguntas = numRondas*numJugadores;
		this.generarPreguntas();
	}
	
	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	private void generarPreguntas() {
		for (int i = 0; i < numPreguntas; i++) {
			preguntas.add(Pregunta.generarAleatoria());
		}
	}
	
	public boolean addJugador(Jugador jugador) {
		if (jugadores.contains(jugador)) {
			jugadores.add(jugador);
			Collections.shuffle(jugadores);
			return true;
			
		} else {
			return false;
		}
	}
	
	public Pregunta nextPregunta() {
		this.preguntas.remove(0);
		return this.preguntas.get(0);
	}
	
	public Jugador nextJugador() {
		Jugador primerJugadorEnCola = jugadores.get(0);
		Collections.rotate(jugadores, -1);
		return primerJugadorEnCola;
	}
	
	
}