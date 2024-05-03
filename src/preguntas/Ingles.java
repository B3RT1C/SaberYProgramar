package preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

import util.Consts;

public class Ingles extends Pregunta {
	public static ArrayList<String> preguntasRespuestas = new ArrayList<>();
	private ArrayList<String> opciones = new ArrayList<>();
	
	public Ingles() throws IOException {
		this.leerArchivo();
		super.setEnunciado(this.generarEnunciado());
		this.generarOpciones();
		super.setSolucion(this.generarSolucion());
		this.mezclarOpciones();
	}
	
	public ArrayList<String> getOpciones() {
		return opciones;
	}
	
	public void leerArchivo() throws IOException {
		if (preguntasRespuestas.isEmpty()) {
			preguntasRespuestas = (ArrayList<String>)Files.readAllLines(Consts.PATH_INGLES);
		}
	}
	
	protected void generarOpciones() {
		int indiceEnunciado = preguntasRespuestas.indexOf(this.getEnunciado());
		//El número de respuestas de cada enunciado son 4 y cada uno ocupa una linea por lo que sus índices son el del enunciado+(1,2,3,4)
		for (int i = 1; i <= Consts.TOTAL_OPCIONES_INGLES; i++) {
			opciones.add(preguntasRespuestas.get(indiceEnunciado +i));			
		}
	}

	protected void mezclarOpciones() {
		Collections.shuffle(opciones);
	}
	
	@Override
	protected String generarEnunciado() {
		//Todos los encunciados en el archivo ingles.txt están cada 5 líneas por lo que
		//Si hay 5000 líneas en el archivo todas las líneas que se encuentren en n*5 siendo
		//n {0, 5000/5} tendrán siempre un enunciado, exceptiando la línea 5000
		int randomIndiceEnunciado = Consts.RAND.nextInt(0,(preguntasRespuestas.size()/(Consts.TOTAL_OPCIONES_INGLES+1))) * (Consts.TOTAL_OPCIONES_INGLES+1);
		return preguntasRespuestas.get(randomIndiceEnunciado);
	}
	
	@Override
	protected String generarSolucion() {
		return opciones.get(0);
	}

}