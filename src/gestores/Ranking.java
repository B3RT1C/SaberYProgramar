package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

import util.Consts;

public class Ranking {
	private ArrayList<String> ranking = new ArrayList<>();
	private ArrayList<String> nombreJugadores = new ArrayList<>();
	private ArrayList<Integer> partidasGanadasJugadores = new ArrayList<>();

	protected Ranking() {
		this.verificarExistenciaArchivo(Consts.PATH_RANKING);
		this.leerArchivo();
	}
	
	private void verificarExistenciaArchivo(Path path) {
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(Consts.MENSAJE_ERROR_CREAR_ARCHIVO);
			}
		}
	}
	
	public ArrayList<Integer> getPartidasGanadas() {
		return this.partidasGanadasJugadores;
	}
	
	public ArrayList<String> getNombres() {
		return this.nombreJugadores;
	}
	
	public ArrayList<String> getRanking() {
		return this.ranking;
	}
	
	public boolean crearJugador(String jugador) {
		jugador = jugador.toUpperCase();
		if (!this.existsJugador(jugador)) {
			this.nombreJugadores.add(jugador);
			this.partidasGanadasJugadores.add(0);
			this.actualizarRanking();
			return true;
		
		} else {
			return false;
		}
	}
		
	public boolean removeJugador(String jugador) {
		jugador = jugador.toUpperCase();
		if (this.existsJugador(jugador)) {
			int index = this.findIndiceJugador(jugador);
			this.nombreJugadores.remove(index);
			this.partidasGanadasJugadores.remove(index);
			this.actualizarRanking();
			return true;

		} else {
			return false;
		}
	}
	
	public int findIndiceJugador(String nombre) {
		nombre = nombre.toUpperCase();
		return this.nombreJugadores.indexOf(nombre);
	}
	
	public boolean existsJugador(String nombre) {
		nombre = nombre.toUpperCase();
		return this.nombreJugadores.contains(nombre);
	}
	
	public void partidaGanada(String nombre) {
		int index = this.findIndiceJugador(nombre);
		int partidasGanadas = this.partidasGanadasJugadores.get(index);
		this.partidasGanadasJugadores.set(index, partidasGanadas+1);
		this.actualizarRanking();
	}
	
	private void ordenarRanking() {
		ArrayList<Integer> partidasGanadas = this.getValoresDiferentesPartidasGanadas();
		
		ArrayList<String> auxNombresJugadores = new ArrayList<>();
		ArrayList<Integer> auxPartidasGanadasJugadores = new ArrayList<>();
		
		for (int i : partidasGanadas) {
			for (int j = 0; j < this.partidasGanadasJugadores.size(); j++) {
				
				if (i == this.partidasGanadasJugadores.get(j)) {
					auxNombresJugadores.add(this.nombreJugadores.get(j));
					auxPartidasGanadasJugadores.add(this.partidasGanadasJugadores.get(j));
				}
			}
		}
		
		this.nombreJugadores = auxNombresJugadores;
		this.partidasGanadasJugadores = auxPartidasGanadasJugadores;
	}
	
	/*Devuelve sin repeticiones cada valor que aparece en partidasGanadasJugadores ordenado de mayor a menor
	 * ej: {6,3,3,8,1} -> devuelve {8,6,3,1}
	*/
	private ArrayList<Integer> getValoresDiferentesPartidasGanadas() {
		ArrayList<Integer> valoresDiferentes = new ArrayList<>();
		
		for (int i : partidasGanadasJugadores) {
			if (!valoresDiferentes.contains(i)) {
				valoresDiferentes.add(i);
			}
		}
		
		Collections.sort(valoresDiferentes);
		
		return valoresDiferentes;
	}
	
	//Se llama a este método cada vez que se llama a partidaGanada o se crea o borra un jugador
	public void actualizarRanking() {
		this.ordenarRanking();
		this.escribirArchivo();
	}

	private void escribirArchivo() {
		try {
			//Sobreescribe todo el archivo a una cadena sin nada/borra el contenido del archivo
			Files.writeString(Consts.PATH_RANKING, "");
			
			for (int i = 0; i < nombreJugadores.size(); i++) {
				String registro = nombreJugadores.get(i) + " " + partidasGanadasJugadores.get(i) + "\n";
				Files.writeString(Consts.PATH_RANKING, registro, StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	private void leerArchivo() {
		ArrayList<String> aux = new ArrayList<>();
		try {
			aux = (ArrayList<String>)Files.readAllLines(Consts.PATH_RANKING);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_LEER_ARCHIVO);
		}
		
		for (String i : aux) {
			String[] datosJugador = i.split(" ");

			/*Se escriben promero el número de partidas ganadas para que si salta un indexOutOfBounds o un
			 * numberFormatException que se capture la excepción y no se guarde en los ArrayLists
			 * Así el programa continua aunque existan datos con el formato incorrecto en el arhivo,
			 * este caso no se puede dar a menos que el usuario cambie manualmente el ranking
			*/
			try {
				this.partidasGanadasJugadores.add(Integer.valueOf(datosJugador[1]));
				this.nombreJugadores.add(datosJugador[0]);				
				this.ranking.add(i);
			} catch (Exception e) {
			}
		}
	}	
}