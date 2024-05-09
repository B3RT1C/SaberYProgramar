package preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import gestores.Gestor;
import util.Consts;

public class Letras extends Pregunta {
	private static ArrayList<String> palabras = new ArrayList<>();
	
	public Letras() throws IOException {
		this.leerArchivo();
		super.setSolucion(this.generarSolucion());
		super.setEnunciado(this.generarEnunciado());
	}
	
	@Override
	protected String generarEnunciado() {
		char[] palabra = this.getSolucion().toCharArray();
		
		ArrayList<Integer> posicionLetrasOcultar = this.indiceAleatorioPalabra(palabra.length);
		
		for (int i : posicionLetrasOcultar) {
			palabra[i] = '*';
		}
		
		return new String(palabra);
	}
	
	@Override
	protected String generarSolucion() {
		String palabraAleatoria;
		do {
			palabraAleatoria = Letras.palabras.get(Gestor.rand.nextInt(0, Letras.palabras.size()));
		} while (palabraAleatoria.length() < Consts.MIN_LENGTH_PALABRA_PERMITIDO);
		return palabraAleatoria;
	}
	
	public void leerArchivo() throws IOException {
		if (Letras.palabras.isEmpty()) {
			Letras.palabras = (ArrayList<String>)Files.readAllLines(Consts.PATH_DICCIONARIO);
		}
	}
	
	private ArrayList<Integer> indiceAleatorioPalabra(int lengthPalabra) {
		int numLetrasOcultar = (int)(lengthPalabra*Consts.RATIO_ESCONDER_LETRAS);
		
		if (numLetrasOcultar >= lengthPalabra) {
			numLetrasOcultar = lengthPalabra - 1;
		}
		
		ArrayList<Integer> indicesLetrasPalabra = new ArrayList<>();
		for (int i = 0; i < lengthPalabra; i++) {
			indicesLetrasPalabra.add(i);
		}
		Collections.shuffle(indicesLetrasPalabra);
		
		return new ArrayList<>(indicesLetrasPalabra.subList(0, numLetrasOcultar));
	}
	
	/**
	 * @deprecated Demasiado lento
	 */
	@SuppressWarnings("unused")
	private void filtrarPalabras() {
		ArrayList<String> palabrasNoValidas = new ArrayList<>();
		for (String i : Letras.palabras) {
			if (i.length() <= 3) {
				palabrasNoValidas.add(i);
			}
		}
		for (String i : palabrasNoValidas) {
			Letras.palabras.remove(i);
		}
	}
	
	
}