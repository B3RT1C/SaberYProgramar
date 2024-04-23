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

	public static final Path DICCIONARIO_PATH = Paths.get("./file/diccionario.txt");
	public static final Path INGLES_PATH = Paths.get("./file/ingles.txt");
	public static final Path RANKING_PATH = Paths.get("./file/ranking.txt");
	
	public static final String MENSAJE_ERROR_CREAR_PREGUNTA = "ERROR: no se pudo crear una pregunta de tipo letras/ingles, se creará una de tipo Mates";
	
	public static final double RATIO_ESCONDER_LETRAS = 1d/3d;

	public static final Random RAND = new Random();
	
	public static final int TOTAL_OPCIONES_INGLES = 4;
}