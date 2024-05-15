package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;
import preguntas.Pregunta;
/**
 * Clase destinada a almacenar Strings staticas finales o metodos staticos que devuelven Strings, 
 * contienen opciones, informacion, mensajes que son contantes usados en el codigo
 */
public class Consts {
	/**
	 * Tipos de preguntas diferentes que pueden existir
	 */
	public static final String[] TIPOS_PREGUNTAS = {Mates.class.getSimpleName(), Letras.class.getSimpleName(), Ingles.class.getSimpleName()};
	/**
	 * Diferentes operadores validos para generar un enunciado de una pregunta de la clase Mates
	 * @see Mates
	 */
	public static final String[] OPERADORES = {"+","-","*"};

	/**
	 * Path del archivo con los enunciados de las preguntas de tipo Letras
	 * @see Letras
	 */
	public static final Path PATH_DICCIONARIO = Paths.get("./file/diccionario.txt");
	/**
	 * Path del archivo con los enunciados y opciones de las preguntas de tipo Ingles
	 * @see Ingles
	 */
	public static final Path PATH_INGLES = Paths.get("./file/ingles.txt");
	/**
	 * Path del archivo que contiene el ranking
	 * @see Ranking
	 */
	public static final Path PATH_RANKING = Paths.get("./file/ranking.txt");
	/**
	 * Path del archivo que contiene el historial
	 * @see Historial
	 */
	public static final Path PATH_HISTORIAL = Paths.get("./file/historial.txt");
	/**
	 * Path del archivo que contiene los mensajes de log
	 * @see Log
	 */
	public static final Path PATH_LOG = Paths.get("./file/salida.log");
	/**
	 * Metodo que devuelve un path donde se movera el archivo log
	 * @param fechaArchivo Fecha de los registros del archivo
	 * @return Path con el nuevo nombre del archivo
	 */
	public static Path PATH_MOVER_LOG(String fechaArchivo) {
		return Paths.get("./historico/salida.log"+"."+fechaArchivo);
	}
	
	/**
	 * Opcion que permitira elegir comparar por dia del año dos fechas
	 * @see gestores.Log#isMismaFecha(String)
	 */
	public static final String COMPARAR_FECHA_POR_DIA = "D";
	/**
	 * Opcion que permitira elegir comparar por mes del año dos fechas
	 * @see gestores.Log#isMismaFecha(String)
	 */
	public static final String COMPARAR_FECHA_POR_MES = "MM";
	/**
	 * Opcion que permitira elegir comparar por año dos fechas
	 * @see gestores.Log#isMismaFecha(String)
	 */
	public static final String COMPARAR_FECHA_POR_ANYO = "yyyy";
	
	/**
	 * Mensaje de pregunta acertada
	 */
	public static final String PREGUNTA_ACERTADA = "¡Pregunta acertada! +1 punto";
	/**
	 * Mensaje de pregunta fallada
	 */
	public static final String PREGUNTA_FALLADA = "Pregunta fallada";
	/**
	 * Mensaje de pregunta fallada con solucion a la pregunta
	 * @param pregunta Pregunta a la cual pertenece la solucion
	 * @return String con el mensaje
	 */
	public static String PREGUNTA_FALLADA(Pregunta pregunta) {
		return Consts.PREGUNTA_FALLADA + "\n"
				 + "Respuesta correcta: " + pregunta.getSolucion();
	}
	
	/**
	 * Mensaje de error al crear un pregunta
	 */
	public static final String ERROR_CREAR_PREGUNTA = "ERROR: no se pudo crear una pregunta de tipo letras/ingles, se creará una de tipo Mates";
	/**
	 * Mensaje de error al escribir en un archivo
	 */
	public static final String ERROR_ESCRIBIR_ARCHIVO = "ERROR: no se pudo escribir en el archivo";
	/**
	 * Mensaje de error al leer de un archivo
	 */
	public static final String ERROR_LEER_ARCHIVO = "ERROR: no se pudo leer el archivo";
	/**
	 * Mensaje de error al crear un archivo
	 */
	public static final String ERROR_CREAR_ARCHIVO = "ERROR: no se pudo crear el archivo";
	/**
	 * Mensaje de error al mover un archivo de sitio
	 */
	public static final String ERROR_MOVER_ARCHIVO = "ERROR: no se pudo mover el archivo";
	/**
	 * Mensaje de error al introducir una opcion no valida
	 */
	public static final String ERROR_OPCION_NO_VALIDA = "ERROR: opción no válida, introduce otro valor";
	/**
	 * Mensaje de error tras seleccionar un jugador ya seleccionado
	 */
	public static final String ERROR_JUGADOR_REPETIDO = "ERROR: jugador ya existente en la partida, introduce otro jugador";
	
