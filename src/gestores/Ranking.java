package gestores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;

import util.Consts;

/**
 * Clase encargada de gestionar los jugadores existentes en la partida, lee y escribe en el archivo file/ranking.txt
 * La instancia de esta clase en la clase Gestor se llama jugadores ya que se ha considerado mas apropiado
 */
public class Ranking {
	/**
	 * ArrayList<String> que contiene la informacion del archivo 
	 */
	private ArrayList<String> ranking = new ArrayList<>();
	/**
	 * ArrayList<String> que contiene la informacion del archivo respectiva a los nombres de los jugadores
	 */
	private ArrayList<String> nombreJugadores = new ArrayList<>();
	/**
	 * ArrayList<Integer> que contiene la informacion del archivo respectiva a los puntos totales de los jugadores
	 */
	private ArrayList<Integer> partidasGanadasJugadores = new ArrayList<>();
 
	/**
	 * Constructor de la clase
	 */
	protected Ranking() {
		this.verificarExistenciaArchivo(Consts.PATH_RANKING);
		this.leerArchivo();
	}
	/**
	 * Verifica que exista el archivo, si no existe lo crea
	 * @param path Path y nombre del archivo que se quiere verificar la existencia
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
	
	public ArrayList<Integer> getPartidasGanadas() {
		return this.partidasGanadasJugadores;
	}
	
	public ArrayList<String> getNombres() {
		return this.nombreJugadores;
	}
	
	public ArrayList<String> getRanking() {
		return this.ranking;
	}
	
	/**
	 * Permite crear un jugador en el sistema (No admite nombres como CPU o CPU+[numero])
	 * Todos los jugadores tendran su nombre en mayusculas
	 * @param jugador Nombre del jugador a crear
	 * @return true si se ha creado con exito el jugador, false si el jugador ya existia, si tiene un nombre no valido o vacio
	 */
	public boolean crearJugador(String jugador) {
		jugador = jugador.toUpperCase();
		if (!this.existsJugador(jugador) && !jugador.matches("CPU\\d*") && !jugador.equals("")) {
			this.ranking.add(jugador + " 0");
			this.nombreJugadores.add(jugador);
			this.partidasGanadasJugadores.add(0);
			this.actualizarRanking();
			Gestor.log.escribirArchivo(Consts.LOG_JUGADOR_ANYADIDO + jugador);
			return true;
		
		} else {
			return false;
		}
	}
	/**
	 * Permite eliminar un jugador del sistema
	 * @param jugador Nombre del jugador a eliminar
	 * @return true si se ha eliminado el jugador con exito, false si el jugador no existia previamente en el sistema
	 */
	public boolean removeJugador(String jugador) {
		jugador = jugador.toUpperCase();
		if (this.existsJugador(jugador)) {
			int index = this.findIndiceJugador(jugador);
			this.nombreJugadores.remove(index);
			this.partidasGanadasJugadores.remove(index);
			this.actualizarRanking();
			Gestor.log.escribirArchivo(Consts.LOG_JUGADOR_ELIMINADO + jugador);
			return true;

		} else {
			return false;
		}
	}
	/**
	 * Permite encontrar a un jugador por nombre en el ArrayList nombreJugadores
	 * @param nombre Nombre del jugador que se quiere buscar
	 * @see nombreJugadores
	 * @return Indice del jugador en el array
	 */
	private int findIndiceJugador(String nombre) {
		nombre = nombre.toUpperCase();
		return this.nombreJugadores.indexOf(nombre);
	}
	/**
	 * Permite saber si un jugador existe en el sistema
	 * @param nombre Nombre del jugador que se quiere saber su existencia
	 * @return true si el jugador existe, false si el jugador no existe
	 */
	public boolean existsJugador(String nombre) {
		nombre = nombre.toUpperCase();
		return this.nombreJugadores.contains(nombre);
	}
	/**
	 * Actualiza los puntos un jugador en el ranking
	 * @param nombre Nombre del jugador al cual se le quieren actualizar los puntos
	 * @param puntuacion Cantidad de puntos a añadir a la puntuacion preexistente
	 */
	public void actualizarPuntosRanking(String nombre, int puntuacion) {
		//Evita a las cpus en caso de que ganen la partida
		if (this.existsJugador(nombre)) {
			int index = this.findIndiceJugador(nombre);
			int partidasGanadas = this.partidasGanadasJugadores.get(index);
			this.partidasGanadasJugadores.set(index, partidasGanadas+puntuacion);
		}
	}
	/**
	 * Ordena el ranking por puntos de mayor a menor
	 */
	private void ordenarRanking() {
		ArrayList<Integer> partidasGanadas = this.getValoresDiferentesPartidasGanadas();
		
		ArrayList<String> auxRanking = new ArrayList<>();
		ArrayList<String> auxNombresJugadores = new ArrayList<>();
		ArrayList<Integer> auxPartidasGanadasJugadores = new ArrayList<>();
		
		//TODO optimizar con un hasmap? key: puntuacion, val: jugador (key: 1, val: juan, pepe)
		for (int i : partidasGanadas) {
			for (int j = 0; j < this.partidasGanadasJugadores.size(); j++) {
				
				if (i == this.partidasGanadasJugadores.get(j)) {
					auxRanking.add(this.nombreJugadores.get(j)+" "+this.partidasGanadasJugadores.get(j));
					auxNombresJugadores.add(this.nombreJugadores.get(j));
					auxPartidasGanadasJugadores.add(this.partidasGanadasJugadores.get(j));
				}
			}
		}

		this.ranking = auxRanking;
		this.nombreJugadores = auxNombresJugadores;
		this.partidasGanadasJugadores = auxPartidasGanadasJugadores;
	}
	
