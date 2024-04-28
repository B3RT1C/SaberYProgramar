package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Cpu;
import jugadores.Jugador;
import jugadores.Persona;
import preguntas.Pregunta;

public class Partida {
	private static ArrayList<String> ganadores = new ArrayList<>();

	
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	private int numJugadores;
	private boolean jugadoresMezclados = false;
	
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
		Partida.ganadores.clear();
	}
	
	private void generarPreguntas(int numPreguntas) {
		for (int i = 0; i < numPreguntas; i++) {
			this.preguntas.add(Pregunta.generarAleatoria());
		}
	}
	
	public int getNumJugadores() {
		return this.numJugadores;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}
	
	private boolean addJugador(Jugador jugador) {
		this.jugadores.add(jugador);

		if (this.jugadores.size() == numJugadores) {
			this.jugadores.remove(0);
		}
		
		return true;
	}
	
	public boolean addPersona(String newJugador) {
		if (!this.jugadorEnPartida(newJugador)) {
			return this.addJugador(new Persona(newJugador));
		} else {
			return false;
		}
	}
	
	public boolean addCPU() {
		String nombreCPU = "CPU";
		int numCPU = 1;
		
		while (this.jugadorEnPartida(nombreCPU+numCPU)) {
			numCPU++;
		}
		
		return this.addJugador(new Cpu(nombreCPU+numCPU));
	}
	
	private boolean jugadorEnPartida(String nombreNewJugador) {
		for (Jugador i : this.jugadores) {
			if (i.getNombre().equals(nombreNewJugador)) {
				return true;
			}
		}
		return false;
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
		if (!this.jugadoresMezclados) {
			Collections.shuffle(this.jugadores);
			this.jugadoresMezclados = true;
		}
		return this.nextEnCola(this.jugadores, true);
	}
	
	public boolean isTerminada() {
		return this.preguntas.size() == 0;
	}
	
	public String getPuntuacion() {
		String puntuacion = "";
		
		for (Jugador i : this.jugadores) {
			puntuacion += i.getNombre() + ":" + i.getPuntosPartida() + " ";
		}
		return puntuacion;
	}
	
	//Devuelve un array para controlar el caso de un empate
	public ArrayList<String> getGanador() {
		int highestPuntos = 0;
		
		for (Jugador i : this.jugadores) {
			if (i.getPuntosPartida() > highestPuntos) {
				highestPuntos = i.getPuntosPartida();
			}
		}
		
		for (Jugador i : this.jugadores) {
			if (i.getPuntosPartida() == highestPuntos) {
				Partida.ganadores.add(i.getNombre());
			}
		}
		
		return Partida.ganadores;
	}
	
	public boolean isEmpate() {
		return (Partida.ganadores.size() != 1);
	}
	
}