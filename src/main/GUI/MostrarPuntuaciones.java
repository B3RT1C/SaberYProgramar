package main.GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import gestores.Gestor;
import jugadores.Jugador;
import util.Consts;

@SuppressWarnings("serial")
public abstract class MostrarPuntuaciones extends JPanel {
	MostrarPuntuaciones() {
		this.setLayout(null);
		this.setBackground(Color.PINK);
		
		MostradorTexto enunciado = new MostradorTexto(Gestor.partida.getPuntuaciones());
		enunciado.setBounds(110, 200, 420, 20);
		
		this.add(this.addBoton());
		this.add(enunciado);
		
		Gestor.historial.escribir(Gestor.partida.getPuntuaciones());
		
		for (Jugador i : Gestor.partida.getJugadores()) {
			Gestor.jugadores.actualizarPuntosRanking(i.getNombre(), i.getPuntosPartida());;
		}
		Gestor.jugadores.actualizarRanking();
		
		Gestor.log.escribirArchivo(Consts.LOG_FIN_PARTIDA(Gestor.partida.getNumJugadores(), Gestor.partida.getGanador()));
	}
	
	protected abstract JButton addBoton();
}
