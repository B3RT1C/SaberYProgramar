package gestores;

import java.util.ArrayList;
import java.util.Collections;

import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Pregunta;
/**
 * Clase encargada de gestionar los jugadores y preguntas de la partida, proporciona herramientas para facilitar la gestion de la partida
 */
public class Partida {
	/**
	 * ArrayList<String> en el cual contendra los ganadores de la partida
	 */
	private ArrayList<String> ganadores = new ArrayList<>();
	/**
	 * ArrayList<Jugador> en el cual se almacenan los jugadores de la partida
	 */
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	/**
	 * ArrayList<Pregunta> en el cual se almacenan las preguntas de la partida
	 */
	private ArrayList<Pregunta> preguntas = new ArrayList<>();
	
	/**
	 * Numero de jugadores
	 */
	private int numJugadores;
	/**
	 * Variable que almacena true si los jugadores han sido mezclados
	 */
	private boolean jugadoresMezclados = false;
	/**
	 * Almacena el turno actual en el que se encuentra la partida, tras cada pregunta turnoX aumente en 1
	 */
	private int turnoX;
	/**
	 * Constructor de la clase
	 */
	protected Partida() {}
	
	/**
	 * Inicializa numJugadores y las preguntas de la partida,
	 * llama al metodo reiniciarPartida(),
	 * este metodo debe ser llamada antes de añadir los jugadores a la partida
	 * @param numJugadores Cantidad de jugadores totales que tendra la partida
	 * @param numRondas Cantidad de rondas que tendra la partida
	 * @see reiniciarPartida()
	 */
	public void configurar(int numJugadores, int numRondas) {
		this.reiniciarPartida();
		this.numJugadores = numJugadores;
		this.generarPreguntas(numJugadores*numRondas);
	}
	/**
	 * Establece la partida a sus valores por defecto o iniciales para poder iniciar una nuevo partida sin errores ni informacion no deseada
	 */
	private void reiniciarPartida() {
		this.jugadores.clear();
		this.preguntas.clear();
		this.ganadores.clear();
		this.turnoX = 0;
	}
	/**
	 * Añade las preguntas al ArrayList preguntas
	 * @param numPreguntas Numero de preguntas a añadir, numPreguntas = (numJugadores*nunRondas)
	 * @see preguntas
	 */
	private void generarPreguntas(int numPreguntas) {
		for (int i = 0; i < numPreguntas; i++) {
			this.preguntas.add(Pregunta.generarAleatoria());
		}
	}
	/**
	 * Indica si la ronda ha terminado
	 * @return true si ha terminado la ronda, false si no ha terminado
	 */
	public boolean isFinRonda() {
		return this.turnoX%this.jugadores.size() == 0;
	}
	
	public int getNumJugadores() {
		return this.numJugadores;
	}
	
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	/*
	 * Añade jugadores a la partida, si se añaden mas jugadores de los que se indican en numJugadores, 
	 * los mas antiguos seran eliminados para hacer sitio a los nuevos
	 * @param jugador Intancio de Jugador a añadir a la partida
	 */
	private void addJugador(Jugador jugador) {
		this.jugadores.add(jugador);

		if (this.jugadores.size() > numJugadores) {
			this.jugadores.remove(0);
		}
	}
	
	/**
	 * Añade a la partida un jugador humano (instancia de Jugador) 
	 * @param newJugador Nombre del jugador a añadir
	 * @return true si se ha añadido con exito el jugador, false si el jugador ya existia en la partida
	 */
	public boolean addPersona(String newJugador) {
		if (!this.jugadorEnPartida(newJugador)) {
			this.addJugador(new Jugador(newJugador));
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Añade a la partida un jugador cpu (instancia de CPU),
	 * genera nombres secuencuales para las CPUs (CPU1, CPU2, CPU3, etc...)
	 * @return
	 */
	public void addCPU() {
		String nombreCPU = "CPU";
		int numCPU = 1;
		
		while (this.jugadorEnPartida(nombreCPU+numCPU)) {
			numCPU++;
		}
		this.addJugador(new Cpu(nombreCPU+numCPU));
	}
	/**
	 * Permite saber si un jugador ya se encuentra en la partida
	 * @param nombre Nombre del jugador del que se quiere verificar su existencia en la partida
	 * @return true si se encuentra en la partida, false si se encuentra en la partida
	 */
	private boolean jugadorEnPartida(String nombre) {
		for (Jugador i : this.jugadores) {
			if (i.getNombre().equalsIgnoreCase(nombre)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Funciona a modo de cola dependiendo de la opcion
	 * @param <T> Tipo generico de dato, usado para no tener que operar con un ArrayList<Object> y tener que castear el return a su clase original
	 * @param cola ArrayList<T> el cual se quiere usar a modo de cola
	 * @param rotate false para que el metodo se comporte igual que una cola (FIFO), 
	 * true para que cuando el primer elemento de la cola sea sacado de ella, se vuelva a añadir al final para hacer un bucle que repita los valores
	 * @return Devuelve el primer valor que se encuentre en la cola
	 */
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
	/**
	 * Usa el ArrayList preguntas a como de cola usando el metodo nextEnCola(rotate = false)
	 * @see preguntas
	 * @see nextEnCola
	 * @return Primera pregunta que se encuntre en la cola
	 */
	public Pregunta nextPregunta() {
		this.turnoX++;
		return this.nextEnCola(this.preguntas, false);
	}
	/**
	 * Usa el ArrayList jugadores a como de cola en bucle usando el metodo nextEnCola(rotate = true)
	 * @see jugadores
	 * @see nextEnCola
	 * @return Primer jugador que se encuntre en la cola
	 */
	public Jugador nextJugador() {
		if (!this.jugadoresMezclados) {
			Collections.shuffle(this.jugadores);
			this.jugadoresMezclados = true;
		}
		return this.nextEnCola(this.jugadores, true);
	}
	/**
	 * Permite saber si la pertida ha terminado
	 * @return true si la partida ha terminado y no hay mas preguntas, false si la partida ahun no ha terminado
	 */
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
	/**
	 * Permite saber si una partida ha terminado con un empate
	 * @return true si hay mas de 1 jugador con el maximo de puntos de la partida, false si hay 1 jugador con el maximo de puntos en la partida
	 */
	public boolean isEmpate() {
		return (this.ganadores.size() > 1 && this.numJugadores > 1);
	}
	
}