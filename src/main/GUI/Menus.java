package main.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gestores.Gestor;
import jugadores.Cpu;
import jugadores.Jugador;
import preguntas.Ingles;
import preguntas.Pregunta;
import util.Consts;

/*
 * Como quiero tener todas las clases de GUI en un paquete para facilitar la correción me he visto con demasiadas clases en el paquete GUI
 * a si que las he metido todas en el mismo archio y así poder almenos ver claramente que otras clases tengo en el paquete GUI
 */
public class Menus {
	@SuppressWarnings("serial")
	abstract class Seleccion extends JPanel {
		private JButton[] botones;
		
		private int medioY;
		
		protected Seleccion(int numBotones) {
			this.setLayout(null);
			this.botones = new JButton[numBotones];
			
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
				this.botones[i].setBounds(ConstsGUI.MEDIOX,medioY+(30*i),ConstsGUI.BUTTONWIDTH,ConstsGUI.BUTTONHEIGHT);
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
	abstract class MostrarTexto extends JPanel {
		private MostradorTexto texto;
		private JButton volver = new JButton("Volver");
		
		protected MostrarTexto(ArrayList<String> texto) {
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
			this.volver.setBounds(ConstsGUI.MEDIOX, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
			this.volver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.volver();
				}
			});
		}
	}
	
	@SuppressWarnings("serial")
	abstract class ImputJugador extends JPanel {
		private JTextField inText;
		private JButton getText = new JButton();
		private JButton volver = new JButton("Volver");
		
		protected ImputJugador() {
			this.setLayout(null);
			
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
			this.inText.setBounds(ConstsGUI.MEDIOX-(ConstsGUI.BUTTONWIDTH/2)-10, 300, ConstsGUI.BUTTONWIDTH*2+20, 20);
	
			this.getText.setBounds(ConstsGUI.MEDIOX-(ConstsGUI.BUTTONWIDTH/2)-10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
			this.getText.addActionListener(this.setListenerGetText());
			
			this.volver.setBounds(ConstsGUI.MEDIOX+(ConstsGUI.BUTTONWIDTH/2)+10, 380, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
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
	class Principal extends Seleccion {
		Principal() {
			super(5);
					
			this.setBackground(Color.CYAN);
	
			super.anyadirNombres(new String[]{"Jugar","Ranking","Histórico","Jugadores","Salir"});
			
			JButton[] botones = super.getBotones();
			
			botones[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarElegirRondas();
				}
			});
			
			botones[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarRanking();
				}
			});
			
			botones[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarHistorico();
				}
			});
			
