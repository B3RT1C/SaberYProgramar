package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import preguntas.Ingles;
import preguntas.Letras;
import preguntas.Mates;

public class Consts {
	public static final String[] TIPOS_PREGUNTAS = {Mates.class.getSimpleName(), Letras.class.getSimpleName(), Ingles.class.getSimpleName()};
	
	public static final String[] OPERADORES = {"+","-","*"};

	public static final Path PATH_DICCIONARIO = Paths.get("./file/diccionario.txt");
	public static final Path PATH_INGLES = Paths.get("./file/ingles.txt");
	public static final Path PATH_RANKING = Paths.get("./file/ranking.txt");
	public static final Path PATH_HISTORIAL = Paths.get("./file/historial.txt");
	public static final Path PATH_LOG = Paths.get("./file/salida.log");
	public static Path PATH_MOVER_LOG(String fechaArchivo) {
		return Paths.get("./historico/salida.log"+"."+fechaArchivo);
	}
	
	public static final String ERROR_CREAR_PREGUNTA = "ERROR: no se pudo crear una pregunta de tipo letras/ingles, se creará una de tipo Mates";
	public static final String ERROR_ESCRIBIR_ARCHIVO = "ERROR: no se pudo escribir en el archivo";
	public static final String ERROR_LEER_ARCHIVO = "ERROR: no se pudo leer el archivo";
	public static final String ERROR_CREAR_ARCHIVO = "ERROR: no se pudo crear el archivo";
	public static final String ERROR_MOVER_ARCHIVO = "ERROR: no se pudo mover el archivo";
	public static final String ERROR_OPCION_NO_VALIDA = "ERROR: opción no válida, introduce otro valor";
	public static final String ERROR_JUGADOR_REPETIDO = "ERROR: jugador ya existente en la partida, introduce otro jugador";
	
	public static final double RATIO_ESCONDER_LETRAS = 1d/3d;

	public static final int TOTAL_OPCIONES_INGLES = 4;
	
	public static final int MIN_LENGTH_PALABRA_PERMITIDO = 4;
	
	public static final String LOG_JUGADOR_ANYADIDO = "Se ha añadido el jugador: ";
	public static final String LOG_JUGADOR_ELIMINADO = "Se ha eliminado el jugador: ";
	public static final String LOG_INICIO_PARTIDA(int numHumanos, int numCpu) {
		return "Inicio de partida con " + numHumanos + " jugadores humanos, " + numCpu + " jugadores CPU";
	}
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
	public static final String MENU_ADD_JUGADOR = "Escribe un nombre para añadir un jugador al sistema";
	public static final String MENU_ADD_JUGADOR_ERROR = "El jugador que has introducido ya existía en el sistema";
	public static final String MENU_REMOVE_JUGADOR = "Escribe un nombre de un jugador para borrarlo del sistema";
	public static final String MENU_REMOVE_JUGADOR_ERROR = "El jugador que has introducido no existe en el sistema";
	public static final String MENU_SUCCEED = "Acción realizada con éxito";
	
	public static final String MENU_CERO_CONTENIDO = "No existe información para esta opción";
}