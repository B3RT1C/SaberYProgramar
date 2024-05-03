package preguntas;

import java.io.IOException;

import gestores.Gestor;
import util.Consts;

public abstract class Pregunta {
	private String enunciado;
	private String solucion;
	
	protected Pregunta() {}
	
	protected abstract String generarEnunciado();
	protected abstract String generarSolucion();
	
	public static Pregunta generarAleatoria() {
		String randomTipoPregunta = Consts.TIPOS_PREGUNTAS[Consts.RAND.nextInt(0, Consts.TIPOS_PREGUNTAS.length)];
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

	public boolean verificarRespuesta(String respuesta) {
		return this.solucion == respuesta;
	}
}