	/**
	 * Porcentaje de letras que se esconderan al jugador en las preguntas de letras
	 * @see Letras
	 */
	public static final double RATIO_ESCONDER_LETRAS = 1d/3d;
	/**
	 * Minimo de letras que puede contener una palabra para poder ser seleccionada al crear una pregunta de tipo letras
	 * @see Letras
	 */
	public static final int MIN_LENGTH_PALABRA_PERMITIDO = 4;

	/**
	 * Numero de opciones diferentes que tienen las preguntas de ingles
	 * @see Ingles
	 */
	public static final int TOTAL_OPCIONES_INGLES = 4;
	
	/**
	 * Registro del log cuando se añade un jugador
	 */
	public static final String LOG_JUGADOR_ANYADIDO = "Se ha añadido el jugador: ";
	/**
	 * Registro del log cuando se elimina un jugador
	 */
	public static final String LOG_JUGADOR_ELIMINADO = "Se ha eliminado el jugador: ";
	/**
	 * Metodo que devuelve un registro a escribir en el log que contiene informacion sobre el inicio de la partida
	 * @param numHumanos Numero de jugadores humanos de la partida
	 * @param numCpu Numero de jugadores CPU de la partido
	 * @return Registro a escribir en el log
	 */
	public static String LOG_INICIO_PARTIDA(int numHumanos, int numCpu) {
		return "Inicio de partida con " + numHumanos + " jugadores humanos, " + numCpu + " jugadores CPU";
	}
	/**
	 * Metodo que devuelve un registro a escribir en el log que contiene informacion sobre el fin de la partida
	 * @param numJugadores Numero de jugadores de la partida
	 * @param ganador ArrayList<String> con el nombre de los jugadores que han ganado la partida
	 * @return
	 */
	public static final String LOG_FIN_PARTIDA(int numJugadores, ArrayList<String> ganador) {
		String mensaje = "Fin partida con " + numJugadores + " jugador/es. ";
		if (ganador.size() == 1) {
			mensaje += "Ganador: " + ganador.get(0);
		
		} else if (ganador.size() == 0) {
			mensaje += "No hubo ganador/es";
		
		} else {
			String ganadores = "";
			for (int i = 0; i < ganador.size(); i++) {
				ganadores += ganador.get(i);
				
				if (i != ganador.size()-1) {
					ganadores += ", ";
				}
			}
			mensaje += "Ha sido empate. Ganadores: " + ganadores;
		}
		
		return mensaje;
	}
	public static final String LOG_ERROR = "ERROR. Se ha producido un error en la aplicación: ";
	
	public static final String MENU_TITLE = "SABER Y PROGRAMAR";
	public static final String MENU_SALIR = "Saliendo...";
	public static final String MENU_VOLVER = "Volviendo...";
	public static final String MENU_FORMATEO_NOMBRES = "Las mayúscula y minúsculas serán ignoradas en los nombres de los jugadores";
	public static final String MENU_ADD_JUGADOR = "Escribe un nombre para añadir un jugador";
	public static String MENU_ADD_JUGADOR_INEXISTENTE(String nombre) {
		return "El jugador: " + nombre.toUpperCase() + " no existe en el sistema, ¿quieres añadirlo?";
	}
	public static final String MENU_ADD_JUGADOR_ERROR = "El nombre del jugador que has introducido no es válido";
	public static final String MENU_REMOVE_JUGADOR = "Escribe un nombre de un jugador para borrarlo del sistema";
	public static final String MENU_REMOVE_JUGADOR_ERROR = "El jugador que has introducido no existe en el sistema";
	public static final String MENU_SUCCEED = "Acción realizada con éxito";
	
	public static final String MENU_CERO_CONTENIDO = "No existe información para esta opción";
}