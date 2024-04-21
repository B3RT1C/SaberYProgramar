package preguntas;

public abstract class Pregunta {
	private String tipo;
	private String enunciado;
	private String solucion;
	
	Pregunta(String tipo, String enunciado, String solucion) {
		this.tipo = tipo;
		this.enunciado = enunciado;
		this.solucion = solucion;
	}
	
	public String getTipo() {
		return this.tipo;
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