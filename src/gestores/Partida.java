package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Jugador;
import preguntas.Pregunta;

public class Partida {
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	private int numJugadores;
	
	protected Partida() {}
	
	//Para usar la clase bien hay que llamar al método configurar y luego añadir los jugadores
	public void configurar(int numJugadores, int numRondas) {
		this.reiniciarPartida();
		this.numJugadores = numJugadores;
		this.generarPreguntas(numJugadores*numRondas);
	}
	
	private void reiniciarPartida() {
		this.jugadores.clear();
		this.preguntas.clear();
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
		if (!jugadores.contains(jugador)) {
			this.jugadores.add(jugador);

			if (this.jugadores.size() == numJugadores) {
				this.jugadores.remove(0);
			}
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
	
	public boolean isFinished() {
		return this.preguntas.size() == 0;
	}
	
	public String getPuntuacion() {
		String puntuacion = "";
		
		for (Jugador i : this.jugadores) {
			puntuacion += i.getNombre() + ":" + i.getPuntosPartida() + " ";
		}
		return puntuacion;
	}
	
	public void terminarPartida() {
		if (this.isFinished()) {
			Gestor.historial.escribirArchivo();
		}
	}
	
}