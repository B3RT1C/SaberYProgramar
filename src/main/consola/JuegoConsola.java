package main.consola;

import java.util.ArrayList;
import java.util.Scanner;

import gestores.Gestor;
import interfaces.Menu;
import jugadores.Jugador;
import util.Consts;

public class JuegoConsola implements Menu {
	private Scanner in = new Scanner(System.in);
	
	public JuegoConsola() {
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
		String opcion = in.nextLine().toUpperCase();
		while (!opcion.matches("[ABCDE]")) {
			System.err.println(Consts.ERROR_OPCION_NO_VALIDA);
			opcion = in.nextLine().toUpperCase();
		}
		
		return opcion;
	}
	
	private void loopPrincipal() {
		String opcion;
		do {
			this.mostrarPrincipal();
			opcion = this.elegirPrincipal();
			switch (opcion) {
				case "A": {
					System.out.println("aaaaa");
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
		/*
		 * a) Ver Jugadores: muestra la lista de jugadores registrados.
		 * b) Añadir jugador: permite añadir al sistema a un nuevo jugador.
		 * c) Eliminar jugador: permite eliminar del sistema a un jugador registrado.
		 * d) Volver: vuelve al menú principal.
		 */
		System.out.println("\n"
				 + "a) Ver Jugadores -> Muestra la lista de jugadores registrados\n"
				 + "b) Añadir jugador -> Permite añadir al sistema un nuevo jugador\n"
				 + "c) Eliminar jugador -> Permite eliminar del sistema a un jugador registrado\n"
				 + "d) Volver -> Vuelve al menú principal\n"
				 + "\n");
	}

	@Override
	public String elegirGestorJugadores() {
		String opcion = in.nextLine().toUpperCase();
		while (!opcion.matches("[ABCD]")) {
			System.err.println(Consts.ERROR_OPCION_NO_VALIDA);
			opcion = in.nextLine().toUpperCase();
		}
		
		return opcion;
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
	
		if (Gestor.jugadores.crearJugador(in.nextLine())) {
			System.out.println(Consts.MENU_SUCCEED);
		
		} else {
			System.out.println(Consts.MENU_ADD_JUGADOR_ERROR);
		}
		System.out.println(Consts.MENU_VOLVER+"\n");
	}
	
	private void removeJugador() {
		System.out.println("\n"+Consts.MENU_REMOVE_JUGADOR);
		System.out.println(Consts.MENU_FORMATEO_NOMBRES);
		
		if (Gestor.jugadores.removeJugador(in.nextLine())) {
			System.out.println(Consts.MENU_SUCCEED);
		
		} else {
			System.out.println(Consts.MENU_REMOVE_JUGADOR_ERROR);
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
		System.out.println("\nIntroduce cualquier valor para volver: ");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarElegirJugador() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Jugador elegirJugador(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mostrarElegirRondas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int elegirRondas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mostrarPregunta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preguntaTerminada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarFinPartida() {
		// TODO Auto-generated method stub
		
	}
	
	private void mostrarContenidoArchivo(ArrayList<String> contenido) {
		for (String i : contenido) {
			System.out.println(i);
		}
	}

}
