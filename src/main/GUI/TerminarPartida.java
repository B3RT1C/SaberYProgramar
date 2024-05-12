package main.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class TerminarPartida extends JButton {
	public TerminarPartida() {
		this.setText("Salir");
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showOptionDialog(TerminarPartida.this.getParent(), "Seguro que quieres salir?", null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Y", "N"}, "Y");
				if (opcion == 0) {
					JuegoGUI.visuales.mostrarPrincipal();

				}
			}
		});
		
		this.setBounds(500, 400, ConstsGUI.BUTTONWIDTH/2, ConstsGUI.BUTTONHEIGHT);
	}
}