	/*Devuelve sin repeticiones cada valor que aparece en partidasGanadasJugadores ordenado de mayor a menor
	 * ej: {6,3,3,8,1} -> devuelve {8,6,3,1}
	*/
	/**
	 * Permite encontrar los valores diferentes del ArrayList partidasGanadasJugadores
	 * @see partidasGanadasJugadores
	 * @return ArrayList<Integer> con cada valor diferente exitente en el array y esta ordenado de mayor a menor, 
	 * Array(5,5,5,3,2,2,1,0,0) -> Return(5,3,2,1,0)
	 */
	private ArrayList<Integer> getValoresDiferentesPartidasGanadas() {
		ArrayList<Integer> valoresDiferentes = new ArrayList<>();
		
		for (int i : partidasGanadasJugadores) {
			if (!valoresDiferentes.contains(i)) {
				valoresDiferentes.add(i);
			}
		}
		
		Collections.sort(valoresDiferentes);
		Collections.reverse(valoresDiferentes);
		
		return valoresDiferentes;
	}
	
	//Se llama a este método cada vez que se termina una partida o se crea o borra un jugador
	/**
	 * Llama a ordenarRanking() y luego sobreescribe el archivo con los valores del ArrayList ranking
	 * @see ordenarRanking()
	 * @see ranking
	 */
	public void actualizarRanking() {
		this.ordenarRanking();
		this.escribirArchivo();
	}
	/**
	 * Sobreescribe el archivo y añade una linea por cada String del ArrayList ranking
	 */
	private void escribirArchivo() {
		try {
			//Sobreescribe todo el archivo a una cadena sin nada/borra el contenido del archivo
			Files.writeString(Consts.PATH_RANKING, "");
			
			for (String i : this.ranking) {
				Files.writeString(Consts.PATH_RANKING, i+"\n", StandardOpenOption.APPEND);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_ESCRIBIR_ARCHIVO);
		}
	}
	
	/**
	 * Lee el archivo y añade su contenido a los ArrayList ranking, nombreJugadores y partidasGanadasJugadores
	 * Si durante la lectura se encuentra algun registro que no siga el patron [String][espacio][int] lo ignorara
	 * @see ranking
	 * @see nombreJugadores 
	 * @see partidasGanadasJugadores
	 */
	private void leerArchivo() {
		ArrayList<String> aux = new ArrayList<>();
		try {
			aux = (ArrayList<String>)Files.readAllLines(Consts.PATH_RANKING);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(Consts.ERROR_LEER_ARCHIVO);
		}
		
		for (String i : aux) {
			String[] datosJugador = i.split(" ");

			/*Se escriben primero el número de partidas ganadas para que si salta un indexOutOfBounds o un
			 * numberFormatException que se capture la excepción y no se guarde en los ArrayLists
			 * Así el programa continua aunque existan datos con el formato incorrecto en el arhivo,
			 * este caso no se puede dar a menos que el usuario cambie manualmente el ranking
			*/
			try {
				//Este if evita que se almacenen jugadores repetidos en el caso de que el archivo por la razón que sea tenga nombres repetidos
				if (!this.existsJugador(datosJugador[0])) {
					this.partidasGanadasJugadores.add(Integer.valueOf(datosJugador[1]));
					this.nombreJugadores.add(datosJugador[0].toUpperCase());				
					this.ranking.add(i);
				}
			} catch (Exception e) {
			}
		}
	}	
}