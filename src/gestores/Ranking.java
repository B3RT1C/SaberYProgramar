package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

import interfaces.EscritorArchivos;
import interfaces.LectorArchivos;
import util.Consts;

public class Ranking implements LectorArchivos, EscritorArchivos {
	private ArrayList<String> ranking = new ArrayList<>();
	private ArrayList<String> nombreJugadores = new ArrayList<>();
	private ArrayList<Integer> partidasGanadasJugadores = new ArrayList<>();

	Ranking() {
		this.verificarExistenciaArchivo();
		this.leerArchivo();
	}
	
	private void verificarExistenciaArchivo() {
		if (!Files.exists(Consts.RANKING_PATH)) {
			try {
				Files.createFile(Consts.RANKING_PATH);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(Consts.MENSAJE_ERROR_CREAR_ARCHIVO);
			}
		}
	}

	public ArrayList<String> getRanking() {
		return this.ranking;
	}
	
	public ArrayList<Integer> getPartidasGanadas() {
		return this.partidasGanadasJugadores;
	}
	
	public ArrayList<String> getNombres() {
		return this.nombreJugadores;
	}
	
	public boolean add(String nombre) {
		nombre = nombre.toUpperCase();
		if (!this.exists(nombre)) {
			this.nombreJugadores.add(nombre);
			this.partidasGanadasJugadores.add(0);
			this.actualizarFicheroRanking();
			return true;
		
		} else {
			return false;
		}
	}
		
	public boolean remove(String nombre) {
		nombre = nombre.toUpperCase();
		if (this.exists(nombre)) {
			int index = this.findIndice(nombre);
			this.nombreJugadores.remove(index);
			this.partidasGanadasJugadores.remove(index);
			this.actualizarFicheroRanking();
			return true;

		} else {
			return false;
		}
	}
	
	public int findIndice(String nombre) {
		nombre = nombre.toUpperCase();
		return this.nombreJugadores.indexOf(nombre);
	}
	
	public boolean exists(String nombre) {
		nombre = nombre.toUpperCase();
		return (this.findIndice(nombre) != -1);
	}
	
	public void partidaGanada(String nombre) {
		int index = this.findIndice(nombre);
		int partidasGanadas = this.partidasGanadasJugadores.get(index);
		this.partidasGanadasJugadores.set(index, partidasGanadas+1);
		this.actualizarFicheroRanking();
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
	private void actualizarFicheroRanking() {
		this.ordenarRanking();
		this.escribirArchivo();
		this.leerArchivo();
	}

	@Override
	public void escribirArchivo() {
		try {
			//Sobreescribe todo el archivo a una cadena sin nada/borra el contenido del archivo
			Files.writeString(Consts.RANKING_PATH, "");
			
			for (int i = 0; i < nombreJugadores.size(); i++) {
				String registro = nombreJugadores.get(i) + " " + partidasGanadasJugadores.get(i) + "\n";
	
					Files.writeString(Consts.RANKING_PATH, registro, StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	@Override
	public void leerArchivo() {
		ArrayList<String> aux = new ArrayList<>();
		try {
			aux = (ArrayList<String>)Files.readAllLines(Consts.RANKING_PATH);
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