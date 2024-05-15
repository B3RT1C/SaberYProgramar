package preguntas;

import java.io.IOException;

import gestores.Gestor;
import util.Consts;

/**
 * Clase que permite almacenar informacion sobre una pregunta y realizar ciertas operaciones relacionadas 
 */
public abstract class Pregunta {
	/**
	 * Enunciado de la pregunta
	 */
	private String enunciado;
	/**
	 * Solucion de la pregunta
	 */
	private String solucion;
	
	/**
	 * Constructor de la clase
	 */
	protected Pregunta() {}
	
	/**
	 * Metodo abstracto destinado a generar un enunciado para la pregunta dependiendo de su tipo
	 * @return String con el enunciado
	 */
	protected abstract String generarEnunciado();
	/**
	 * Metodo abstracto destinado a generar una solucion dependiendo del enunciado
	 * @return String con la sulucion a la pregunta
	 */
	protected abstract String generarSolucion();
	
	/**
	 * Metodo estatico que funciona como herramienta principal para generar una pregunta, controla las excepciones lanzadas por los constructores de las subclases
	 * @return Instancia de una subclase de Pregunta ya inicializada y lista para su uso
	 */
	public static Pregunta generarAleatoria() {
		String randomTipoPregunta = Consts.TIPOS_PREGUNTAS[Gestor.rand.nextInt(0, Consts.TIPOS_PREGUNTAS.length)];
		Pregunta pregunta = new Mates();
		//En el caso de que el new Letras() o new Ingles() lance una excepción, pregunta siempre será de tipo Mates y la excepción será capturada por el try catch
		try {
			if (randomTipoPregunta.equals(Letras.class.getSimpleName())) {
				pregunta = new Letras();
			
			} else if (randomTipoPregunta.equals(Ingles.class.getSimpleName())) {
				pregunta = new Ingles();
			}
		} catch (IOException e) {
			System.out.println(Consts.ERROR_CREAR_PREGUNTA);
			Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.ERROR_CREAR_PREGUNTA);
		}

		return pregunta;
	}
	
	public String getEnunciado() {
		return this.enunciado;
	}
	void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getSolucion() {
		return this.solucion;
	}
	void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	/**
	 * Verifica si una respuesta es la correcta para la pregunta
	 * @param respuesta
	 * @return true si la respuesta es correcta, false si la respuesta es incorrecta
	 */
	public boolean verificarRespuesta(String respuesta) {
		return this.solucion.equals(respuesta);
	}
}