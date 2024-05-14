package main.consola;

import java.util.Scanner;

import gestores.Gestor;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Pregunta;
import util.Consts;

public class JuegoConsola {
	static Scanner in = new Scanner(System.in);
	private VisualesConsola visuales = new VisualesConsola();
	
	public JuegoConsola() {
		System.out.println(Consts.MENU_TITLE);
		this.loopPrincipal();
		JuegoConsola.in.close();
	}
	
	public String elegirPrincipal() {
		return this.elegirOpcion("[ABCDE]", true);
	}
	
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

	public String elegirGestorJugadores() {
		return this.elegirOpcion("[ABCD]", true);
	}
	
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

	public int[] elegirCantidadJugadores() {
		int numJugadores = Integer.valueOf(this.elegirOpcion("[1234]", true));
		
		System.out.println("De esos " + numJugadores + " jugadores cuantos quieres que sean humanos, el resto serán CPUs");
		int numHumanos = Integer.valueOf(this.elegirOpcion("[0-"+numJugadores+"]", true));
		
		System.out.println("Jugarán " + numHumanos + " humano/s y " + (numJugadores-numHumanos) + " CPU/s");
		
		return new int[]{numJugadores, numHumanos};
	}

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
		}
	}
	
	private String elegirRespuesta(Jugador jugador, Pregunta pregunta) {
		return jugador instanceof Cpu? ((Cpu)jugador).generarRespuesta(pregunta) : in.nextLine();
	}
	
	private void partidaCloseup() {
		Gestor.historial.escribir(Gestor.partida.getPuntuaciones());
		
		for (Jugador i : Gestor.partida.getJugadores()) {
			Gestor.jugadores.actualizarPuntosRanking(i.getNombre(), i.getPuntosPartida());
		}
		Gestor.jugadores.actualizarRanking();
		
		Gestor.log.escribirArchivo(Consts.LOG_FIN_PARTIDA(Gestor.partida.getNumJugadores(), Gestor.partida.getGanador()));
	}
}
