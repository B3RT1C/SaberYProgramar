package preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import interfaces.LectorArchivos;
import util.Consts;

public class Letras extends Pregunta implements LectorArchivos {
	private static ArrayList<String> palabras = new ArrayList<>();
	
	public Letras() throws IOException {
		this.leerArchivo();
		this.filtrarPalabras();
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
		return palabras.get(Consts.RAND.nextInt(0, palabras.size()));
	}
	
	@Override
	public void leerArchivo() throws IOException {
		if (palabras.isEmpty()) {
			palabras = (ArrayList<String>)Files.readAllLines(Consts.DICCIONARIO_PATH);
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
		
		
		return (ArrayList<Integer>)(indicesLetrasPalabra.subList(0, numLetrasOcultar));
	}
	
	private void filtrarPalabras() {
		for (String i : palabras) {
			if (i.length() <= 3) {
				palabras.remove(i);
			}
		}
	}
	
}