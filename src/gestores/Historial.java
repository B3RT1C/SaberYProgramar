package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import util.Consts;

/**
 * Clase encargada de leer y escribir en el archivo file/historial.tx
 */
public class Historial {
	/**
	 * ArrayList con el contenido del archivo
	 */
	private ArrayList<String> historial = new ArrayList<>();
	
	/**
	 * Constructor del array
	 */
	protected Historial() {
		this.verificarExistenciaArchivo(Consts.PATH_HISTORIAL);
		this.leerArchivo();
	}
	
	/**
	 * Verifica la existencia del archivo, si no existe lo crea
	 * @param path Path y nombre del archivo del cual se va a revisar su existencia
	 */
	private void verificarExistenciaArchivo(Path path) {
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(Consts.ERROR_CREAR_ARCHIVO);
			}
		}
	}
	/**
	 * Lee del archivo y almacena su contenido en el ArrayList historial,
	 * en el caso de que el archivo ya haya sido leido, no se volvera a leer
	 * @see historial
	 */
	private void leerArchivo() {
		try {
			this.historial = (ArrayList<String>)Files.readAllLines(Consts.PATH_HISTORIAL);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_LEER_ARCHIVO);
		}
	}
	/**
	 * Escribe un registro en el archivo
	 * @param linea String a escribir en el archivo
	 */
	public void escribir(String linea) {
		try {
			Files.writeString(Consts.PATH_HISTORIAL, linea+"\n", StandardOpenOption.APPEND);
			this.historial.add(linea);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Consts.ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	public ArrayList<String> getHistorial() {
		return this.historial;
	}
	
}