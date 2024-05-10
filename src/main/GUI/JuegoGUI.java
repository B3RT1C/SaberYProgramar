package main.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class JuegoGUI extends JFrame {
	
	static VisualesGUI visuales;
	
	public JuegoGUI() throws InterruptedException {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(ConstsGUI.FRAMEWIDTH, ConstsGUI.FRAMEHEIGHT);
		this.setTitle("Saber Y Programar");
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);

		JuegoGUI.visuales = new VisualesGUI(this);
		JuegoGUI.visuales.mostrarPrincipal();
		
		Thread.sleep(2000);
		
		JOptionPane a = new JOptionPane();
		JOptionPane.showMessageDialog(this, "uwu");
		
		new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOption
			}
		});
	}
	
}