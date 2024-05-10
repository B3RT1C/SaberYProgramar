//package main.GUI;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import gestores.Gestor;
//import util.Consts;
//
//@SuppressWarnings("serial")
//abstract class MenuModificarJugador extends JPanel {
//	private JTextField inText;
//	private JButton getText = new JButton("Añadir jugador");
//	private JButton volver = new JButton("Volver");
//	private int medioX = (ConstsGUI.FRAMEWIDTH/2)-(ConstsGUI.BUTTONWIDTH/2);
//	
//	MenuModificarJugador() {
//		this.setLayout(null);
//		this.setBackground(Color.GREEN);
//		
//		this.configurarComponentes();
//
//		this.add(inText);
//		this.add(getText);
//		this.add(volver);
//	}
//	
//	protected JTextField getInText() {
//		return this.inText;
//	}
//	
//	private void configurarComponentes() {
//		this.inText = new JTextField();
//		this.inText.setBounds(this.medioX-(ConstsGUI.BUTTONWIDTH/2)-10, 300, ConstsGUI.BUTTONWIDTH*2+20, 20);
//
//		this.getText.setBounds(this.medioX-(ConstsGUI.BUTTONWIDTH/2)-10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
//		this.getText.addActionListener(this.setListenerGetText());
//		
//		this.volver.setBounds(this.medioX+(ConstsGUI.BUTTONWIDTH/2)+10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
//		this.volver.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JuegoGUI.visuales.volver();
//			}
//		});
//	}
//	
//	protected void setNombreGetText(String nombre) {
//		this.getText.setText(nombre);
//	}
//	
//	protected abstract ActionListener setListenerGetText();	
//}
//
//@SuppressWarnings("serial")
//class MenuAnyadirJugador extends MenuModificarJugador {
//
//	public MenuAnyadirJugador() {
//		super.setNombreGetText("Añadir jugador");
//	}
//	
//	@Override
//	protected ActionListener setListenerGetText() {
//		return new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String nombre = getInText().getText();
//				if (Gestor.jugadores.crearJugador(nombre)) {
//					JOptionPane.showMessageDialog(MenuAnyadirJugador.this, Consts.MENU_SUCCEED);
//					JuegoGUI.visuales.volver();
//					
//				} else {
//					JOptionPane.showMessageDialog(MenuAnyadirJugador.this, Consts.MENU_ADD_JUGADOR_ERROR);
//					Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
//				}
//			}
//		};
//	}
//}
//
//@SuppressWarnings("serial")
//class MenuEliminarJugador extends MenuModificarJugador {
//
//	public MenuEliminarJugador() {
//		super.setNombreGetText("Eliminar jugador");
//	}
//	
//	@Override
//	protected ActionListener setListenerGetText() {
//		return new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String nombre = getInText().getText();
//				if (Gestor.jugadores.removeJugador(nombre)) {
//					JOptionPane.showMessageDialog(MenuEliminarJugador.this, Consts.MENU_SUCCEED);
//					JuegoGUI.visuales.volver();
//					
//				} else {
//					JOptionPane.showMessageDialog(MenuEliminarJugador.this, Consts.MENU_REMOVE_JUGADOR_ERROR);
//					Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
//				}
//			}
//		};
//	}
//}
