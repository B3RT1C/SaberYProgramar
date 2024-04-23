package preguntas;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import util.Consts;

public class Mates extends Pregunta {
	protected Mates() {
		super.setEnunciado(this.generarEnunciado());
		super.setSolucion(this.generarSolucion());
	}

	@Override
	protected String generarEnunciado() {
		String enunciado = "";
		int totalEnteros = Consts.RAND.nextInt(4, 8 +1); 
		
		for (int i = 0; i < totalEnteros; i++) {
			enunciado += Consts.RAND.nextInt(2, 12 +1);
			
			if (i != totalEnteros-1) {
				enunciado += Consts.OPERADORES[Consts.RAND.nextInt(0, Consts.OPERADORES.length)];
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