			botones[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarGestorJugadores();
				}
			});
			
			botones[4].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
	//				JuegoGUI.visuales.volver();
					System.exit(0);
				}
			});
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Saber Y Programar", 230, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class MostrarRanking extends MostrarTexto {
		MostrarRanking() {
			super(Gestor.jugadores.getRanking());
			this.setBackground(Color.MAGENTA);
		}
	}
	
	@SuppressWarnings("serial")
	class MostrarJugadores extends MostrarTexto {
		MostrarJugadores() {
			super(Gestor.jugadores.getNombres());
			this.setBackground(Color.GREEN);
		}
	}
	
	@SuppressWarnings("serial")
	class MostrarHistorico extends MostrarTexto {
		MostrarHistorico() {
			super(Gestor.historial.getHistorial());
			this.setBackground(Color.ORANGE);
		}
	}
	
	@SuppressWarnings("serial")
	class GestionJugadores extends Seleccion {
		GestionJugadores() {
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
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Gestión de jugadores", 215, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class AnyadirJugador extends ImputJugador {
		AnyadirJugador() {
			super.setNombreGetText("Añadir jugador");
			this.setBackground(Color.GREEN);
		}
		
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getInText().getText();
					if (Gestor.jugadores.crearJugador(nombre)) {
						JOptionPane.showMessageDialog(AnyadirJugador.this, Consts.MENU_SUCCEED);
						JuegoGUI.visuales.volver();
						
					} else {
						JOptionPane.showMessageDialog(AnyadirJugador.this, Consts.MENU_ADD_JUGADOR_ERROR);
						Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
					}
				}
			};
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Añadir jugador", 250, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class EliminarJugador extends ImputJugador {
		EliminarJugador() {
			super.setNombreGetText("Eliminar jugador");
			this.setBackground(Color.GREEN);
		}
		
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getInText().getText();
					if (Gestor.jugadores.removeJugador(nombre)) {
						JOptionPane.showMessageDialog(EliminarJugador.this, Consts.MENU_SUCCEED);
						JuegoGUI.visuales.volver();
						
					} else {
						JOptionPane.showMessageDialog(EliminarJugador.this, Consts.MENU_REMOVE_JUGADOR_ERROR);
						Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
					}
				}
			};
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Eliminar jugador", 245, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class ElegirRondas extends Seleccion {
		
		private static int numRondas;
	
		private TerminarPartida botonSalir = new TerminarPartida();
		
		ElegirRondas() {
			super(4);
			super.anyadirNombres(new String[]{"Partida rápida: 3 rondas", "Partida corta: 5 rondas", "Partida normal: 10 rondas", "Partida larga: 20 rondas"});
			
			this.setLayout(null);
			this.setBackground(Color.PINK);
			
			JButton[] botones = super.getBotones();
			
			botones[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					establecerNumeroRondasYMostrarElegirCantidadJugadores(3);
				}
			});
			
			botones[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					establecerNumeroRondasYMostrarElegirCantidadJugadores(5);
				}
			});
			
			botones[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					establecerNumeroRondasYMostrarElegirCantidadJugadores(10);
				}
			});
			
			botones[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					establecerNumeroRondasYMostrarElegirCantidadJugadores(20);
				}
			});
			
			this.add(this.botonSalir);
		}
		
		static int getNumRondas() {
			return numRondas;
		}
		
		private void establecerNumeroRondasYMostrarElegirCantidadJugadores(int numRondas) {
			ElegirRondas.numRondas = numRondas;
			JuegoGUI.visuales.mostrarElegirCantidadJugadores();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Seleccionar ronda", 230, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class ElegirCantidadJugadores extends JPanel {
		//No se pueden elegir 0 jugadores para una partida, si hay 1 jugador de humanos/cpus y 0 del otro, el que vale 1 no podrá ver si valor reducido para que no existan 0 jugadores en la partida
		private static int numHumanos;
		private int numCPUs  = 0;
		private int botonWidth = 45;
		private int medioX = (ConstsGUI.FRAMEWIDTH/2)-(this.botonWidth/2);
		private JButton getInfoJugadores = new JButton("Aceptar");
		private TerminarPartida botonSalir = new TerminarPartida();
		
		ElegirCantidadJugadores() {
			this.setLayout(null);
			this.setBackground(Color.PINK);
			numHumanos = 1;
			
			this.addContadorHumanos();
			this.addContadorCPUs();
			
			this.getInfoJugadores.setBounds((ConstsGUI.FRAMEWIDTH/2)-(ConstsGUI.BUTTONWIDTH/2), 300, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
			this.getInfoJugadores.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						Gestor.partida.configurar(numHumanos+numCPUs, ElegirRondas.getNumRondas());
						
						for (int i = 0; i < numCPUs; i++) {
							Gestor.partida.addCPU();
						}
						
						if (numHumanos == 0) {
							JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
							
						} else {
							JuegoGUI.visuales.mostrarElegirJugadorPartida();						
						}
				}
			});
			this.add(getInfoJugadores);
			this.add(botonSalir);
		}
		
		static int getNumHumanos() {
			return ElegirCantidadJugadores.numHumanos;
		}
		
		private void addContadorHumanos() {
			JButton menos = new JButton("-");
			menos.setBounds(this.medioX-this.botonWidth, 150, this.botonWidth, 20);
			menos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numHumanos == 0 || (numCPUs == 0 && numHumanos == 1)) {
						//Do nothing
					} else {
						numHumanos--;
					}
					
					ElegirCantidadJugadores.this.repaint();
				}
			});
			
			JButton mas = new JButton("+");
			mas.setBounds(this.medioX+this.botonWidth, 150, this.botonWidth, 20);
			mas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numHumanos+numCPUs != 4) {
						numHumanos++;
					}
					
					ElegirCantidadJugadores.this.repaint();
				}
			});
			
			this.add(menos);
			this.add(mas);
		}
		
		private void addContadorCPUs() {
			JButton menos = new JButton("-");
			menos.setBounds(this.medioX-this.botonWidth, 200, this.botonWidth, 20);
			menos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numCPUs == 0 || (numCPUs == 1 && numHumanos == 0)) {
						//Do nothing
					} else {
						numCPUs--;
					}
					
					ElegirCantidadJugadores.this.repaint();
				}
			});
			
			JButton mas = new JButton("+");
			mas.setBounds(this.medioX+this.botonWidth, 200, this.botonWidth, 20);
			mas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numHumanos+numCPUs != 4) {
						numCPUs++;
					}
					
					ElegirCantidadJugadores.this.repaint();
				}
			});
			
			this.add(menos);
			this.add(mas);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//ContadorHumanos
			g.drawString("Número de jugadores humanos", this.medioX+(this.botonWidth/2)-80, 145);
			if (numHumanos+numCPUs == 4) {
				g.setColor(Color.RED);
			}
			g.drawString(String.valueOf(numHumanos), this.medioX+(this.botonWidth/2), 165);
			g.setColor(Color.BLACK);
			
			//ContadorCPUS
			g.drawString("Número de jugadores CPUs", this.medioX+(this.botonWidth/2)-80, 195);
			if (numHumanos+numCPUs == 4) {
				g.setColor(Color.RED);
			}
			g.drawString(String.valueOf(this.numCPUs), this.medioX+(this.botonWidth/2), 215);
			g.setColor(Color.BLACK);
		
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Elegir número de jugadores", 195, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class AnyadirJugadorPartida extends ImputJugador  {
		
		private int numHumanos;
		private TerminarPartida botonSalir = new TerminarPartida();
		
		AnyadirJugadorPartida() {
			this.setBackground(Color.PINK);
			super.setNombreGetText("Seleccionar jugador");
	
			this.numHumanos = ElegirCantidadJugadores.getNumHumanos();
			
			this.esMasFacilHacerEsteMetodoQueArreglarLaHerencia();
			
			this.add(botonSalir);
			
			
		}
		
		//Este metodo elimina un boton que hereda del padre y que causa problemas en este caso, podría arreglarlo pero no me apetece y tampoco me da tiempo :)
		private void esMasFacilHacerEsteMetodoQueArreglarLaHerencia() {
			for (Component i : this.getComponents()) {
				if (i instanceof JButton  && ((JButton)i).getText().equals("Volver")) {
					this.remove(i);
				}
			}
		}
	
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = getInText().getText();
					
					if (nombre.toUpperCase().matches("CPU\\d*")) {
						JOptionPane.showMessageDialog(AnyadirJugadorPartida.this, Consts.MENU_ADD_JUGADOR_ERROR);
						Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.MENU_ADD_JUGADOR_ERROR);
						
					} else if (verificarExistenciaJugador(nombre)) {
						addJugadorPartida(nombre);
					}
				}
			};
		}
		
		private boolean verificarExistenciaJugador(String nombre) {
			if (Gestor.jugadores.existsJugador(nombre)) {
				return true;
				
			} else {
				int opcion = JOptionPane.showOptionDialog(AnyadirJugadorPartida.this, Consts.MENU_ADD_JUGADOR_INEXISTENTE(nombre), null, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Y", "N"}, "Y");
				
				if (opcion == 0) {
					return Gestor.jugadores.crearJugador(nombre);
					
				} else {
					return false;
				}
			}
		}
		
		private void addJugadorPartida(String nombre) {
			if (Gestor.partida.addPersona(nombre)) {
				JOptionPane.showMessageDialog(AnyadirJugadorPartida.this, Consts.MENU_SUCCEED);
				
				this.numHumanos--;
				AnyadirJugadorPartida.this.repaint();
	
				if (this.numHumanos == 0) {
					JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
				}
				
			} else {
				JOptionPane.showMessageDialog(AnyadirJugadorPartida.this, Consts.ERROR_JUGADOR_REPETIDO);
				Gestor.log.escribirArchivo(Consts.LOG_ERROR + Consts.ERROR_JUGADOR_REPETIDO);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("Jugadores a añadir restantes: " + String.valueOf(this.numHumanos), 110, 280);
				
			g.setFont(new Font(null, Font.BOLD, 20));			
			g.drawString("Seleccionar jugador", 230, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class PreguntaMateLetras extends ImputJugador {
		private Pregunta pregunta;
		private Jugador jugador;
		private TerminarPartida salir = new TerminarPartida();
		private MostradorTexto enunciado;
		
		PreguntaMateLetras(Pregunta pregunta) {
			this.setBackground(Color.PINK);
			super.setNombreGetText("Responder");
			
			this.pregunta = pregunta;
			this.enunciado = new MostradorTexto(pregunta.getEnunciado());
			this.enunciado.setBounds(110, 200, 420, 20);
			
			this.jugador = Gestor.partida.nextJugador();
			
			this.esMasFacilHacerEsteMetodoQueArreglarLaHerencia();
			
			this.add(salir);
			this.add(enunciado);
	
			this.repaint();
		}
		
		void isCPUJugando() {
			if (jugador instanceof Cpu) {
				JuegoGUI.visuales.mostrarFinPregunta(jugador, ((Cpu)jugador).generarRespuesta(pregunta), pregunta);
			}
		}
	
		@Override
		protected ActionListener setListenerGetText() {
			return new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarFinPregunta(jugador, getInText().getText(), pregunta);
				}
			};
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
			g.drawString("Responder pregunta", 230, 100);
		}
	}
	
	@SuppressWarnings("serial")
	class PreguntaIngles extends Seleccion {
		private Pregunta pregunta;
		private Jugador jugador;
		private TerminarPartida salir = new TerminarPartida();
		private MostradorTexto enunciado;
	
		PreguntaIngles(Pregunta pregunta) {
			super(Consts.TOTAL_OPCIONES_INGLES);
	
			this.setBackground(Color.PINK);
			
			this.pregunta = pregunta;
			ArrayList<String> socorro = ((Ingles)this.pregunta).getOpciones();
			String[] opciones = {socorro.get(0), socorro.get(1), socorro.get(2), socorro.get(3)};
			super.anyadirNombres(opciones);
			
			this.enunciado = new MostradorTexto(pregunta.getEnunciado());
			this.enunciado.setBounds(110, 100, 420, 40);
			
			this.jugador = Gestor.partida.nextJugador();
			
			JButton[] botones = super.getBotones();
			
			botones[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarFinPregunta(jugador, botones[0].getText(), pregunta);
				}
			});
			botones[0].setToolTipText(opciones[0]);
			
			botones[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarFinPregunta(jugador, botones[1].getText(), pregunta);
				}
			});
			botones[1].setToolTipText(opciones[1]);
			
			botones[2].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarFinPregunta(jugador, botones[2].getText(), pregunta);
				}
			});
			botones[2].setToolTipText(opciones[2]);
			
			botones[3].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarFinPregunta(jugador, botones[3].getText(), pregunta);
				}
			});
			botones[3].setToolTipText(opciones[3]);
			
			this.add(salir);
			this.add(enunciado);
			
			this.repaint();
		}
		
		void isCPUJugando() {
			if (jugador instanceof Cpu) {
				JuegoGUI.visuales.mostrarFinPregunta(jugador, ((Cpu)jugador).generarRespuesta(pregunta), pregunta);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("Turno de " + jugador.getNombre(), 110, 90);
	
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Responder pregunta", 230, 50);
		}
	}
	
	@SuppressWarnings("serial")
	class FinPregunta extends JPanel {
		private Jugador jugador;
		private String respuesta;
		private Pregunta pregunta;
		private String mensaje;
		
		FinPregunta(Jugador jugador, String respuesta, Pregunta pregunta) {
			this.setBackground(Color.PINK);
	
			this.jugador = jugador;
			this.respuesta = respuesta;
			this.pregunta = pregunta;
	
			this.mensaje = this.generarMensaje();
		}
		
		String getMensaje() {
			return this.mensaje;
		}
		
		void continuar() {
			if (Gestor.partida.isTerminada()) {
				JuegoGUI.visuales.mostrarFinPartida();
				
			} else if (Gestor.partida.isFinRonda()) {
				JuegoGUI.visuales.mostrarFinRonda();
			
			} else {
				JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
			}
		}
		
		private String generarMensaje() {
			String mensaje = "";
			
			mensaje += "Jugador: " + jugador.getNombre() + "\n";
			
			if (jugador.responder(respuesta, pregunta)) {
				mensaje += Consts.PREGUNTA_ACERTADA;
			
			} else {
				mensaje += "Solución: " + this.pregunta.getSolucion() + "\n";
				mensaje += Consts.PREGUNTA_FALLADA;
			}
			
			return mensaje;
		}
	}
	
	@SuppressWarnings("serial")
	class FinRonda extends MostrarPuntuaciones {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Fin de ronda", 260, 100);
		}

		@Override
		protected JButton addBoton() {
			JButton continuar = new JButton("Continuar");
			continuar.setBounds(ConstsGUI.MEDIOX, 300, ConstsGUI.BUTTONWIDTH, ConstsGUI.BUTTONHEIGHT);
			continuar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JuegoGUI.visuales.mostrarPregunta(Gestor.partida.nextPregunta());
				}
			});
			return continuar;
		}
	}
	
	@SuppressWarnings("serial")
	class FinPartida extends MostrarPuntuaciones {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(null, Font.BOLD, 20));
			g.drawString("Resultados finales", 230, 100);
		}
	
		@Override
		protected JButton addBoton() {
			return new TerminarPartida();
		}
	}

}