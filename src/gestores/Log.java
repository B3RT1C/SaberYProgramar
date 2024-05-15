package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import util.Consts;

/**
 * Clase encargada de leer y escribir en el archivo file/salida.log
 */
public class Log {
	/**
	 * ArrayList con el contenido del archivo
	 */
	private ArrayList<String> log = new ArrayList<>();
	/**
	 * String con la fecha del ultimo registro del archivo
	 */
	private String fechaLog;
	
	/**
	 * Constructor de la clase
	 */
	protected Log() {
		this.verificarExistenciaArchivo();
		this.leerArchivo();
		this.setFechaLog();
	}
	/**
	 * Verifica que exista el archivo salida.log, si no existe lo crea
	 */
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
	/**
	 * Lee el contenido del archivo y lo guarda en el ArrayList log
	 * @see log
	 */
	private void leerArchivo() {
		try {
			this.log = (ArrayList<String>)Files.readAllLines(Consts.PATH_LOG);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_LEER_ARCHIVO);
			this.fechaLog = null;
		}
	}
	/**
	 * Asigna a la variable fechaLog un valor igual a la fecha del ultimo registro del log
	 * @see fechaLog
	 */
	private void setFechaLog() {
		if (this.log.isEmpty()) {
			this.fechaLog = null;
		} else {
			this.fechaLog = this.getFechaRegistro(this.log.get(this.log.size()-1));
		}
	}
	/**
	 * Procesa un registro entero del log para obtener solo la fecha
	 * @param registro
	 * @return String con la fecha del registro
	 */
	private String getFechaRegistro(String registro) {
		String[] datos = registro.split("\\[|\\]\\[|\\]: ");
        return datos[1];
	}
	/**
	 * Verifica que los registros del archivo esten dentro del periodo de tiempo exigido
	 */
	private void veificarLog() {
//		if (!this.getFecha().equals(this.fechaLog) && this.fechaLog != null) {

		if (!(this.isMismaFecha(Consts.COMPARAR_FECHA_POR_DIA, this.getFecha(), this.fechaLog)) && this.fechaLog != null) {
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
	/**
	 * Se encarga de comparar 2 fechas segun una opcion dada
	 * @param comparador Opcion que indica por uqe valor de la fecha comparar (dia del mes, mes, año, semana, etc...)
	 * @param fecha1 Fecha a comparar
	 * @param fecha2 Fecha a comparar
	 * @return true si al comparar los valores son iguales, false si al comparar los valores son diferentes
	 * @see Consts#COMPARAR_FECHA_POR_DIA
	 * @see Consts#COMPARAR_FECHA_POR_MES
	 * @see Consts#COMPARAR_FECHA_POR_ANYO
	 */
	private boolean isMismaFecha(String comparador, String fecha1, String fecha2) {
		return (LocalDate.parse(fecha1, DateTimeFormatter.ofPattern("dd/MM/yyyy")).
				format(DateTimeFormatter.ofPattern(comparador)).
					matches(
							LocalDate.parse(fecha2, DateTimeFormatter.ofPattern("dd/MM/yyyy")).
							format(DateTimeFormatter.ofPattern(comparador))));
	}
	/**
	 * Escribe un registro en el archivo
	 * @param linea Registro a escribir
	 */
	public void escribirArchivo(String linea) {
		this.setFechaLog();
		this.veificarLog();
		
		linea = "["+ this.getFecha() +"]" + "["+this.getHoraNow()+"]: " + linea + "\n";
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
	
	private String getHoraNow() {
		return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
}