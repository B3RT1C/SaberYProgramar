package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Pregunta;

public class Partida {
	private ArrayList<String> ganadores = new ArrayList<>();
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	private int numJugadores;
	private int numRondas;
	private boolean jugadoresMezclados = false;
	
	private static int rondaX;
	
	protected Partida() {}
	
	//Para usar la clase bien hay que llamar al método configurar y luego añadir los jugadores
	public void configurar(int numJugadores, int numRondas) {
		this.reiniciarPartida();
		this.numJugadores = numJugadores;
		this.numRondas = numJugadores;
		this.generarPreguntas(numJugadores*numRondas);
	}
	
	private void reiniciarPartida() {
		this.jugadores.clear();
		this.preguntas.clear();
		this.ganadores.clear();
		rondaX = 1;
	}
	
	private void generarPreguntas(int numPreguntas) {
		for (int i = 0; i < numPreguntas; i++) {
			this.preguntas.add(Pregunta.generarAleatoria());
		}
	}
	
	public int getNumJugadores() {
		return this.numJugadores;
	}
	
//	public ArrayList<Jugador> getJugadores() {
//		return this.jugadores;
//	}
	
	public int getRondaX() {
//		return this.numRondas-(this.preguntas.size()/this.numJugadores)+1;
		rondaX++;
		return rondaX%
	}
	
	private boolean addJugador(Jugador jugador) {
		this.jugadores.add(jugador);

		if (this.jugadores.size() > numJugadores) {
			this.jugadores.remove(0);
		}
		
		return true;
	}
	
	public boolean addPersona(String newJugador) {
		if (!this.jugadorEnPartida(newJugador)) {
			return this.addJugador(new Jugador(newJugador));
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
			if (i.getNombre().equalsIgnoreCase(nombreNewJugador)) {
				return true;
			}
		}
		return false;
	}
	
	private <T> T nextEnCola(ArrayList<T> cola, boolean rotate) {
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
	
	public String getPuntuaciones() {
		String puntuacion = "";
		
		for (Jugador i : this.jugadores) {
			puntuacion += i.getNombre() + ":" + i.getPuntosPartida() + " ";
		}
		return puntuacion;
	}
	
	//Devuelve un array para controlar el caso de un empate
	//Para que se considere hanar has de conseguir almenos 1 punto, para que si juegas tu solo y no consigues nigún punto no cuente como victorio
	public ArrayList<String> getGanador() {
		if (this.ganadores.isEmpty()) {
			int highestPuntos = 0;
			
			for (Jugador i : this.jugadores) {
				if (i.getPuntosPartida() > highestPuntos) {
					highestPuntos = i.getPuntosPartida();
				}
			}
			
			for (Jugador i : this.jugadores) {
				if (i.getPuntosPartida() == highestPuntos && highestPuntos > 0) {
					this.ganadores.add(i.getNombre());
				}
			}			
		}
		
		return this.ganadores;
	}
	
	public boolean isEmpate() {
		return (this.ganadores.size() > 1 && this.numJugadores > 1);
	}
	
}