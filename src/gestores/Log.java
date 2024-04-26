package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import util.Consts;

public class Log {
	ArrayList<String> log = new ArrayList<>();
	LocalTime time = LocalTime.now();
	LocalDate date = LocalDate.now();
	LocalDateTime dateTime = LocalDateTime.now();
	
	protected Log() {
		this.verificarExistenciaArchivo(Consts.PATH_LOG);
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
	
	public void leerArchivo() throws IOException {
		try {
			this.log = (ArrayList<String>)Files.readAllLines(Consts.PATH_LOG);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.MENSAJE_ERROR_LEER_ARCHIVO);
		}
	}
	
	public void veificarLogHoy() {
		//TODO
	}

	private void escribirArchivo() {
		// TODO Auto-generated method stub
		
	}
	
	public void 

}
