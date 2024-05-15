package preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import gestores.Gestor;
import util.Consts;

/**
 * Representa una pregunta de tipo letras, lee del archivo file/diccionario.txt
 */
public class Letras extends Pregunta {
	/**
	 * Contiene la informaion del archivo
	 */
	private static ArrayList<String> palabras = new ArrayList<>();
	
	/**
	 * Contructor de la clase
	 * @throws IOException
	 */
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
	/**
	 * Lee el archivo y añade su contenido al ArrayList palabras
	 * @see palabras
	 * @throws IOException
	 */
	public void leerArchivo() throws IOException {
		if (Letras.palabras.isEmpty()) {
			Letras.palabras = (ArrayList<String>)Files.readAllLines(Consts.PATH_DICCIONARIO);
		}
	}
	/**
	 * Usado para seleccionar una letra aleatoria de una palablra
	 * @param lengthPalabra
	 * @return ArrayList<Integer> con x indices aleatorios de letras dentro de la palabra sin repeticion, siendo x = MIN_LENGTH_PALABRA_PERMITIDO
	 * @see MIN_LENGTH_PALABRA_PERMITIDO
	 */
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
	 * Elimina las palabras con menor longitud que MIN_LENGTH_PALABRA_PERMITIDO del ArrayList
	 * @see MIN_LENGTH_PALABRA_PERMITIDO
	 * @deprecated Demasiado lento, no parece ser muy optimo
	 */
	@SuppressWarnings("unused")
	private void filtrarPalabras() {
		ArrayList<String> palabrasNoValidas = new ArrayList<>();
		for (String i : Letras.palabras) {
			if (i.length() < Consts.MIN_LENGTH_PALABRA_PERMITIDO) {
				palabrasNoValidas.add(i);
			}
		}
		for (String i : palabrasNoValidas) {
			Letras.palabras.remove(i);
		}
	}
	
	
}