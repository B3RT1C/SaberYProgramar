//package main.GUI;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//
//@SuppressWarnings("serial")
//class MenuElegirRondas extends MenuSeleccion {
//	
//	private static int numRondas;
//
//	private TerminarPartida botonSalir = new TerminarPartida();;
////	private JTextField setRondas = new JTextField(); 
////	private JButton getRondas = new JButton("Elegir número rondas");
//	
//	public MenuElegirRondas() {
//		super(4);
//		super.anyadirNombres(new String[]{"Partida rápida: 3 rondas", "Partida corta: 5 rondas", "Partida normal: 10 rondas", "Partida larga: 20 rondas"});
//		
//		this.setLayout(null);
//		this.setBackground(Color.PINK);
//		
//		JButton[] botones = super.getBotones();
//		
//		botones[0].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				establecerNumeroRondasYMostrarElegirCantidadJugadores(3);
//			}
//		});
//		
//		botones[1].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				establecerNumeroRondasYMostrarElegirCantidadJugadores(5);
//			}
//		});
//		
//		botones[2].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				establecerNumeroRondasYMostrarElegirCantidadJugadores(10);
//			}
//		});
//		
//		botones[3].addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				establecerNumeroRondasYMostrarElegirCantidadJugadores(20);
//			}
//		});
//		
//		this.add(this.botonSalir);
//	}
//	
//	public static int getNumRondas() {
//		return numRondas;
//	}
//	
//	private void establecerNumeroRondasYMostrarElegirCantidadJugadores(int numRondas) {
//		MenuElegirRondas.numRondas = numRondas;
//		JuegoGUI.visuales.mostrarElegirCantidadJugadores();
//	}
//}