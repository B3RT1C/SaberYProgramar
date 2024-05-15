package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import util.Consts;

public class Log {
	private ArrayList<String> log = new ArrayList<>();
	private String fechaLog;
	
	protected Log() {
		this.verificarExistenciaArchivo();
		this.leerArchivo();
		this.setFechaLog();
	}

	private void verificarExistenciaArchivo() {
		if (!Files.exists(Consts.PATH_LOG)) {
			try {
				Files.createFile(Consts.PATH_LOG);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(Consts.ERROR_CREAR_ARCHIVO);
			}
		}
	}
	
	private void leerArchivo() {
		try {
			this.log = (ArrayList<String>)Files.readAllLines(Consts.PATH_LOG);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_LEER_ARCHIVO);
			this.fechaLog = null;
		}
	}
	
	private void setFechaLog() {
		if (this.log.isEmpty()) {
			this.fechaLog = null;
		} else {
			this.fechaLog = this.getFechaRegistro(this.log.get(this.log.size()-1));
		}
	}
	
	private String getFechaRegistro(String registro) {
		String[] datos = registro.split("\\[|\\]\\[|\\]: ");
        return datos[1];
	}
	
	private void veificarLogHoy() {
//		if (!this.getFecha().equals(this.fechaLog) && this.fechaLog != null) {

		if (!(this.isMismaFecha(Consts.COMPARAR_FECHA_POR_DIA)) && this.fechaLog != null) {
			String[] fecha = this.fechaLog.split("/");
			try {
				Files.move(Consts.PATH_LOG, Consts.PATH_MOVER_LOG(fecha[2]+fecha[1]+fecha[0]));
				this.verificarExistenciaArchivo();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(Consts.ERROR_MOVER_ARCHIVO);
			}
		}
	}
	
	private boolean isMismaFecha(String comparador) {
		return (LocalDate.parse(this.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).
				format(DateTimeFormatter.ofPattern(comparador)).
					matches(
							LocalDate.parse(this.fechaLog, DateTimeFormatter.ofPattern("dd/MM/yyyy")).
							format(DateTimeFormatter.ofPattern(comparador))));
	}

	public void escribirArchivo(String linea) {
		this.setFechaLog();
		this.veificarLogHoy();
		
		linea = "["+ this.getFecha() +"]" + "["+this.getHora()+"]: " + linea + "\n";
		try {
			Files.writeString(Consts.PATH_LOG, linea, StandardOpenOption.APPEND);
			this.log.add(linea);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Consts.ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	private String getFecha() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	private String getHora() {
		return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
}