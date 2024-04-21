package core.preguntas;

public class Pregunta {
	private String enunciado;
	private String solucion;
	
	Pregunta(String enunciado, String solucion) {
		this.enunciado = enunciado;
		this.solucion = solucion;
	}

	public String getEnunciado() {
		return enunciado;
	}
	void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getSolucion() {
		return solucion;
	}
	void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public boolean responder(String respuesta) {
		return this.solucion == respuesta;
	}
}
