package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import util.Consts;

public class Historial {
	ArrayList<String> historial = new ArrayList<>();
	
	protected Historial() {
		this.verificarExistenciaArchivo(Consts.PATH_HISTORIAL);

	}
	
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
	
	private void leerArchivo() {
		try {
			this.historial = (ArrayList<String>)Files.readAllLines(Consts.PATH_HISTORIAL);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_LEER_ARCHIVO);
		}
	}

	public void escribirs(String linea) {
		try {
			Files.writeString(Consts.PATH_HISTORIAL, linea+"\n", StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Consts.ERROR_ESCRIBIR_ARCHIVO);
		}
	}

	public ArrayList<String> getHistorial() {
		this.leerArchivo();
		return this.historial;
	}
	
}