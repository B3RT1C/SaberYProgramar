package main.consola;

import java.util.ArrayList;

import gestores.Gestor;
import interfaces.Menu;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Ingles;
import preguntas.Pregunta;
import util.Consts;

/**
 * Esta clase maneja todo lo relacionado con mostrar menus e informacion por consola
 */
public class VisualesConsola implements Menu {
	/**
	 * Constructor de la clase, visibilidad asignada a solo el paquete para solo poder ser insatanciado en la clase JuegoConsola
	 * @see JuegoConsola
	 */
	VisualesConsola() {}
	
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
	public void mostrarGestorJugadores() {
		System.out.println("\n"
				 + "a) Ver Jugadores -> Muestra la lista de jugadores registrados\n"
				 + "b) Añadir jugador -> Permite añadir al sistema un nuevo jugador\n"
				 + "c) Eliminar jugador -> Permite eliminar del sistema a un jugador registrado\n"
				 + "d) Volver -> Vuelve al menú principal\n"
				 + "\n");
	}

	@Override
	public void mostrarJugadores() {
		this.mostrarContenidoArchivo(Gestor.jugadores.getNombres());
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
		JuegoConsola.in.nextLine();
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
	public void mostrarElegirJugador() {
		System.out.println("\n"+Consts.MENU_ADD_JUGADOR);
		System.out.println(Consts.MENU_FORMATEO_NOMBRES);
	}	

	@Override
	public void mostrarEliminarJugador() {
		System.out.println("\n"+Consts.MENU_REMOVE_JUGADOR);
		System.out.println(Consts.MENU_FORMATEO_NOMBRES);
	}

	@Override
	public void mostrarElegirRondas() {
		System.out.println("\n"
						 + "Cuantas rondas quieres jugar\n"
						 + "a) Partida rápida: 3 rondas\n"
						 + "b) Partida corta: 5 rondas\n"
						 + "c) Partida normal: 10 rondas\n"
						 + "d) Partida larga: 20 rondas\n"
						 + "\n");
	}
	
	@Override
	public void mostrarElegirJugadorPartida() {
		this.mostrarElegirJugador();
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
	public void mostrarFinPregunta(Jugador jugador, String respuesta, Pregunta pregunta) {
		if (jugador instanceof Cpu) {
			System.out.println(respuesta);
		}
		
		if (jugador.responder(respuesta , pregunta)) {
			System.out.println(Consts.PREGUNTA_ACERTADA+"\n");
		
		} else {
			System.out.println(Consts.PREGUNTA_FALLADA(pregunta) + "\n");
		}
		
		if (jugador instanceof Cpu) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	@Override
	public void mostrarFinRonda() {
		if (Gestor.partida.isFinRonda() && !Gestor.partida.isTerminada()) {
			System.out.println("\nFin de la ronda: \n"+Gestor.partida.getPuntuaciones()+"\n");
		}
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

}