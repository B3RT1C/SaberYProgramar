package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

import interfaces.EscritorArchivos;
import interfaces.LectorArchivos;

public class Ranking implements LectorArchivos, EscritorArchivos{
	private ArrayList<String> ranking = new ArrayList<>();
	private ArrayList<String> nombreJugadores = new ArrayList<>();
	private ArrayList<Integer> partidasGanadasJugadores = new ArrayList<>();

	public Ranking() {
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
		this.actualizarFicheroRanking();
		return this.ranking;
	}
	
	public ArrayList<Integer> getPartidasGanadasJugadores() {
		this.actualizarFicheroRanking();
		return this.partidasGanadasJugadores;
	}
	
	public ArrayList<String> getNombreJugadores() {
		this.actualizarFicheroRanking();
		return this.nombreJugadores;
	}
	
	public boolean addJugador(String nombre) {
		if (!this.jugadorExists(nombre)) {
			this.nombreJugadores.add(nombre);
			this.partidasGanadasJugadores.add(0);
			return true;
		
		} else {
			return false;
		}
	}
		
	public boolean removeJugador(String nombre) {
		if (this.jugadorExists(nombre)) {
			int index = this.findIndiceJugador(nombre);
			this.nombreJugadores.remove(index);
			this.partidasGanadasJugadores.remove(index);
			return true;

		} else {
			return false;
		}
	}
	
	public int findIndiceJugador(String nombre) {
		return this.nombreJugadores.indexOf(nombre);
	}
	
	public boolean jugadorExists(String nombre) {
		return (this.findIndiceJugador(nombre) != -1);
	}
	
	public void partidaGanada(String nombre) {
		int index = this.findIndiceJugador(nombre);
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
	
	//Se llama a este método cada vez que se llama a partidaGanada o algún get público
	private void actualizarFicheroRanking() {
		this.ordenarRanking();
		this.escribirArchivo();
		this.leerArchivo();
	}

	@Override
	public void escribirArchivo() {
		try {
			Files.writeString(Consts.RANKING_PATH, "");
			
			for (int i = 0; i < nombreJugadores.size(); i++) {
				String registro = nombreJugadores.get(i) + " " + partidasGanadasJugadores.get(i);
	
					Files.writeString(Consts.RANKING_PATH, registro, StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	@Override
	public void leerArchivo() {
		try {
			this.ranking = (ArrayList<String>)Files.readAllLines(Consts.RANKING_PATH);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_LEER_ARCHIVO);
		}
		
		for (String i : this.ranking) {
			String[] datosJugador = i.split(" ");
			this.nombreJugadores.add(datosJugador[0]);
			this.partidasGanadasJugadores.add(Integer.valueOf(datosJugador[1]));
		}
	}	
}