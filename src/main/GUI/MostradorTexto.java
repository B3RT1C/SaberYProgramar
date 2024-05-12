package main.GUI;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
class MostradorTexto extends JScrollPane {
	
	private JTextArea texto;
	
	MostradorTexto(ArrayList<String> info) {
		this.texto = new JTextArea(this.listaAString(info));
		this.texto.setEditable(false);
		this.setViewportView(texto);
	}
	
	MostradorTexto(String info) {
		this.texto = new JTextArea(info);
		this.texto.setEditable(false);
		this.setViewportView(texto);
	}

	private String listaAString(ArrayList<String> lista) {
		String texto = "";
		for (String i : lista) {
			texto += i+"\n";
		}
		return texto;
	}
}