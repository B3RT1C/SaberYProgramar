package main.consola;

import java.util.Scanner;

import gestores.Gestor;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Pregunta;
import util.Consts;

/**
 * Clase nucleo donde se juntan todos los metodos del resto de clases para crear un bucle de juego en la consola
 */
public class JuegoConsola {
	/**
	 * Usado para recoger imputs del usuario por consola
	 */
	static Scanner in = new Scanner(System.in);
	/**
	 * Instancia de la clase VisualesConsola, usado para mostrar por pantalla mensajes predefinidos
	 */
	private VisualesConsola visuales = new VisualesConsola();
	/**
	 * Llama al metodo que inicia el bucle principal de juego
	 * @see loopPrincipal
	 */
	public JuegoConsola() {
		System.out.println(Consts.MENU_TITLE);
		this.loopPrincipal();
		JuegoConsola.in.close();
	}
	/**
	 * Permite al usuario elegir entre las siguientes opciones A, B, C, D, E (discrimina mayuscula y minusculas) 
	 * @return Opcion validada
	 */
	public String elegirPrincipal() {
		return this.elegirOpcion("[ABCDE]", true);
	}
	/**
	 * Loop principal de juego, muestra y permite elegir entre varios menus secundarios
	 */
	private void loopPrincipal() {
		String opcion;
		do {
			this.visuales.mostrarPrincipal();
			opcion = this.elegirPrincipal();
			switch (opcion) {
				case "A": {
					this.partidaStartup();
					this.loopJuego();
					this.visuales.mostrarFinPartida();
					this.partidaCloseup();
					break;
				
				} case "B": {
					this.visuales.mostrarRanking();
					break;
				
				} case "C": {
					this.visuales.mostrarHistorico();
					break;
				
				} case "D": {
					this.loopGestorjugadores();
					break;
		
				} case "E": {
					System.out.println(Consts.MENU_SALIR);
					break;
				}
			}
		} while (!opcion.equals("E"));
	}
	/*
	 * Permite al usuario elegir entre las siguientes opciones A, B, C, D (discrimina mayuscula y minusculas) 
	 */
	public String elegirGestorJugadores() {
		return this.elegirOpcion("[ABCD]", true);
	}
	/*
	 * Loop de gestion de jugadores, muestra y permite elegir entre varios menus que permiten hacer ciertas operaciones con los jugadores
	 */
	private void loopGestorjugadores() {
		String opcion;
		do {
			this.visuales.mostrarGestorJugadores();
			opcion = this.elegirGestorJugadores();
			switch (opcion) {
				case "A": {
					this.visuales.mostrarJugadores();
					break;
				
				} case "B": {
					this.visuales.mostrarElegirJugador();
					this.addJugador();
					break;
				
				} case "C": {
					this.visuales.mostrarEliminarJugador();
					this.removeJugador();
					break;
					
				} case "D": {
					System.out.println(Consts.MENU_VOLVER);
					break;
				}
			}
		} while (!opcion.equals("D"));
	}
	/**
	 * Permite añadir un jugador al sistema, se pedira un String nombre al usuario para crear el jugador nuevo, 
	 * si el nombre está en uso mostrará un error y no se creara el jugador (discrimina las mayusculas y minusculas)
	 */
	private void addJugador() {
		String nombre = in.nextLine();
		if (Gestor.jugadores.crearJugador(nombre)) {
			System.out.println(Consts.MENU_SUCCEED);
			
		} else {
			System.out.println(Consts.MENU_ADD_JUGADOR_ERROR);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
		}
		System.out.println(Consts.MENU_VOLVER+"\n");
	}
	/**
	 * Permite eliminar un jugador del sistema, se pedira un String nombre al usuario para borrar al jugador con ese nombre del sistema,
	 * si el jugador no existe en el sistema se mostrará un error (discrimina las mayusculas y minuscula)
	 */
	private void removeJugador() {
		String nombre = in.nextLine().toUpperCase();
		if (Gestor.jugadores.removeJugador(nombre)) {
			System.out.println(Consts.MENU_SUCCEED);
			
		} else {
			System.out.println(Consts.MENU_REMOVE_JUGADOR_ERROR);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_REMOVE_JUGADOR_ERROR);
		}
		System.out.println(Consts.MENU_VOLVER+"\n");
	}
	/**
	 * Permite elegir el numero maximo de jugadores de la partida (1 min - 4 max), se elegiran tambien de ese maximo de jugadores cuantos seran humanos y cuantos CPUs
	 * @return Un array de ints con dos posicione 0 = numJugadores, 1 = numHumanos (numCPUs será la resta de esos dos números)
	 */
	public int[] elegirCantidadJugadores() {
		int numJugadores = Integer.valueOf(this.elegirOpcion("[1234]", true));
		
		System.out.println("De esos " + numJugadores + " jugadores cuantos quieres que sean humanos, el resto serán CPUs");
		int numHumanos = Integer.valueOf(this.elegirOpcion("[0-"+numJugadores+"]", true));
		
		System.out.println("Jugarán " + numHumanos + " humano/s y " + (numJugadores-numHumanos) + " CPU/s");
		
		return new int[]{numJugadores, numHumanos};
	}
	/**
	 * Pide al usuario un String nombre de un jugador vara verificar si existe, si no existe se dara la opcion de crearlo en el momento,
	 * @return Si el jugador existe o es creado en el momento devuelve el nombre del jugador, si el jugador no existe y no se crea en el momento devuelve null
	 */
	public String elegirJugador() {
		String nombre = this.elegirOpcion("CPU\\d*", false);
		
		if (!Gestor.jugadores.existsJugador(nombre)) {
			System.out.println(Consts.MENU_ADD_JUGADOR_INEXISTENTE(nombre)+" Y/N");
			String opcion = this.elegirOpcion("[YN]", true);
			
			if (opcion.equals("Y")) {
				Gestor.jugadores.crearJugador(nombre);
			
			} else if (opcion.equals("N")) {
				return null;
			}
		}
		
		return nombre;
	}
	/**
	 * Pemite elegir al usuario entre un número predeterminado de rondas (3, 5, 10 ,20)
	 * @return El numero de rondas seleccionado, si se hace una seleccion con un valor no valido se devuelven 3 rondas
	 */
	public int elegirRondas() {
		String opcion = this.elegirOpcion("[ABCD]", true);

		switch (opcion) {
		case "A": {
			return 3;

		} case "B": {
			return 5;
		
		} case "C": {
			return 10;
		
		} case "D": {
			return 20;
		
		} default: {
			return 3;
		}
	}
	}
	
	/**
	 * Permite dado un patron crear un bucle infinito hasta que se introduca por consola un valor que cunpla con el patron
	 * @param patron Patron que ha de cumplirse
	 * @param toMatch true = indica que el bucle seguira mientras el valor introducido no coincida con el patron,
	 * 				  false = indica que el bicle seguira mientras el valor introducido coincida con el patron
	 * @return La opcion que cumple o no con el patron, dependiendo del valor de toMatch
	 */
	//toMatch = true para que pida valores nuevos hasta cumplir el patrón y toMatch = false para que pida valores nuevos hasta no cumplir el patrón 
	private String elegirOpcion(String patron, boolean toMatch) {
		String opcion = in.nextLine().toUpperCase();
		while (toMatch? !opcion.matches(patron) : opcion.matches(patron)) {
			System.err.println(Consts.ERROR_OPCION_NO_VALIDA);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.ERROR_OPCION_NO_VALIDA);
			opcion = in.nextLine().toUpperCase();
		}
		return opcion;
	}
	/**
	 * Se llaman a los metodos para pedir valores necesarios para la inicializacion de la partida (numJugadores, numRondas),
	 * se añaden los jugadores seleccionados a la partida y se generan las preguntas
	 */
	private void partidaStartup() {
		visuales.mostrarElegirRondas();
		int numRondas = this.elegirRondas();
		
		visuales.mostrarElegirCantidadJugadores();
		int[] infoNumJugadores = this.elegirCantidadJugadores();
		int numJugadores = infoNumJugadores[0];
		int numHumanos = infoNumJugadores[1];
		int numCpu = infoNumJugadores[0]-infoNumJugadores[1];
		
		Gestor.partida.configurar(numJugadores, numRondas);
		
		while (numHumanos > 0) {
			this.visuales.mostrarElegirJugadorPartida();
			String jugador = this.elegirJugador();
			
			if (jugador != null) {
				while (!Gestor.partida.addPersona(jugador)) {
					System.err.println(Consts.ERROR_JUGADOR_REPETIDO);
					Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.ERROR_JUGADOR_REPETIDO);
					jugador = this.elegirJugador();
				}				
				numHumanos--;
			}
		}
		
		for (int i = 0; i < numCpu; i++) {
			Gestor.partida.addCPU();
		}
		
		Gestor.log.escribirArchivo(Consts.LOG_INICIO_PARTIDA(numHumanos, numCpu));
	}
	/**
	 * Loop en el que se juega una partida tras ser creada
	 */
	private void loopJuego() {
		while (!Gestor.partida.isTerminada()) {
			Jugador jugador = Gestor.partida.nextJugador();
			Pregunta pregunta = Gestor.partida.nextPregunta();
			
			System.out.println("\nTurno de " + jugador.getNombre());
			this.visuales.mostrarPregunta(pregunta);

			if (jugador instanceof Cpu) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
			
			String respuesta = this.elegirRespuesta(jugador, pregunta);

			this.visuales.mostrarFinPregunta(jugador, respuesta, pregunta);
			this.visuales.mostrarFinRonda();
		}
	}
	/**
	 * Maneja la accion de responder una pregunta, si el jugador es humano tendra que responder por teclado, si el jugador es CPU se generara una respuesta automatica
	 * @param jugador Jugador al que le toca responder la pregunta
	 * @param pregunta Pregunta a responder
	 * @return Respuesta a la pregunta
	 */
	private String elegirRespuesta(Jugador jugador, Pregunta pregunta) {
		return jugador instanceof Cpu? ((Cpu)jugador).generarRespuesta(pregunta) : in.nextLine();
	}
	/**
	 * Llama a todos los metodos necesarios para finalizar una partida, se actualiza el historial de partidas y el ranking
	 */
	private void partidaCloseup() {
		Gestor.historial.escribir(Gestor.partida.getPuntuaciones());
		
		for (Jugador i : Gestor.partida.getJugadores()) {
			Gestor.jugadores.actualizarPuntosRanking(i.getNombre(), i.getPuntosPartida());
		}
		Gestor.jugadores.actualizarRanking();
		
		Gestor.log.escribirArchivo(Consts.LOG_FIN_PARTIDA(Gestor.partida.getNumJugadores(), Gestor.partida.getGanador()));
	}
}
