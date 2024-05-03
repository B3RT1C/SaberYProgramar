package main.consola;

import java.util.ArrayList;
import java.util.Scanner;

import gestores.Gestor;
import interfaces.Menu;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Ingles;
import preguntas.Pregunta;
import util.Consts;

public class JuegoConsola implements Menu {
	private Scanner in = new Scanner(System.in);
	
	public JuegoConsola() {
		System.out.println(Consts.MENU_TITLE);
		this.loopPrincipal();
	}
	
	@Override
	public void mostrarPrincipal() {
		System.out.println("\n"
						 + "a) Jugar Partida -> Permite jugar una nueva partida\n"
						 + "b) Ranking -> Muestra el ranking de los mejores jugadores\n"
						 + "c) Histórico -> Muestra el histórico de partidas\n"
						 + "d) Jugadores -> Permite acceder a un submenú de gestión de jugadores\n"
						 + "e) Salir -> Termina el programa"
						 + "\n");
	}

	@Override
	public String elegirPrincipal() {
		return this.elegirOpcion("[ABCDE]", true);
	}
	
	private void loopPrincipal() {
		String opcion;
		do {
			this.mostrarPrincipal();
			opcion = this.elegirPrincipal();
			switch (opcion) {
				case "A": {
					this.partidaStartup();
					this.loopJuego();
					this.mostrarFinPartida();
					this.partidaCloseup();
					break;
				
				} case "B": {
					this.mostrarRanking();
					break;
				
				} case "C": {
					this.mostrarHistorico();
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

	@Override
	public void mostrarGestorJugadores() {
		System.out.println("\n"
				 + "a) Ver Jugadores -> Muestra la lista de jugadores registrados\n"
				 + "b) Añadir jugador -> Permite añadir al sistema un nuevo jugador\n"
				 + "c) Eliminar jugador -> Permite eliminar del sistema a un jugador registrado\n"
				 + "d) Volver -> Vuelve al menú principal\n"
				 + "\n");
	}

	@Override
	public String elegirGestorJugadores() {
		return this.elegirOpcion("[ABCD]", true);
	}
	
	private void loopGestorjugadores() {
		String opcion;
		do {
			this.mostrarGestorJugadores();
			opcion = this.elegirGestorJugadores();
			switch (opcion) {
				case "A": {
					this.mostrarContenidoArchivo(Gestor.jugadores.getNombres());
					break;
				
				} case "B": {
					this.addJugador();
					break;
				
				} case "C": {
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
		System.out.println("\n"+Consts.MENU_ADD_JUGADOR);
		System.out.println(Consts.MENU_FORMATEO_NOMBRES);
	
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
		System.out.println("\n"+Consts.MENU_REMOVE_JUGADOR);
		System.out.println(Consts.MENU_FORMATEO_NOMBRES);
		
		String nombre = in.nextLine().toUpperCase();
		if (Gestor.jugadores.removeJugador(nombre)) {
			System.out.println(Consts.MENU_SUCCEED);
			
		
		} else {
			System.out.println(Consts.MENU_REMOVE_JUGADOR_ERROR);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_REMOVE_JUGADOR_ERROR);
		}
		System.out.println(Consts.MENU_VOLVER+"\n");
	}

	@Override
	public void mostrarHistorico() {
		System.out.println();
		this.mostrarContenidoArchivo(Gestor.historial.getHistorial());
		this.volver();
		System.out.println();
	}
	
	@Override
	public void volver() {
		System.out.println("\nPulsa enter o introduce cualquier valor para volver: ");
		this.in.nextLine();
		System.out.println(Consts.MENU_VOLVER);
	}

	@Override
	public void mostrarRanking() {
		System.out.println();
		this.mostrarContenidoArchivo(Gestor.jugadores.getRanking());
		this.volver();
		System.out.println();
	}

	@Override
	public void mostrarElegirCantidadJugadores() {
		System.out.println("Dime cuantos jugadores quieres que existan en la partida (1-4)");
	}
	
	@Override
	public int[] elegirCantidadJugadores() {
		int numJugadores = Integer.valueOf(this.elegirOpcion("[1234]", true));
		
		System.out.println("De esos " + numJugadores + " jugadores cuantos quieres que sean humanos, el resto serán CPUs");
		int numHumanos = Integer.valueOf(this.elegirOpcion("[0-"+numJugadores+"]", true));
		
		System.out.println("Jugarán " + numHumanos + " humano/s y " + (numJugadores-numHumanos) + " CPU/s");
		
		return new int[]{numJugadores, numHumanos};
	}

	@Override
	public void mostrarElegirJugador() {
		System.out.println("Dime el nombre de un jugador para añadirlo a la partida");
	}

	@Override
	public String elegirJugador() {
		String nombre = this.elegirOpcion("CPU\\d*", false);
		
		if (!Gestor.jugadores.existsJugador(nombre)) {
			System.out.println("El jugador: " + nombre.toUpperCase() + " no existe en el sistema, ¿quieres añadirlo? Y/N");
			String opcion = this.elegirOpcion("[YN]", true);
			
			if (opcion.equals("Y")) {
				Gestor.jugadores.crearJugador(nombre);
			
			} else if (opcion.equals("N")) {
				return null;
			}
		}
		
		return nombre;
	}

	@Override
	public void mostrarElegirRondas() {
		System.out.println("\n"
						 + "Cuantas rondas quieres jugar\n"
						 + "a) Partida rápida: 3 rondas\n"
						 + "b) Partida corta: 5 rondas\n"
						 + "c) Partida normal: 10 rondas\n"
						 + "d) PArtida larga: 20 rondas\n"
						 + "\n");
	}

	@Override
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
	
	@Override
	public void mostrarPregunta(Pregunta pregunta) {
		System.out.println(pregunta.getEnunciado());
		if (pregunta instanceof Ingles) {
			char letra = 'a';
			for (String i : ((Ingles) pregunta).getOpciones()) {
				System.out.println(letra + ") " + i);
				letra++;
			}
		}
	}

	@Override
	public void preguntaTerminada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFinPartida() {
		System.out.println("Partida finalizada");
		if (Gestor.partida.isEmpate()) {
			System.out.println("¡Es un empate!");
		}
		System.out.println(Gestor.partida.getPuntuaciones());
	}
	
	private void mostrarContenidoArchivo(ArrayList<String> contenido) {
		if (contenido.isEmpty()) {
			System.out.println(Consts.MENU_CERO_CONTENIDO);

		} else {
			for (String i : contenido) {
				System.out.println(i);
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
		this.mostrarElegirRondas();
		int numRondas = this.elegirRondas();
		
		this.mostrarElegirCantidadJugadores();
		int[] infoNumJugadores = this.elegirCantidadJugadores();
		int numJugadores = infoNumJugadores[0];
		int numHumanos = infoNumJugadores[1];
		int numCpu = infoNumJugadores[0]-infoNumJugadores[1];
		
		Gestor.partida.configurar(numJugadores, numRondas);
		
		while (numHumanos > 0) {
			this.mostrarElegirJugador();
			String jugador = this.elegirJugador();
			
			if (jugador != null) {
				Gestor.partida.addPersona(jugador);
				numHumanos--;
			}
		}
		
		for (int i = 0; i < numCpu; i++) {
			Gestor.partida.addCPU();
		}
		
		Gestor.log.escribirArchivo(Consts.LOG_INICIO_PARTIDA(numHumanos, numCpu));
	}
	
	private void loopJuego() {
		int ronda = 1;
		int turno = 0;
		while (!Gestor.partida.isTerminada()) {
			Jugador jugador = Gestor.partida.nextJugador();
			Pregunta pregunta = Gestor.partida.nextPregunta();
			
			if (turno == 0) {
				System.out.println("\nRonda " + ronda);
				ronda++;
			}
			turno = (turno+1)%Gestor.partida.getNumJugadores();
			
			System.out.println("\nTurno de " + jugador.getNombre());
			this.mostrarPregunta(pregunta);

			if (jugador instanceof Cpu) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
			
			String respuestaCPU = "";
			if (jugador instanceof Cpu) {
				respuestaCPU = ((Cpu)jugador).generarRespuesta(pregunta);
				System.out.println(respuestaCPU);
			}
			if (jugador.responder(jugador instanceof Cpu? respuestaCPU : in.nextLine(), pregunta)) {
				System.out.println("¡Pregunta acertada! +1 punto\n");
			
			} else {
				System.out.println("Pregunta fallada\n"
								 + "Respuesta correcta:" + pregunta.getSolucion() + "\n");
			}
			
			if (jugador instanceof Cpu) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	private void partidaCloseup() {
		Gestor.historial.escribir(Gestor.partida.getPuntuaciones());
		
		for (String i : Gestor.partida.getGanador()) {
			Gestor.jugadores.partidaGanada(i);
		}
		
		Gestor.jugadores.actualizarRanking();
		Gestor.log.escribirArchivo(Consts.LOG_FIN_PARTIDA(Gestor.partida.getNumJugadores(), Gestor.partida.getGanador()));
	}
}
