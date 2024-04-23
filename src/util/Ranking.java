package util;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import interfaces.EscritorArchivos;
import interfaces.LectorArchivos;
import jugadores.Jugador;

public class Ranking implements LectorArchivos, EscritorArchivos{
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
			}
		}
	}
	
	public ArrayList<String> getNombreJugadores() {
		return this.nombreJugadores;
	}
	
	public ArrayList<String> getPartidasGanadasJugadores() {
		return this.nombreJugadores;
	}
	
	@Override
	public void escribirArchivo() {
		for (int i = 0; i < nombreJugadores.size(); i++) {
			String registro = nombreJugadores.get(i) + " " + partidasGanadasJugadores.get(i);

			try {
				Files.writeString(Consts.RANKING_PATH, registro);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void leerArchivo() {
		ArrayList<String> aux = new ArrayList<>();
		try {
			aux = (ArrayList<String>)Files.readAllLines(Consts.RANKING_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String i : aux) {
			String[] datosJugador = i.split(" ");
			nombreJugadores.add(datosJugador[0]);
			partidasGanadasJugadores.add(Integer.valueOf(datosJugador[1]));
		}
	}
	
	private void ordenarRanking() {
		//TODO
	}
	
	public Jugador findJugador() {
		//TODO
		return null;
	}
	
	public void actualizar() {
		this.ordenarRanking();
		this.escribirArchivo();
		this.leerArchivo();
	}
}
