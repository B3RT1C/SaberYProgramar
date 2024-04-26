package util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
	
	public static final String MENSAJE_ERROR_CREAR_PREGUNTA = "ERROR: no se pudo crear una pregunta de tipo letras/ingles, se creará una de tipo Mates";
	public static final String MENSAJE_ERROR_ESCRIBIR_ARCHIVO = "ERROR: no se pudo escribir en el archivo";
	public static final String MENSAJE_ERROR_LEER_ARCHIVO = "ERROR: no se pudo leer el archivo";
	public static final String MENSAJE_ERROR_CREAR_ARCHIVO = "ERROR: no se pudo crear el archivo";
	
	public static final double RATIO_ESCONDER_LETRAS = 1d/3d;

	public static final Random RAND = new Random();
	
	public static final int TOTAL_OPCIONES_INGLES = 4;
}