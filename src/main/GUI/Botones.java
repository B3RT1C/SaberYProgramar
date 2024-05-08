package main.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class BotonCambiarMenu extends JButton implements ActionListener {
	private JuegoGUI frame;
	private JPanel cambio;
	
	public BotonCambiarMenu(String titulo, JuegoGUI frame, JPanel cambio) {
		this.setText(titulo);
		this.frame = frame;
		this.cambio = cambio;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.cambiarPanel();
	}
	
	private void cambiarPanel() {
		this.frame.cambiarJPanel(cambio);
	}
}

@SuppressWarnings("serial")
class BotonSalir extends JButton implements ActionListener {
	BotonSalir() {
		this.setText("Salir");
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}

class MiBoton extends JButton implements ActionListener {
	public MiBoton(JuegoGUI frame) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}