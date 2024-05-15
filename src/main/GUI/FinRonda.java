//package main.GUI;
//
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//
//import gestores.Gestor;
//
//@SuppressWarnings("serial")
//class FinRonda extends MostrarPuntuaciones {
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.setFont(new Font(null, Font.BOLD, 20));
//		g.drawString("Fin de ronda", 260, 100);
//	}
//
//	@Override
//	protected JButton addBoton() {
//		JButton continuar = new JButton("Continuar");
//		continuar.setBounds(ConstsGUI.MEDIOX, 300, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
//		continuar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
//			}
//		});
//		return continuar;
//	}
//}
