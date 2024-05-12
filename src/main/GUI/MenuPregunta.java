package main.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gestores.Gestor;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Pregunta;

@SuppressWarnings("serial")
public class MenuPregunta extends MenuModificarJugador {
	private Pregunta pregunta;
	private Jugador jugador;
	private TerminarPartida salir = new TerminarPartida();
	
	public MenuPregunta(Pregunta pregunta) {
		this.setBackground(Color.PINK);
		super.setNombreGetText("Responder");
		
		this.pregunta = pregunta;
		this.jugador = Gestor.partida.nextJugador();
		
		this.esMasFacilHacerEsteMetodoQueArreglarLaHerencia();
		
		this.add(salir);

		if (jugador instanceof Cpu) {
			
			
			
			
		}
	}

	@Override
	protected ActionListener setListenerGetText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void esMasFacilHacerEsteMetodoQueArreglarLaHerencia() {
		for (Component i : this.getComponents()) {
			if (i instanceof JButton  && ((JButton)i).getText().equals("Volver")) {
				this.remove(i);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Turno de " + jugador.getNombre(), 110, 280);
		
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(this.pregunta.getEnunciado(), 110, 200);
	}
	
}
