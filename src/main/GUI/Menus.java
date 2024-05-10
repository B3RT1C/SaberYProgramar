package main.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gestores.Gestor;
import util.Consts;

@SuppressWarnings("serial")
abstract class MenuSeleccion extends JPanel {
	private JButton[] botones;
	
	private int medioX;
	private int medioY;
	
	protected MenuSeleccion(int numBotones) {
		this.setLayout(null);
		this.botones = new JButton[numBotones];
		
		medioX = (ConstsGUI.FRAMEWIDTH/2)-(ConstsGUI.BUTTONWIDTH/2);
		medioY = (ConstsGUI.FRAMEHEIGHT/2)-(((20*(botones.length))+10*(botones.length-1))/2);
		
		this.configurarBotones();
		this.anyadirBotones();
	}
	
	protected JButton[] getBotones() {
		return this.botones;
	}
	
	private void configurarBotones() {
		for (int i = 0; i < this.botones.length; i++) {
			this.botones[i] = new JButton();
			this.botones[i].setBounds(medioX,medioY+(30*i),ConstsGUI.BUTTONWIDTH,ConstsGUI.BUTTONHEIGHT);
		}
	}
	
	private void anyadirBotones() {
		for (JButton b : botones) {
			this.add(b);
		}
	}
	
	protected void anyadirNombres(String[] nombres) {
		for (int i = 0; i < this.botones.length; i++) {
			this.botones[i].setText(nombres[i]);
		}
	}
}

@SuppressWarnings("serial")
abstract class MenuMostrarTexto extends JPanel {
	private MostradorTexto texto;
	private JButton volver = new JButton("Volver");
	private int medioX = (ConstsGUI.FRAMEWIDTH/2)-(ConstsGUI.BUTTONWIDTH/2);
	
	protected MenuMostrarTexto(ArrayList<String> texto) {
		this.setLayout(null);
		
		this.configurarMostradorTexto(texto);
		
		this.configurarBotonVolver();
		
		this.add(this.texto);
		this.add(volver);
	}
	
	private void configurarMostradorTexto(ArrayList<String> texto) {
		this.texto = new MostradorTexto(texto);
		this.texto.setBounds(20, 20, 585, 320);
	}
	
	private void configurarBotonVolver() {
		this.volver.setBounds(this.medioX, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
		this.volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.volver();
			}
		});
	}
}

@SuppressWarnings("serial")
abstract class MenuModificarJugador extends JPanel {
	private JTextField inText;
	private JButton getText = new JButton("Añadir jugador");
	private JButton volver = new JButton("Volver");
	private int medioX = (ConstsGUI.FRAMEWIDTH/2)-(ConstsGUI.BUTTONWIDTH/2);
	
	MenuModificarJugador() {
		this.setLayout(null);
		this.setBackground(Color.GREEN);
		
		this.configurarComponentes();

		this.add(inText);
		this.add(getText);
		this.add(volver);
	}
	
	protected JTextField getInText() {
		return this.inText;
	}
	
	private void configurarComponentes() {
		this.inText = new JTextField();
		this.inText.setBounds(this.medioX-(ConstsGUI.BUTTONWIDTH/2)-10, 300, ConstsGUI.BUTTONWIDTH*2+20, 20);

		this.getText.setBounds(this.medioX-(ConstsGUI.BUTTONWIDTH/2)-10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
		this.getText.addActionListener(this.setListenerGetText());
		
		this.volver.setBounds(this.medioX+(ConstsGUI.BUTTONWIDTH/2)+10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
		this.volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.volver();
			}
		});
	}
	
	protected void setNombreGetText(String nombre) {
		this.getText.setText(nombre);
	}
	
	protected abstract ActionListener setListenerGetText();	
}

@SuppressWarnings("serial")
class MenuPrincipal extends MenuSeleccion {
	MenuPrincipal() {
		super(5);
				
		this.setBackground(Color.CYAN);

		super.anyadirNombres(new String[]{"Jugar","Ranking","Histórico","Jugadores","Salir"});
		
		super.getBotones()[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		
		super.getBotones()[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarRanking();
			}
		});
		
		super.getBotones()[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarHistorico();
			}
		});
		
		super.getBotones()[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarGestorJugadores();
			}
		});
		
		super.getBotones()[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.volver();
			}
		});
	}
}

@SuppressWarnings("serial")
class MenuMostrarRanking extends MenuMostrarTexto {
	MenuMostrarRanking() {
		super(Gestor.jugadores.getRanking());
		this.setBackground(Color.MAGENTA);
	}
}

@SuppressWarnings("serial")
class MenuMostrarJugadores extends MenuMostrarTexto {
	MenuMostrarJugadores() {
		super(Gestor.jugadores.getNombres());
		this.setBackground(Color.GREEN);
	}
}

@SuppressWarnings("serial")
class MenuMostrarHistorico extends MenuMostrarTexto {
	MenuMostrarHistorico() {
		super(Gestor.historial.getHistorial());
		this.setBackground(Color.ORANGE);
	}
}

@SuppressWarnings("serial")
class MenuGestionJugadores extends MenuSeleccion {
	MenuGestionJugadores() {
		super(4);
		
		this.setBackground(Color.GREEN);
		
		super.anyadirNombres(new String[]{"Ver jugadores","Añadir jugador","Eliminar jugador","Volver"});
	
		super.getBotones()[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarJugadores();
			}
		});
		
		super.getBotones()[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarElegirJugador();
			}
		});
		
		super.getBotones()[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.mostrarEliminarJugador();
			}
		});
		
		super.getBotones()[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JuegoGUI.visuales.volver();
			}
		});
	}
	
	@SuppressWarnings("serial")
	class MenuAnyadirJugador extends MenuModificarJugador {

		public MenuAnyadirJugador() {
			super.setNombreGetText("Añadir jugador");
		}
		
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getInText().getText();
					if (Gestor.jugadores.crearJugador(nombre)) {
						JOptionPane.showMessageDialog(MenuAnyadirJugador.this, Consts.MENU_SUCCEED);
						JuegoGUI.visuales.volver();
						
					} else {
						JOptionPane.showMessageDialog(MenuAnyadirJugador.this, Consts.MENU_ADD_JUGADOR_ERROR);
						Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
					}
				}
			};
		}
	}

	@SuppressWarnings("serial")
	class MenuEliminarJugador extends MenuModificarJugador {

		public MenuEliminarJugador() {
			super.setNombreGetText("Eliminar jugador");
		}
		
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getInText().getText();
					if (Gestor.jugadores.removeJugador(nombre)) {
						JOptionPane.showMessageDialog(MenuEliminarJugador.this, Consts.MENU_SUCCEED);
						JuegoGUI.visuales.volver();
						
					} else {
						JOptionPane.showMessageDialog(MenuEliminarJugador.this, Consts.MENU_REMOVE_JUGADOR_ERROR);
						Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
					}
				}
			};
		}
	}
	
}