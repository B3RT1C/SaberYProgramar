package preguntas;

import gestores.Gestor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import util.Consts;
/**
 * Representa una pregutna de matematicas
 */
public class Mates extends Pregunta {
	/**
	 * Constructor de la clase
	 */
	protected Mates() {
		super.setEnunciado(this.generarEnunciado());
		super.setSolucion(this.generarSolucion());
	}

	@Override
	protected String generarEnunciado() {
		String enunciado = "";
		int totalEnteros = Gestor.rand.nextInt(4, 8 +1); 
		
		for (int i = 0; i < totalEnteros; i++) {
			enunciado += Gestor.rand.nextInt(2, 12 +1);
			
			if (i != totalEnteros-1) {
				enunciado += Consts.OPERADORES[Gestor.rand.nextInt(0, Consts.OPERADORES.length)];
			}
		}

		return enunciado;
	}

	@Override
	protected String generarSolucion() {
		Expression exp = new ExpressionBuilder(super.getEnunciado()).build();
		return Integer.toString((int)exp.evaluate());
	}
}